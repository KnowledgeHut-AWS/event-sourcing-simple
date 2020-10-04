package com.kh.sda.devops.es.service;

import com.kh.sda.devops.es.domain.Address;
import com.kh.sda.devops.es.domain.Contact;
import com.kh.sda.devops.es.domain.User;
import com.kh.sda.devops.es.events.Event;
import com.kh.sda.devops.es.events.UserAddressAddedEvent;
import com.kh.sda.devops.es.events.UserAddressRemovedEvent;
import com.kh.sda.devops.es.events.UserContactAddedEvent;
import com.kh.sda.devops.es.events.UserContactRemovedEvent;
import com.kh.sda.devops.es.events.UserCreatedEvent;
import com.kh.sda.devops.es.repository.EventStore;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class UserService {

    private final EventStore repository;

    public UserService(EventStore repository) {
        this.repository = repository;
    }

    public void createUser(String userId, String firstName, String lastName) {
        repository.addEvent(userId, new UserCreatedEvent(userId, firstName, lastName));
    }

    public void updateUser(String userId, Set<Contact> contacts, Set<Address> addresses) throws Exception {
        User user = getUser(userId);

        user.getContacts().stream()
                .filter(c -> !contacts.contains(c))
                .forEach(c -> repository.addEvent(userId, new UserContactRemovedEvent(c.getType(), c.getDetail())));

        contacts.stream()
                .filter(c -> !user.getContacts().contains(c))
                .forEach(c -> repository.addEvent(userId, new UserContactAddedEvent(c.getType(), c.getDetail())));

        user.getAddresses().stream()
                .filter(a -> !addresses.contains(a))
                .forEach(a -> repository.addEvent(userId, new UserAddressRemovedEvent(a.getCity(), a.getState(), a.getPostcode())));

        addresses.stream()
                .filter(a -> !user.getAddresses().contains(a))
                .forEach(a -> repository.addEvent(userId, new UserAddressAddedEvent(a.getCity(), a.getState(), a.getPostcode())));
    }

    public Set<Contact> getContactByType(String userId, String contactType) throws Exception {
        return getUser(userId).getContacts()
                .stream()
                .filter(c -> c.getType().equals(contactType))
                .collect(toSet());
    }

    public Set<Address> getAddressByRegion(String userId, String state) throws Exception {
        return getUser(userId).getAddresses()
                .stream()
                .filter(a -> a.getState().equals(state))
                .collect(toSet());
    }

    public User getUser(String userId) {
        final List<Event> events = repository.getEvents(userId);

        if (events == null || events.size() == 0)
            throw new RuntimeException("User does not exist.");

        UserCreatedEvent userCreatedEvent = (UserCreatedEvent) events.get(0); // user create event is always first
        User user = new User(userCreatedEvent.getUserId(), userCreatedEvent.getFirstName(), userCreatedEvent.getLastName());

        events.stream().forEach(event -> {
            if (event instanceof UserAddressAddedEvent) {
                UserAddressAddedEvent userAddressAddedEvent = (UserAddressAddedEvent) event;
                Address address = new Address(userAddressAddedEvent.getCity(), userAddressAddedEvent.getState(), userAddressAddedEvent.getPostCode());
                user.getAddresses().add(address);
            } else if (event instanceof UserAddressRemovedEvent) {
                UserAddressRemovedEvent userAddressRemovedEvent = (UserAddressRemovedEvent) event;
                Address address = new Address(userAddressRemovedEvent.getCity(), userAddressRemovedEvent.getState(), userAddressRemovedEvent.getPostCode());
                user.getAddresses().remove(address);
            } else if (event instanceof UserContactAddedEvent) {
                UserContactAddedEvent userContactAddedEvent = (UserContactAddedEvent) event;
                Contact contact = new Contact(userContactAddedEvent.getContactType(), userContactAddedEvent.getContactDetails());
                user.getContacts().add(contact);
            } else if (event instanceof UserContactRemovedEvent) {
                UserContactRemovedEvent userContactRemovedEvent = (UserContactRemovedEvent) event;
                Contact contact = new Contact(userContactRemovedEvent.getContactType(), userContactRemovedEvent.getContactDetails());
                user.getContacts().remove(contact);
            }
        });

        return user;
    }
}

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
import java.util.UUID;

public class UserStateManager {

    public static User recreateUserState(EventStore store, String userId) {
        User user = null;

        List<Event> events = store.getEvents(userId);
        for (Event event : events) {
            if (event instanceof UserCreatedEvent) {
                UserCreatedEvent e = (UserCreatedEvent) event;
                user = new User(UUID.randomUUID()
                        .toString(), e.getFirstName(), e.getLastName());
            }
            if (event instanceof UserAddressAddedEvent) {
                UserAddressAddedEvent e = (UserAddressAddedEvent) event;
                Address address = new Address(e.getCity(), e.getState(), e.getPostCode());
                if (user != null)
                    user.getAddresses()
                            .add(address);
            }
            if (event instanceof UserAddressRemovedEvent) {
                UserAddressRemovedEvent e = (UserAddressRemovedEvent) event;
                Address address = new Address(e.getCity(), e.getState(), e.getPostCode());
                if (user != null)
                    user.getAddresses()
                            .remove(address);
            }
            if (event instanceof UserContactAddedEvent) {
                UserContactAddedEvent e = (UserContactAddedEvent) event;
                Contact contact = new Contact(e.getContactType(), e.getContactDetails());
                if (user != null)
                    user.getContacts()
                            .add(contact);
            }
            if (event instanceof UserContactRemovedEvent) {
                UserContactRemovedEvent e = (UserContactRemovedEvent) event;
                Contact contact = new Contact(e.getContactType(), e.getContactDetails());
                if (user != null)
                    user.getContacts()
                            .remove(contact);
            }
        }

        return user;
    }
}

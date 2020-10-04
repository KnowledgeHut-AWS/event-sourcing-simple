package com.kh.sda.devops.es.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

    private String userid;
    private String firstname;
    private String lastname;
    private Set<Contact> contacts = new HashSet<>();
    private Set<Address> addresses = new HashSet<>();

    public User(String userid, String firstname, String lastname) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contacts = contacts;
        this.addresses = addresses;
    }

    public String getUserid() {
        return userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserid().equals(user.getUserid()) &&
                getFirstname().equals(user.getFirstname()) &&
                getLastname().equals(user.getLastname()) &&
                Objects.equals(getContacts(), user.getContacts()) &&
                Objects.equals(getAddresses(), user.getAddresses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserid(), getFirstname(), getLastname(), getContacts(), getAddresses());
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", contacts=" + contacts +
                ", addresses=" + addresses +
                '}';
    }
}

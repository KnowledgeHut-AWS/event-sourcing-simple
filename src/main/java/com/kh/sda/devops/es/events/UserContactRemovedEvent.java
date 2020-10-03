package com.kh.sda.devops.es.events;

import java.util.Objects;

public class UserContactRemovedEvent extends Event {

  private String contactType;
  private String contactDetails;

  public UserContactRemovedEvent(String contactType, String contactDetails) {
    this.contactType = contactType;
    this.contactDetails = contactDetails;
  }

  public String getContactType() {
    return contactType;
  }

  public String getContactDetails() {
    return contactDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserContactRemovedEvent)) return false;
    UserContactRemovedEvent that = (UserContactRemovedEvent) o;
    return getContactType().equals(that.getContactType()) &&
        getContactDetails().equals(that.getContactDetails());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getContactType(), getContactDetails());
  }

  @Override
  public String toString() {
    return "UserContactRemovedEvent{" +
        "contactType='" + contactType + '\'' +
        ", contactDetails='" + contactDetails + '\'' +
        ", id=" + id +
        ", created=" + created +
        '}';
  }
}

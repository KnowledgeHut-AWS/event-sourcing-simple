package com.kh.sda.devops.es.events;

import java.util.Objects;

public class UserCreatedEvent extends Event {

  private String userId;
  private String firstName;
  private String lastName;

  public UserCreatedEvent(String userId, String firstName, String lastName) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getUserId() {
    return userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserCreatedEvent)) return false;
    UserCreatedEvent that = (UserCreatedEvent) o;
    return Objects.equals(getUserId(), that.getUserId()) &&
        Objects.equals(getFirstName(), that.getFirstName()) &&
        Objects.equals(getLastName(), that.getLastName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserId(), getFirstName(), getLastName());
  }

  @Override
  public String toString() {
    return "UserCreatedEvent{" +
        "userId='" + userId + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", id=" + id +
        ", created=" + created +
        '}';
  }
}

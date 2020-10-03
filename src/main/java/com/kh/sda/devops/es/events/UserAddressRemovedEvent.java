package com.kh.sda.devops.es.events;

import java.util.Objects;

public class UserAddressRemovedEvent extends Event {

  private String city;
  private String state;
  private String postCode;

  public UserAddressRemovedEvent(String city, String state, String postCode) {
    this.city = city;
    this.state = state;
    this.postCode = postCode;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostCode() {
    return postCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserAddressRemovedEvent)) return false;
    UserAddressRemovedEvent that = (UserAddressRemovedEvent) o;
    return getCity().equals(that.getCity()) &&
        getState().equals(that.getState()) &&
        getPostCode().equals(that.getPostCode());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCity(), getState(), getPostCode());
  }

  @Override
  public String toString() {
    return "UserAddressRemovedEvent{" +
        "city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", postCode='" + postCode + '\'' +
        ", id=" + id +
        ", created=" + created +
        '}';
  }
}

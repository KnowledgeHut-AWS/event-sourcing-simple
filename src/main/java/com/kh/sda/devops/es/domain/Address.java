package com.kh.sda.devops.es.domain;

import java.util.Objects;

public class Address {

  private String city;
  private String state;
  private String postcode;

  public Address(String city, String state, String postcode) {
    this.city = city;
    this.state = state;
    this.postcode = postcode;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostcode() {
    return postcode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Address)) return false;
    Address address = (Address) o;
    return getCity().equals(address.getCity()) &&
        getState().equals(address.getState()) &&
        getPostcode().equals(address.getPostcode());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCity(), getState(), getPostcode());
  }

  @Override
  public String toString() {
    return "Address{" +
        "city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", postcode='" + postcode + '\'' +
        '}';
  }
}

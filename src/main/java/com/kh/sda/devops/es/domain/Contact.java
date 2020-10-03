package com.kh.sda.devops.es.domain;

import java.util.Objects;

public class Contact {

  private String type;
  private String detail;

  public Contact(String type, String detail) {
    this.type = type;
    this.detail = detail;
  }

  public String getType() {
    return type;
  }

  public String getDetail() {
    return detail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Contact)) return false;
    Contact contact = (Contact) o;
    return getType().equals(contact.getType()) &&
        getDetail().equals(contact.getDetail());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getType(), getDetail());
  }

  @Override
  public String toString() {
    return "Contact{" +
        "type='" + type + '\'' +
        ", detail='" + detail + '\'' +
        '}';
  }
}
package com.kh.sda.devops.es.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UserContact {

  private Map<String, Set<Contact>> contactByType = new HashMap<>();

  public UserContact(Map<String, Set<Contact>> contactByType) {
    this.contactByType = contactByType;
  }

  public Map<String, Set<Contact>> getContactByType() {
    return contactByType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserContact)) return false;
    UserContact that = (UserContact) o;
    return Objects.equals(getContactByType(), that.getContactByType());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getContactByType());
  }
}

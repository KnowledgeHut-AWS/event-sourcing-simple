package com.kh.sda.devops.es.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UserAddress {

  private Map<String, Set<Address>> addressByRegion = new HashMap<>();

  public UserAddress(Map<String, Set<Address>> addressByRegion) {
    this.addressByRegion = addressByRegion;
  }

  public Map<String, Set<Address>> getAddressByRegion() {
    return addressByRegion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserAddress)) return false;
    UserAddress that = (UserAddress) o;
    return Objects.equals(getAddressByRegion(), that.getAddressByRegion());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAddressByRegion());
  }

  @Override
  public String toString() {
    return "UserAddress{" +
        "addressByRegion=" + addressByRegion +
        '}';
  }
}

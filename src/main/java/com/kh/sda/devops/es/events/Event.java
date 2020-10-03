package com.kh.sda.devops.es.events;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public abstract class Event {

  public final UUID id;

  public final Date created;

  public Event() {
    this.id = UUID.randomUUID();
    this.created = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
  }
}

package com.kh.sda.devops.es.service;

import com.kh.sda.devops.es.domain.User;
import com.kh.sda.devops.es.repository.EventStore;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
    @Test
    public void createUser() {
        EventStore repo = new EventStore();
        UserService service = new UserService(repo);
        final String bryanId = UUID.randomUUID().toString();
        service.createUser(bryanId, "Bryan", "Dollery");
        final User bryan = service.getUser(bryanId);
        assertEquals("Bryan", bryan.getFirstname());
    }
}
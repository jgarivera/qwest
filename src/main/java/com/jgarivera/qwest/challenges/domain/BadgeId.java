package com.jgarivera.qwest.challenges.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record BadgeId(UUID id) {

    public BadgeId {
        Assert.notNull(id, "id must not be null");
    }
}

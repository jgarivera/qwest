package com.jgarivera.qwest.challenges.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record ChallengeId(UUID id) {

    public ChallengeId {
        Assert.notNull(id, "id must not be null");
    }
}

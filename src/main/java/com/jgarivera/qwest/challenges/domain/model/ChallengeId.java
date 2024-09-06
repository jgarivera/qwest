package com.jgarivera.qwest.challenges.domain.model;

import org.jmolecules.ddd.types.Identifier;
import org.springframework.util.Assert;

import java.util.UUID;

public record ChallengeId(UUID id) implements Identifier {

    public ChallengeId {
        Assert.notNull(id, "id must not be null");
    }
}

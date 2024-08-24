package com.jgarivera.qwest.challenges.domain;

import org.jmolecules.ddd.types.Identifier;
import org.springframework.util.Assert;

import java.util.UUID;

public record BadgeId(UUID id) implements Identifier {

    public BadgeId {
        Assert.notNull(id, "id must not be null");
    }
}

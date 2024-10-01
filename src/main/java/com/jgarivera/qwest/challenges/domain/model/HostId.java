package com.jgarivera.qwest.challenges.domain.model;

import org.jmolecules.ddd.types.ValueObject;

import java.util.UUID;

public record HostId(UUID id) implements ValueObject {
}

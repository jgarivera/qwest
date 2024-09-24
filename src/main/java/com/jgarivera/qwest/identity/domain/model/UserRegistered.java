package com.jgarivera.qwest.identity.domain.model;

import org.jmolecules.event.types.DomainEvent;

public record UserRegistered(UserId id) implements DomainEvent {
}

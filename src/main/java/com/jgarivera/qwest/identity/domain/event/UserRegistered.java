package com.jgarivera.qwest.identity.domain.event;

import com.jgarivera.qwest.identity.domain.User;
import org.jmolecules.event.types.DomainEvent;

public record UserRegistered(User user) implements DomainEvent {
}

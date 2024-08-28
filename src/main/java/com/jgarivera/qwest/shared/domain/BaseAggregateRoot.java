package com.jgarivera.qwest.shared.domain;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This class is adopted from {@link AbstractAggregateRoot} and extends {@link BaseEntity} to retain
 * its persistable behavior.
 */
public abstract class BaseAggregateRoot<T extends AggregateRoot<T, ID>, ID extends Identifier>
        extends BaseEntity<T, ID>
        implements AggregateRoot<T, ID> {

    @Transient
    private transient final List<Object> domainEvents = new ArrayList<>();

    /**
     * As required by JPA.
     */
    protected BaseAggregateRoot() {
    }

    protected BaseAggregateRoot(ID id) {
        super(id);
    }

    /**
     * Registers the given event object for publication on a call to a Spring Data repository's save or delete methods.
     *
     * @param event must not be {@literal null}.
     * @return the event that has been added.
     */
    protected <T> T registerEvent(T event) {
        Assert.notNull(event, "Domain event must not be null");

        domainEvents.add(event);

        return event;
    }

    /**
     * Clears all domain events currently held. Usually invoked by the infrastructure in place in Spring Data
     * repositories.
     */
    @AfterDomainEventPublication
    protected void clearDomainEvents() {
        domainEvents.clear();
    }

    /**
     * All domain events currently captured by the aggregate.
     */
    @DomainEvents
    protected Collection<Object> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }
}

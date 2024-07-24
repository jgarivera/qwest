package com.jgarivera.qwest.shared;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Transient;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract class BaseEntity<ID> implements Persistable<ID> {

    @Transient
    private boolean isPersisted = false;

    @Override
    public boolean isNew() {
        return !isPersisted;
    }

    @PostPersist
    @PostLoad
    protected void setPersisted() {
        this.isPersisted = true;
    }
}

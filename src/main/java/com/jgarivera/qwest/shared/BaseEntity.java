package com.jgarivera.qwest.shared;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Transient;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Entity;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;

@MappedSuperclass
public abstract class BaseEntity<T extends AggregateRoot<T, ?>, ID>
        implements Entity<T, ID>, Persistable<ID> {

    @EmbeddedId
    protected ID id;

    @Transient
    private boolean isPersisted;

    /**
     * As required by JPA.
     */
    protected BaseEntity() {
    }

    protected BaseEntity(ID id) {
        Assert.notNull(id, "id must not be null");

        this.id = id;
    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return !isPersisted;
    }

    @PostPersist
    @PostLoad
    protected void setPersisted() {
        this.isPersisted = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof BaseEntity<?, ?> other)) {
            return false;
        }

        if (id == null) {
            return false;
        }

        return id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }
}

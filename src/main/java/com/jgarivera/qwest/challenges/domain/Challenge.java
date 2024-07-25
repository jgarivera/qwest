package com.jgarivera.qwest.challenges.domain;

import com.jgarivera.qwest.shared.BaseEntity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.util.Assert;

@Entity
@Table(name = "challenges")
public class Challenge extends BaseEntity<ChallengeId> {

    @EmbeddedId
    private ChallengeId id;
    private String name;
    private String description;

    public Challenge(ChallengeId id, String name) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(name, "name must not be null");

        this.id = id;
        this.name = name;
    }

    public Challenge(ChallengeId id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    /**
     * As required by JPA.
     */
    protected Challenge() {
    }

    public ChallengeId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Challenge other)) {
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

    @Override
    public String toString() {
        return String.format("Challenge[id=%s, name=%s, description=%s]", id.toString(), name, description);
    }
}

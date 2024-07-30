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
    private String title;
    private String description;

    public Challenge(ChallengeId id, String title) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(title, "title must not be null");

        this.id = id;
        this.title = title;
    }

    public Challenge(ChallengeId id, String title, String description) {
        this(id, title);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return String.format("Challenge[id=%s, title=%s, description=%s]", id.toString(), title, description);
    }
}

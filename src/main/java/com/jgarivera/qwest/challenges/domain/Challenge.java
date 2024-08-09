package com.jgarivera.qwest.challenges.domain;

import com.jgarivera.qwest.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.util.Assert;

@Entity
@Table(name = "challenges")
public class Challenge extends BaseEntity<ChallengeId> {

    private String title;
    private String description;

    /**
     * As required by JPA.
     */
    protected Challenge() {
    }

    public Challenge(ChallengeId id, String title) {
        super(id);
        Assert.hasText(title, "title must not be null");

        this.title = title;
    }

    public Challenge(ChallengeId id, String title, String description) {
        this(id, title);
        this.description = description;
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
    public String toString() {
        return String.format("Challenge[id=%s, title=%s, description=%s]", id.toString(), title, description);
    }
}

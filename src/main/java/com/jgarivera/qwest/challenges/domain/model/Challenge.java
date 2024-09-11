package com.jgarivera.qwest.challenges.domain.model;

import com.jgarivera.qwest.shared.domain.model.BaseAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.util.Assert;

@Entity
@Table(name = "challenges")
public class Challenge extends BaseAggregateRoot<Challenge, ChallengeId> {

    private String title;
    private String description;
    private ChallengeVisibility visibility;

    /**
     * As required by JPA.
     */
    protected Challenge() {
    }

    public Challenge(ChallengeId id, String title, ChallengeVisibility visibility) {
        super(id);

        setTitle(title);
        setVisibility(visibility);
    }

    public Challenge(ChallengeId id, String title, ChallengeVisibility visibility, String description) {
        this(id, title, visibility);

        setDescription(description);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Assert.hasText(title, "title must have text");

        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChallengeVisibility getVisibility() {
        return visibility;
    }

    protected void setVisibility(ChallengeVisibility visibility) {
        Assert.notNull(visibility, "visibility must not be null");

        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return String.format("Challenge[id=%s, title=%s, description=%s]", id.toString(), title, description);
    }
}

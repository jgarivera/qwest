package com.jgarivera.qwest.challenges.domain.model;

import com.jgarivera.qwest.shared.domain.model.BaseAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.util.Assert;

@Entity
@Table(name = "challenges")
public class Challenge extends BaseAggregateRoot<Challenge, ChallengeId> {

    private HostId hostId;
    private String title;
    private String description;
    private ChallengeVisibility visibility;

    /**
     * As required by JPA.
     */
    protected Challenge() {
    }

    public Challenge(ChallengeId id, HostId hostId, String title, ChallengeVisibility visibility) {
        super(id);

        setHostId(hostId);
        setTitle(title);
        setVisibility(visibility);
    }

    public Challenge(ChallengeId id, HostId hostId, String title, ChallengeVisibility visibility, String description) {
        this(id, hostId, title, visibility);

        setDescription(description);
    }

    public HostId getHostId() {
        return hostId;
    }

    public void setHostId(HostId hostId) {
        Assert.notNull(hostId, "host id must not be null");

        this.hostId = hostId;
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
        return String.format("Challenge[id=%s, hostId=%s, title=%s, description=%s]",
                id.toString(), hostId.toString(), title, description);
    }
}

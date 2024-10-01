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

    public static class Builder {

        private final ChallengeId id;
        private final HostId hostId;
        private final String title;

        private ChallengeVisibility visibility;
        private String description;

        public Builder(ChallengeId id, HostId hostId, String title) {
            this.id = id;
            this.hostId = hostId;
            this.title = title;

            visibility = ChallengeVisibility.PUBLIC;
        }

        public Builder visibility(ChallengeVisibility visibility) {
            this.visibility = visibility;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Challenge build() {
            return new Challenge(this);
        }
    }

    /**
     * As required by JPA.
     */
    protected Challenge() {
    }

    protected Challenge(Builder builder) {
        super(builder.id);

        setHostId(builder.hostId);
        setTitle(builder.title);
        setVisibility(builder.visibility);
        setDescription(builder.description);
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

package com.jgarivera.qwest.challenges.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.util.Assert;

@Entity
@Table(name = "challenges")
public class Challenge {

    @Id
    private Long id;
    private String name;
    private String description;

    public Challenge(Long id, String name) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(name, "name must not be null");

        this.id = id;
        this.name = name;
    }

    public Challenge(Long id, String name, String description) {
        this(id, name);
        this.description = description;
    }

    protected Challenge() {
        /* as required by JPA */
    }

    public Long getId() {
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

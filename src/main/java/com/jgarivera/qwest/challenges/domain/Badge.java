package com.jgarivera.qwest.challenges.domain;

import com.jgarivera.qwest.shared.BaseEntity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.util.Assert;

@Entity
@Table(name = "badges")
public class Badge extends BaseEntity<BadgeId> {

    @EmbeddedId
    private BadgeId id;
    private String title;
    private String description;
    private String imageUrl;

    public Badge(BadgeId id, String title, String description, String imageUrl) {
        Assert.notNull(id, "id must not be null");
        Assert.hasText(title, "title must not be null");

        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    /**
     * As required by JPA.
     */
    protected Badge() {
    }

    public BadgeId getId() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Badge other)) {
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

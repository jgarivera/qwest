package com.jgarivera.qwest.challenges.domain;

import com.jgarivera.qwest.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.util.Assert;

@Entity
@Table(name = "badges")
public class Badge extends BaseEntity<BadgeId> {

    private String title;
    private String description;
    private String imageUrl;

    /**
     * As required by JPA.
     */
    protected Badge() {
    }

    public Badge(BadgeId id, String title, String description, String imageUrl) {
        super(id);

        Assert.hasText(title, "title must have text");

        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
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
}

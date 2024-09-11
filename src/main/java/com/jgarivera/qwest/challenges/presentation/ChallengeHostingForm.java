package com.jgarivera.qwest.challenges.presentation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

class ChallengeHostingForm {

    @NotBlank(message = "Title must not be blank")
    private String title;
    private String description;
    @Min(value = 1, message = "Visibility must be a valid value")
    @Max(value = 2, message = "Visibility must be a valid value")
    private int visibility;

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

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
}

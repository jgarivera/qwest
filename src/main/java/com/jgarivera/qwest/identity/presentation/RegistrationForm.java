package com.jgarivera.qwest.identity.presentation;

import com.jgarivera.qwest.identity.domain.model.EmailAddress;
import com.jgarivera.qwest.identity.domain.model.Username;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

class RegistrationForm {

    @NotBlank(message = "First name must not be blank")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotBlank(message = "Email must not be blank")
    @Pattern(
            regexp = EmailAddress.VALID_EMAIL_ADDRESS_REGEX,
            message = "Email address must be valid"
    )
    private String email;

    @NotBlank(message = "Username must not be blank")
    @Pattern(
            regexp = Username.VALID_USERNAME_REGEX,
            message = "Username must only contain letters, numbers, underscores, and be between 8 to 30 characters"
    )
    private String username;

    @NotBlank(message = "Password must not be blank")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

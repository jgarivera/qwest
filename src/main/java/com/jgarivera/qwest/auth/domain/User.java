package com.jgarivera.qwest.auth.domain;

import com.jgarivera.qwest.shared.BaseEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity<UserId> implements UserDetails {

    @Embedded
    private PersonalName name;
    private EmailAddress email;
    private Username username;
    private String password;

    @Transient
    private List<GrantedAuthority> authorities;

    /**
     * As required by JPA.
     */
    protected User() {
    }

    public User(UserId id, PersonalName name, EmailAddress email, Username username, String password) {
        super(id);

        Assert.notNull(name, "name must not be null");
        Assert.notNull(email, "email must not be null");
        Assert.notNull(username, "username must not be null");
        Assert.hasText(password, "password must have text");

        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;

        authorities = new ArrayList<>();
    }

    public PersonalName getName() {
        return name;
    }

    public void setName(PersonalName name) {
        this.name = name;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public void setEmail(EmailAddress email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username.value();
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}

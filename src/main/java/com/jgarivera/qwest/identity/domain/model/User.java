package com.jgarivera.qwest.identity.domain.model;

import com.jgarivera.qwest.shared.domain.model.BaseAggregateRoot;
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
public class User extends BaseAggregateRoot<User, UserId> implements UserDetails {

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

    protected User(UserId id, PersonalName name, EmailAddress email, Username username, String password) {
        super(id);

        setName(name);
        setEmail(email);
        setUsername(username);
        setPassword(password);

        authorities = new ArrayList<>();
    }

    public static User create(UserId id, PersonalName name, EmailAddress email, Username username, String password) {
        var user = new User(id, name, email, username, password);

        user.registerEvent(new UserRegistered(user.getId()));

        return user;
    }

    public PersonalName getName() {
        return name;
    }

    public void setName(PersonalName name) {
        Assert.notNull(name, "name must not be null");

        this.name = name;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public void setEmail(EmailAddress email) {
        Assert.notNull(email, "email must not be null");

        this.email = email;
    }

    @Override
    public String getUsername() {
        return username.value();
    }

    public void setUsername(Username username) {
        Assert.notNull(username, "username must not be null");

        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Assert.hasText(password, "password must have text");

        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}

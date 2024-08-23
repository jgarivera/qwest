package com.jgarivera.qwest.identity.infrastructure;

import com.jgarivera.qwest.identity.application.UserService;
import com.jgarivera.qwest.identity.domain.EmailAddress;
import com.jgarivera.qwest.identity.domain.PersonalName;
import com.jgarivera.qwest.identity.domain.User;
import com.jgarivera.qwest.identity.domain.UserRepository;
import com.jgarivera.qwest.identity.domain.Username;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class JpaUserService implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    JpaUserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(PersonalName name, EmailAddress email, Username username, String password) {
        var user = User.create(repository.nextId(), name, email, username, passwordEncoder.encode(password));

        return repository.save(user);
    }
}

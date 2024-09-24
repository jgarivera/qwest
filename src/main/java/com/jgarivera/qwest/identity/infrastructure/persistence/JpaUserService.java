package com.jgarivera.qwest.identity.infrastructure.persistence;

import com.jgarivera.qwest.identity.application.UserService;
import com.jgarivera.qwest.identity.domain.model.EmailAddress;
import com.jgarivera.qwest.identity.domain.model.PersonalName;
import com.jgarivera.qwest.identity.domain.model.User;
import com.jgarivera.qwest.identity.domain.model.UserRepository;
import com.jgarivera.qwest.identity.domain.model.Username;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class JpaUserService implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    JpaUserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void register(String firstName, String middleName, String lastName,
                         String email, String username, String password) {
        var name = new PersonalName(firstName, middleName, lastName);
        var validEmail = new EmailAddress(email);
        var validUsername = new Username(username);
        String encodedPassword = passwordEncoder.encode(password);

        var user = User.create(repository.nextId(), name, validEmail, validUsername, encodedPassword);

        repository.save(user);
    }
}

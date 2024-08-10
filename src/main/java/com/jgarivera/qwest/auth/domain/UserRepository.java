package com.jgarivera.qwest.auth.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UserId> {

    User findByUsername(Username username);

    boolean existsByUsername(Username username);

    User findByEmail(EmailAddress email);
}

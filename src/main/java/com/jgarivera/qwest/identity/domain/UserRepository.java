package com.jgarivera.qwest.identity.domain;

import com.jgarivera.qwest.shared.BaseRepository;
import com.jgarivera.qwest.shared.UUIDFactory;

public interface UserRepository extends BaseRepository<User, UserId> {

    User findByUsername(Username username);

    boolean existsByUsername(Username username);

    User findByEmail(EmailAddress email);

    @Override
    default UserId nextId() {
        return new UserId(UUIDFactory.create());
    }
}

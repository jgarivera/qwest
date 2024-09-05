package com.jgarivera.qwest.identity.domain;

import com.github.f4b6a3.uuid.UuidCreator;
import com.jgarivera.qwest.shared.domain.BaseRepository;

public interface UserRepository extends BaseRepository<User, UserId> {

    User findByUsername(Username username);

    boolean existsByUsername(Username username);

    User findByEmail(EmailAddress email);

    @Override
    default UserId nextId() {
        return new UserId(UuidCreator.getTimeOrderedEpoch());
    }
}

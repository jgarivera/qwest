package com.jgarivera.qwest.challenges.domain;

import com.jgarivera.qwest.shared.BaseRepository;
import com.jgarivera.qwest.shared.UUIDFactory;

public interface BadgeRepository extends BaseRepository<Badge, BadgeId> {

    @Override
    default BadgeId nextId() {
        return new BadgeId(UUIDFactory.create());
    }
}

package com.jgarivera.qwest.challenges.domain;

import com.github.f4b6a3.uuid.UuidCreator;
import com.jgarivera.qwest.shared.domain.BaseRepository;

public interface BadgeRepository extends BaseRepository<Badge, BadgeId> {

    @Override
    default BadgeId nextId() {
        return new BadgeId(UuidCreator.getTimeOrderedEpoch());
    }
}

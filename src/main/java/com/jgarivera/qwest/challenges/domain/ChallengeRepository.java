package com.jgarivera.qwest.challenges.domain;

import com.jgarivera.qwest.shared.BaseRepository;
import com.jgarivera.qwest.shared.UUIDFactory;

public interface ChallengeRepository extends BaseRepository<Challenge, ChallengeId> {

    @Override
    default ChallengeId nextId() {
        return new ChallengeId(UUIDFactory.create());
    }
}

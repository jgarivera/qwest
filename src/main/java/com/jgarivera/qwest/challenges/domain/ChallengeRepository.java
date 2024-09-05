package com.jgarivera.qwest.challenges.domain;

import com.github.f4b6a3.uuid.UuidCreator;
import com.jgarivera.qwest.shared.domain.BaseRepository;

public interface ChallengeRepository extends BaseRepository<Challenge, ChallengeId> {

    @Override
    default ChallengeId nextId() {
        return new ChallengeId(UuidCreator.getTimeOrderedEpoch());
    }
}

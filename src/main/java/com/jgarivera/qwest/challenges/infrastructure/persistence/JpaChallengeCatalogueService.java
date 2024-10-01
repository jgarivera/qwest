package com.jgarivera.qwest.challenges.infrastructure.persistence;

import com.jgarivera.qwest.challenges.domain.model.Challenge;
import com.jgarivera.qwest.challenges.domain.model.ChallengeId;
import com.jgarivera.qwest.challenges.domain.model.ChallengeRepository;
import com.jgarivera.qwest.challenges.domain.model.ChallengeVisibility;
import com.jgarivera.qwest.challenges.application.ChallengeCatalogueService;
import com.jgarivera.qwest.challenges.domain.model.HostId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
class JpaChallengeCatalogueService implements ChallengeCatalogueService {

    private final ChallengeRepository repository;

    JpaChallengeCatalogueService(ChallengeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Challenge> getChallenges() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void hostChallenge(UUID hostId, String title, int visibilityId, String description) {
        ChallengeId id = repository.nextId();
        var visibility = ChallengeVisibility.fromId(visibilityId);
        var challenge = new Challenge.Builder(id, new HostId(hostId), title)
                .visibility(visibility)
                .description(description)
                .build();

        repository.save(challenge);
    }
}

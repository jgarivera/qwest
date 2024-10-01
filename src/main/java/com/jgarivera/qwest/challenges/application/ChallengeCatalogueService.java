package com.jgarivera.qwest.challenges.application;

import com.jgarivera.qwest.challenges.domain.model.Challenge;

import java.util.UUID;

public interface ChallengeCatalogueService {

    Iterable<Challenge> getChallenges();

    void hostChallenge(UUID hostId, String title, int visibility, String description);
}

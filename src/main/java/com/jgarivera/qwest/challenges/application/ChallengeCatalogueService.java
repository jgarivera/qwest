package com.jgarivera.qwest.challenges.application;

import com.jgarivera.qwest.challenges.domain.model.Challenge;

public interface ChallengeCatalogueService {

    Iterable<Challenge> getChallenges();

    void hostChallenge(String title, int visibility, String description);
}

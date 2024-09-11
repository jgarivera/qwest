package com.jgarivera.qwest.challenges.domain.model;

import org.jmolecules.ddd.types.ValueObject;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

public enum ChallengeVisibility implements ValueObject {
    PUBLIC(1),
    INVITE_ONLY(2);

    private static final Map<Integer, ChallengeVisibility> mapById =
            Stream.of(ChallengeVisibility.values()).collect(toMap(ChallengeVisibility::getId, identity()));

    public static ChallengeVisibility fromId(int id) {
        return Optional.ofNullable(mapById.get(id))
                .orElseThrow(UnsupportedOperationException::new);
    }

    private final int id;

    ChallengeVisibility(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

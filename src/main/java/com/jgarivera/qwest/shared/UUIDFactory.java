package com.jgarivera.qwest.shared;

import com.github.f4b6a3.uuid.UuidCreator;

import java.util.UUID;

public class UUIDFactory {

    public static UUID create() {
        return UuidCreator.getTimeOrderedEpoch();
    }
}

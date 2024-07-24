package com.jgarivera.qwest.shared;

import java.util.UUID;

@FunctionalInterface
public interface UUIDFactory {

    UUID create();
}

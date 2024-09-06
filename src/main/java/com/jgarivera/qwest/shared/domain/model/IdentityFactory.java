package com.jgarivera.qwest.shared.domain.model;

import org.jmolecules.ddd.types.Identifier;

public interface IdentityFactory<ID extends Identifier> {

    ID nextId();
}

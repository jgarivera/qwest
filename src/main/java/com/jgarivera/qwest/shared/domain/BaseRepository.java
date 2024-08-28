package com.jgarivera.qwest.shared.domain;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends AggregateRoot<T, ID>, ID extends Identifier>
        extends CrudRepository<T, ID>, IdentityFactory<ID> {
}

package com.jgarivera.qwest.auth.presentation;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

class RegistrationFormAggregator implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
        RegistrationForm form = new RegistrationForm();

        form.setFirstName(accessor.getString(0));
        form.setMiddleName(accessor.getString(1));
        form.setLastName(accessor.getString(2));
        form.setEmail(accessor.getString(3));
        form.setUsername(accessor.getString(4));
        form.setPassword(accessor.getString(5));

        return form;
    }
}

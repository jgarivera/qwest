package com.jgarivera.qwest.challenges.presentation;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

class ChallengeHostingFormAggregator implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
        var form = new ChallengeHostingForm();

        form.setTitle(accessor.getString(0));
        form.setDescription(accessor.getString(1));
        form.setVisibility(accessor.getInteger(2));

        return form;
    }
}

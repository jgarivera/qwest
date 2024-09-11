package com.jgarivera.qwest.challenges.infrastructure.persistence;

import com.jgarivera.qwest.challenges.domain.model.ChallengeVisibility;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
class ChallengeVisibilityConverter implements AttributeConverter<ChallengeVisibility, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ChallengeVisibility attribute) {
        return attribute.getId();
    }

    @Override
    public ChallengeVisibility convertToEntityAttribute(Integer dbData) {
        return ChallengeVisibility.fromId(dbData);
    }
}

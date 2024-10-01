package com.jgarivera.qwest.challenges.infrastructure.persistence;

import com.jgarivera.qwest.challenges.domain.model.HostId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
class HostIdConverter implements AttributeConverter<HostId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(HostId attribute) {
        return attribute.id();
    }

    @Override
    public HostId convertToEntityAttribute(UUID dbData) {
        return new HostId(dbData);
    }
}

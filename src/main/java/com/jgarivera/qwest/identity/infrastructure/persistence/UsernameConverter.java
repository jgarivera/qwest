package com.jgarivera.qwest.identity.infrastructure.persistence;

import com.jgarivera.qwest.identity.domain.model.Username;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
class UsernameConverter implements AttributeConverter<Username, String> {

    @Override
    public String convertToDatabaseColumn(Username attribute) {
        return attribute.value();
    }

    @Override
    public Username convertToEntityAttribute(String dbData) {
        return new Username(dbData);
    }
}

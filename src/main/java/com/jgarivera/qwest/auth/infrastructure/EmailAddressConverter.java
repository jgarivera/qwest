package com.jgarivera.qwest.auth.infrastructure;

import com.jgarivera.qwest.auth.domain.EmailAddress;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
class EmailAddressConverter implements AttributeConverter<EmailAddress, String> {

    @Override
    public String convertToDatabaseColumn(EmailAddress attribute) {
        return attribute.value();
    }

    @Override
    public EmailAddress convertToEntityAttribute(String dbData) {
        return new EmailAddress(dbData);
    }
}

package com.taynahamaral.confectionery.domain.profile;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender != null ? gender.getDescription() : null;
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return dbData != null ? Gender.fromValue(dbData) : null;
    }
}

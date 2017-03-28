package com.vb.mafia.core.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {
    List<String> valueList = null;

    @Override
    public void initialize(EnumValidator enumValidator) {
        valueList = new ArrayList<>();
        Class<? extends Enum<?>> enumClass = enumValidator.enumClazz();

        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();

        for(@SuppressWarnings("rawtypes") Enum enumVal : enumValArr)
        {
            valueList.add(enumVal.name());
        }
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isEmpty(s))
        {
            return true;
        }
        else if(!valueList.contains(s))
        {
            return false;
        }
        return true;
    }
}

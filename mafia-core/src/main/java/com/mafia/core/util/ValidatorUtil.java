package com.mafia.core.util;

import com.mafia.core.exception.MafiaException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.lang.reflect.Constructor;
import java.util.Set;

public abstract class ValidatorUtil {

    public static <E> void checkParam(E obj, Class mafExceptionClass, Integer respCode, Class... validateGroup) throws Exception
    {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> set = validator.validate(obj, validateGroup);
        for(ConstraintViolation<Object> cv : set)
        {
            Constructor constructor = mafExceptionClass.getConstructor(int.class, String.class);
            MafiaException mafException = (MafiaException) constructor.newInstance(respCode, cv.getMessage());
            throw mafException;
        }
    }

    public static <E> void checkParam(E obj, Class mafExceptionClass, Integer respCode) throws Exception
    {
        checkParam(obj, mafExceptionClass, respCode, Default.class);
    }

}

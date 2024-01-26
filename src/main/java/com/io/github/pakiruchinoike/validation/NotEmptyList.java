package com.io.github.pakiruchinoike.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.io.github.pakiruchinoike.validation.constraintvalidator.NotEmptyListValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {
    
    String message() default "{list.empty}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

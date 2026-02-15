package com.taynahamaral.confectionery.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "Email is already registered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

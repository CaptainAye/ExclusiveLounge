package com.exclusivelounge.rental.validation.annotations;

import com.exclusivelounge.rental.validation.validators.IntYearValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IntYearValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidYear {
    String message() default "It is not valid year number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };

}

package com.cainiaoshixi.validation.constraint;

import com.cainiaoshixi.validation.validator.EnterpriseMailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = EnterpriseMailValidator.class)
@Documented
@Repeatable(EnterpriseMail.List.class)
public @interface EnterpriseMail {

    String message() default "可能是非企业邮箱！";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        EnterpriseMail[] value();
    }
}

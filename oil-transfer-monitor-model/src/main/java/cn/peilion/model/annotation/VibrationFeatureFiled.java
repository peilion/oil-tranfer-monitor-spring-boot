package cn.peilion.model.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author fpl11
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VibrationFeatureFiledValidator.class)
public @interface VibrationFeatureFiled {
    String message() default "字段不合法";


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};
}
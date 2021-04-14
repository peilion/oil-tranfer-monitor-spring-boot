package cn.peilion.model.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

/**
 * @author fpl11
 */
public class VibrationFeatureFiledValidator implements ConstraintValidator<VibrationFeatureFiled, Object> {

    private static final HashSet PROVIDED_FILED = new HashSet() {{
        add("rms");
        add("max");
        add("p2p");
        add("avg");
        add("var");
        add("kurtosis");
    }};

    @Override
    public void initialize(VibrationFeatureFiled vibrationFeatureFiled) {
    }


    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        for (String s : (String[]) o) {
            if (!PROVIDED_FILED.contains(s)) {
                return false;
            }
        }
        return true;
    }
}
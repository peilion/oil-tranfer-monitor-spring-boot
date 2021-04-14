package cn.peilion.model.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

/**
 * @author fpl11
 */
public class ElectricFeatureFiledValidator implements ConstraintValidator<ElectricFeatureFiled, Object> {

    private static final HashSet PROVIDED_FILED = new HashSet() {{
        add("urms");
        add("uthd");
        add("umax_current");
        add("umin_current");
        add("ufrequency");
        add("uamplitude");
        add("uinitial_phase");
        add("uharmonics");
        add("ufbrb");
        add("vrms");
        add("vthd");
        add("vmax_current");
        add("vmin_current");
        add("vfrequency");
        add("vamplitude");
        add("vinitial_phase");
        add("vharmonics");
        add("vfbrb");
        add("wrms");
        add("wthd");
        add("wmax_current");
        add("wmin_current");
        add("wfrequency");
        add("wamplitude");
        add("winitial_phase");
        add("wharmonics");
        add("wfbrb");
        add("n_rms");
        add("p_rms");
        add("z_rms");
        add("imbalance");
        add("health_indicator");

    }};

    @Override
    public void initialize(ElectricFeatureFiled electricFeatureFiled) {
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
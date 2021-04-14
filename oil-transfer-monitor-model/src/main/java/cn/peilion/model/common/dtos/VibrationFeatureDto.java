package cn.peilion.model.common.dtos;

import cn.peilion.model.annotation.VibrationFeatureFiled;
import lombok.Data;


/**
 * @author fpl11
 */
@Data
public class VibrationFeatureDto {

    Integer id;

    Boolean isLast;

    @VibrationFeatureFiled
    String[] fileds;

}

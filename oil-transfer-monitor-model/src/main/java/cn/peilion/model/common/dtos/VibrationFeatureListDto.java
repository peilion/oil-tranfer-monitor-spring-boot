package cn.peilion.model.common.dtos;

import cn.peilion.model.annotation.VibrationFeatureFiled;
import lombok.Data;

import java.util.Date;

/**
 * @author fpl11
 */
@Data
public class VibrationFeatureListDto {
    String before;

    String after;

    Integer limit;

    @VibrationFeatureFiled
    String[] fileds;
}

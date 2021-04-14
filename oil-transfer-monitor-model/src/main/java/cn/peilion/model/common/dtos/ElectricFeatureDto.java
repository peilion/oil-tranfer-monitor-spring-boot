package cn.peilion.model.common.dtos;

import cn.peilion.model.annotation.ElectricFeatureFiled;
import lombok.Data;


/**
 * @author fpl11
 */
@Data
public class ElectricFeatureDto {

    Integer id;

    Boolean isLast;

    @ElectricFeatureFiled
    String[] fileds;

}

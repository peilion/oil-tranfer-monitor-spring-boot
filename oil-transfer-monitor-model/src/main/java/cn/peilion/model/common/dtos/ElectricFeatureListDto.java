package cn.peilion.model.common.dtos;

import cn.peilion.model.annotation.ElectricFeatureFiled;
import lombok.Data;

@Data
public class ElectricFeatureListDto {
    String before;

    String after;

    Integer limit;

    @ElectricFeatureFiled
    String[] fileds;
}

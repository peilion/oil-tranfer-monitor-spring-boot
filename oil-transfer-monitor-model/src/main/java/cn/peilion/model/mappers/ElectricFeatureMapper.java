package cn.peilion.model.mappers;

import cn.peilion.model.common.dtos.ElectricFeatureDto;
import cn.peilion.model.common.dtos.ElectricFeatureListDto;
import cn.peilion.model.pojos.electric.ElectricFeature;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ElectricFeatureMapper {
    ElectricFeature loadFeature(@Param("dto") ElectricFeatureDto dto,
                                @Param("stationId") Integer stationId,
                                @Param("innerStationId") Integer innerStationId
    );


    List<ElectricFeature> loadElectricFeatureList(@Param("dto") ElectricFeatureListDto dto,
                                                  @Param("stationId") Integer stationId,
                                                  @Param("innerStationId") Integer innerStationId);
}

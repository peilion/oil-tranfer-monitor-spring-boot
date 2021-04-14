package cn.peilion.model.mappers;


import cn.peilion.model.common.dtos.VibrationFeatureDto;
import cn.peilion.model.common.dtos.VibrationFeatureListDto;
import cn.peilion.model.pojos.vibration.VibrationFeature;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface VibrationFeatureMapper {
    VibrationFeature loadVibrationFeature(@Param("dto") VibrationFeatureDto dto,
                                          @Param("stationId") Integer stationId,
                                          @Param("innerStationId") Integer innerStationId
    );

    List<VibrationFeature> loadVibrationFeatureList(@Param("dto") VibrationFeatureListDto dto,
                                                    @Param("stationId") Integer stationId,
                                                    @Param("innerStationId") Integer innerStationId);
}

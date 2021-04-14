package cn.peilion.model.mappers;


import cn.peilion.model.common.dtos.DataDto;
import cn.peilion.model.pojos.vibration.VibrationData;
import org.apache.ibatis.annotations.Param;


public interface VibrationDataMapper {
    VibrationData loadVibrationData(@Param("dto") DataDto dto,
                                    @Param("stationId") Integer stationId,
                                    @Param("innerStationId") Integer innerStationId);

}

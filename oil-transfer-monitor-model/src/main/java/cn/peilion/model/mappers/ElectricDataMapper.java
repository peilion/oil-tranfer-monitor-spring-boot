package cn.peilion.model.mappers;

import cn.peilion.model.common.dtos.DataDto;
import cn.peilion.model.pojos.electric.ElectricData;
import org.apache.ibatis.annotations.Param;

public interface ElectricDataMapper {
    ElectricData loadElectricData(@Param("dto") DataDto dto,
                                  @Param("stationId") Integer stationId,
                                  @Param("innerStationId") Integer innerStationId);
}

package cn.peilion.model.mappers;

import cn.peilion.model.common.dtos.MeasurePointListDto;
import cn.peilion.model.pojos.measurePoint.MeasurePoint;
import cn.peilion.model.pojos.measurePoint.MeasurePointRelated;
import cn.peilion.model.pojos.measurePoint.MeasurePointStat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fpl11
 */
public interface MeasurePointMapper {

    List<MeasurePointRelated> loadMeasurePointList(@Param("dto") MeasurePointListDto dto);

    MeasurePoint loadMeasurePoint(@Param("measurePointId") Integer measurePointId);

    List<MeasurePointStat> loadMeasurePointStat(@Param("rule") String rule);
}

package cn.peilion.model.common.dtos;

import lombok.Data;

@Data
public class MeasurePointListDto {
    Integer limit;
    Boolean brief;
    Integer type;
    Integer stationId;
    Integer assetId;
}

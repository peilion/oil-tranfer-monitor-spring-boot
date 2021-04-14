package cn.peilion.model.common.dtos;

import lombok.Data;

@Data
public class AssetListDto {
    Integer skip;
    Integer limit;
    Boolean bool;
    Integer type;
    Integer level;
    String stationName;
    Integer stationId;
}

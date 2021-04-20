package cn.peilion.model.common.dtos;

import lombok.Data;

@Data
public class MSETDto {
    String before;
    String after;
    Integer nearBy;
    Integer limit;
}

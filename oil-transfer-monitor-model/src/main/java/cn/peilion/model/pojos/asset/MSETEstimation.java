package cn.peilion.model.pojos.asset;

import lombok.Data;

@Data
public class MSETEstimation {
    private Float[] raw;
    private Float[] est;
    private String[] label;
}

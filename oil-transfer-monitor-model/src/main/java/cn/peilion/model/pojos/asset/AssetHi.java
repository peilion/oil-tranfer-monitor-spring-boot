package cn.peilion.model.pojos.asset;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetHi {

    private long id;
    private Date time;
    private double healthIndicator;
    private double similarity;
    private double threshold;
    private String est;
    private long dataId;

}

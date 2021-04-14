package cn.peilion.model.pojos.asset;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetDetail {

    private long id;
    private String name;
    private String sn;
    private Date lrTime;
    private Date crTime;
    private Date mdTime;
    private Date stTime;
    private long assetType;
    private String memo;
    private double healthIndicator;
    private long statu;
    private long repairs;
    private String stationName;
    private String pipeLineName;
    private String branchName;
    private String regionName;
    private Boolean isDomestic;
    private Integer oilType;
    private double designOutput;
}

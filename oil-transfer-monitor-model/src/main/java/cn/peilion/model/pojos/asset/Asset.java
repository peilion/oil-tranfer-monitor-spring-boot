package cn.peilion.model.pojos.asset;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author fpl11
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Asset {

    private long id;
    private String name;
    private String sn;
    private Date lrTime;
    private Date crTime;
    private Date mdTime;
    private Date stTime;
    private long assetType;
    private long assetLevel;
    private String memo;
    private double healthIndicator;
    private long statu;
    private long parentId;
    private long manufacturerId;
    private long stationId;
    private long adminId;
    private long repairs;
    private long mpConfiguration;

}

package cn.peilion.model.pojos.asset;

import lombok.Data;

import java.util.Date;

/**
 * @author fpl11
 */
@Data
public class AssetCard {

    private long id;
    private String name;
    private String sn;
    private Date stTime;
    private double healthIndicator;
    private long statu;
    private long repairs;
    private long mpConfiguration;
    private String stationName;
    private Boolean isDomesitc;
    private Integer oilType;
    private double designOutput;
}

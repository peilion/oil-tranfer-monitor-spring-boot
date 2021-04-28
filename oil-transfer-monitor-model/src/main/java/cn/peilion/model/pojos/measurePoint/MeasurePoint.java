package cn.peilion.model.pojos.measurePoint;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author fpl11
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeasurePoint {

    private Integer id;
    private String name;
    private Date crTime;
    private Date mdTime;
    private Integer type;
    private Integer sampleInterval;
    private Integer sampleFreq;
    private Integer assetId;
    private Integer stationId;
    private Integer innerStationId;
    private Integer statu;
    private double healthIndicator;
    private Integer lastDiagId;
    private String direction;
    private String position;
    private double sampleSensitive;


}

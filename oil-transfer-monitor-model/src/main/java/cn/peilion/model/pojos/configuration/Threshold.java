package cn.peilion.model.pojos.configuration;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
public class Threshold {

    private long id;
    private String mpPattern;
    private HashMap diagThreshold;
    private Date mdTime;

}

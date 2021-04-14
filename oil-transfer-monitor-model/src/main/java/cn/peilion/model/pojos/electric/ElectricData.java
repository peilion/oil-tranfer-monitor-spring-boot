package cn.peilion.model.pojos.electric;


import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Data
public class ElectricData {

    private long id;
    private Date time;
    private float[] ucur;
    private float[] vcur;
    private float[] wcur;
    private float[] uvolt;
    private float[] vvolt;
    private float[] wvolt;
}

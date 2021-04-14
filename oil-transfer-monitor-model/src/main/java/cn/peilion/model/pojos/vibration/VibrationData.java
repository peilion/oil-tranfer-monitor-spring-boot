package cn.peilion.model.pojos.vibration;

import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Data
public class VibrationData {

    private long id;
    private Date time;
    private double rms;
    private float[] ima;

}

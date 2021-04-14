package cn.peilion.model.pojos.electric;


import lombok.Data;

import java.util.Date;

@Data
public class ElectricFeature {

    private long id;
    private Date time;
    private double urms;
    private double uthd;
    private float[] uharmonics;
    private double umaxCurrent;
    private double uminCurrent;
    private float[] ufbrb;
    private double ufrequency;
    private double uamplitude;
    private double uinitialPhase;
    private double vrms;
    private double vthd;
    private float[] vharmonics;
    private double vmaxCurrent;
    private double vminCurrent;
    private float[] vfbrb;
    private double vfrequency;
    private double vamplitude;
    private double vinitialPhase;
    private double wrms;
    private double wthd;
    private float[] wharmonics;
    private double wmaxCurrent;
    private double wminCurrent;
    private float[] wfbrb;
    private double wfrequency;
    private double wamplitude;
    private double winitialPhase;
    private double nRms;
    private double pRms;
    private double zRms;
    private double imbalance;
    private double healthIndicator;
    private long dataId;

}

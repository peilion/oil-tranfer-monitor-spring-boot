package cn.peilion.model.pojos.vibration;

import java.util.Date;

public class VibrationFeature {

    private long id;
    private Date time;
    private double rms;
    private double max;
    private double p2P;
    private double avg;
    private double var;
    private double kurtosis;
    private double fr;
    private double frAmp;
    private double thd;
    private double bpfi;
    private double bpfo;
    private double bsf;
    private double ftf;
    private double sideband;
    private double healthIndicator;
    private long dataId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public double getRms() {
        return rms;
    }

    public void setRms(double rms) {
        this.rms = rms;
    }


    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }


    public double getP2P() {
        return p2P;
    }

    public void setP2P(double p2P) {
        this.p2P = p2P;
    }


    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }


    public double getVar() {
        return var;
    }

    public void setVar(double var) {
        this.var = var;
    }


    public double getKurtosis() {
        return kurtosis;
    }

    public void setKurtosis(double kurtosis) {
        this.kurtosis = kurtosis;
    }


    public double getFr() {
        return fr;
    }

    public void setFr(double fr) {
        this.fr = fr;
    }


    public double getFrAmp() {
        return frAmp;
    }

    public void setFrAmp(double frAmp) {
        this.frAmp = frAmp;
    }


    public double getThd() {
        return thd;
    }

    public void setThd(double thd) {
        this.thd = thd;
    }


    public double getBpfi() {
        return bpfi;
    }

    public void setBpfi(double bpfi) {
        this.bpfi = bpfi;
    }


    public double getBpfo() {
        return bpfo;
    }

    public void setBpfo(double bpfo) {
        this.bpfo = bpfo;
    }


    public double getBsf() {
        return bsf;
    }

    public void setBsf(double bsf) {
        this.bsf = bsf;
    }


    public double getFtf() {
        return ftf;
    }

    public void setFtf(double ftf) {
        this.ftf = ftf;
    }


    public double getSideband() {
        return sideband;
    }

    public void setSideband(double sideband) {
        this.sideband = sideband;
    }


    public double getHealthIndicator() {
        return healthIndicator;
    }

    public void setHealthIndicator(double healthIndicator) {
        this.healthIndicator = healthIndicator;
    }


    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

}

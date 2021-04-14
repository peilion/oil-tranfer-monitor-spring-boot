package cn.peilion.model.pojos.asset;

import java.util.Date;


public class AssetHi {

    private long id;
    private Date time;
    private double healthIndicator;
    private double similarity;
    private double threshold;
    private String est;
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


    public double getHealthIndicator() {
        return healthIndicator;
    }

    public void setHealthIndicator(double healthIndicator) {
        this.healthIndicator = healthIndicator;
    }


    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }


    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }


    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }


    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

}

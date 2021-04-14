package cn.peilion.model.pojos.log;

import java.util.Date;

public class WarningLog {

  private long id;
  private Date crTime;
  private String description;
  private long severity;
  private long isRead;
  private long assetId;
  private long mpId;
  private long dataId;
  private double ibIndicator;
  private double maIndicator;
  private String bwIndicator;
  private double alIndicator;
  private double blIndicator;
  private double rbIndicator;
  private double sgIndicator;
  private double envKurtosis;
  private double velThd;
  private long thresholdId;
  private String marks;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public Date getCrTime() {
    return crTime;
  }

  public void setCrTime(Date crTime) {
    this.crTime = crTime;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public long getSeverity() {
    return severity;
  }

  public void setSeverity(long severity) {
    this.severity = severity;
  }


  public long getIsRead() {
    return isRead;
  }

  public void setIsRead(long isRead) {
    this.isRead = isRead;
  }


  public long getAssetId() {
    return assetId;
  }

  public void setAssetId(long assetId) {
    this.assetId = assetId;
  }


  public long getMpId() {
    return mpId;
  }

  public void setMpId(long mpId) {
    this.mpId = mpId;
  }


  public long getDataId() {
    return dataId;
  }

  public void setDataId(long dataId) {
    this.dataId = dataId;
  }


  public double getIbIndicator() {
    return ibIndicator;
  }

  public void setIbIndicator(double ibIndicator) {
    this.ibIndicator = ibIndicator;
  }


  public double getMaIndicator() {
    return maIndicator;
  }

  public void setMaIndicator(double maIndicator) {
    this.maIndicator = maIndicator;
  }


  public String getBwIndicator() {
    return bwIndicator;
  }

  public void setBwIndicator(String bwIndicator) {
    this.bwIndicator = bwIndicator;
  }


  public double getAlIndicator() {
    return alIndicator;
  }

  public void setAlIndicator(double alIndicator) {
    this.alIndicator = alIndicator;
  }


  public double getBlIndicator() {
    return blIndicator;
  }

  public void setBlIndicator(double blIndicator) {
    this.blIndicator = blIndicator;
  }


  public double getRbIndicator() {
    return rbIndicator;
  }

  public void setRbIndicator(double rbIndicator) {
    this.rbIndicator = rbIndicator;
  }


  public double getSgIndicator() {
    return sgIndicator;
  }

  public void setSgIndicator(double sgIndicator) {
    this.sgIndicator = sgIndicator;
  }


  public double getEnvKurtosis() {
    return envKurtosis;
  }

  public void setEnvKurtosis(double envKurtosis) {
    this.envKurtosis = envKurtosis;
  }


  public double getVelThd() {
    return velThd;
  }

  public void setVelThd(double velThd) {
    this.velThd = velThd;
  }


  public long getThresholdId() {
    return thresholdId;
  }

  public void setThresholdId(long thresholdId) {
    this.thresholdId = thresholdId;
  }


  public String getMarks() {
    return marks;
  }

  public void setMarks(String marks) {
    this.marks = marks;
  }

}

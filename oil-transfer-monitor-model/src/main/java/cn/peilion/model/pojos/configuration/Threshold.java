package cn.peilion.model.pojos.configuration;

import java.util.Date;

public class Threshold {

  private long id;
  private String mpPattern;
  private String diagThreshold;
  private Date mdTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getMpPattern() {
    return mpPattern;
  }

  public void setMpPattern(String mpPattern) {
    this.mpPattern = mpPattern;
  }


  public String getDiagThreshold() {
    return diagThreshold;
  }

  public void setDiagThreshold(String diagThreshold) {
    this.diagThreshold = diagThreshold;
  }


  public Date getMdTime() {
    return mdTime;
  }

  public void setMdTime(Date mdTime) {
    this.mdTime = mdTime;
  }

}

package cn.peilion.model.pojos.log;

import java.util.Date;

public class MaintenanceRecord {

  private long id;
  private Date crTime;
  private String description;
  private long assetId;
  private long statu;
  private Date mdTime;


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


  public long getAssetId() {
    return assetId;
  }

  public void setAssetId(long assetId) {
    this.assetId = assetId;
  }


  public long getStatu() {
    return statu;
  }

  public void setStatu(long statu) {
    this.statu = statu;
  }


  public Date getMdTime() {
    return mdTime;
  }

  public void setMdTime(Date mdTime) {
    this.mdTime = mdTime;
  }

}

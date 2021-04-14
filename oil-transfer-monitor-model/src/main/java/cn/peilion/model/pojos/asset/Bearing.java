package cn.peilion.model.pojos.asset;


public class Bearing {

  private long id;
  private long isDrivenEnd;
  private double bpfi;
  private double bpfo;
  private double bsf;
  private double ftf;
  private long assetId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getIsDrivenEnd() {
    return isDrivenEnd;
  }

  public void setIsDrivenEnd(long isDrivenEnd) {
    this.isDrivenEnd = isDrivenEnd;
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


  public long getAssetId() {
    return assetId;
  }

  public void setAssetId(long assetId) {
    this.assetId = assetId;
  }

}

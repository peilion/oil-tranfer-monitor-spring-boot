package cn.peilion.model.pojos.asset;

public class PumpUnit {

  private long id;
  private long oilType;
  private long isDomestic;
  private double designOutput;
  private long assetId;
  private long pipelineId;
  private String msetModelPath;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getOilType() {
    return oilType;
  }

  public void setOilType(long oilType) {
    this.oilType = oilType;
  }


  public long getIsDomestic() {
    return isDomestic;
  }

  public void setIsDomestic(long isDomestic) {
    this.isDomestic = isDomestic;
  }


  public double getDesignOutput() {
    return designOutput;
  }

  public void setDesignOutput(double designOutput) {
    this.designOutput = designOutput;
  }


  public long getAssetId() {
    return assetId;
  }

  public void setAssetId(long assetId) {
    this.assetId = assetId;
  }


  public long getPipelineId() {
    return pipelineId;
  }

  public void setPipelineId(long pipelineId) {
    this.pipelineId = pipelineId;
  }


  public String getMsetModelPath() {
    return msetModelPath;
  }

  public void setMsetModelPath(String msetModelPath) {
    this.msetModelPath = msetModelPath;
  }

}

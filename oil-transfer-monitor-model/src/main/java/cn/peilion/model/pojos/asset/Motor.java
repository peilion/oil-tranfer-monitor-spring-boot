package cn.peilion.model.pojos.asset;

public class Motor {

  private long id;
  private long phaseNumber;
  private long polePairsNumber;
  private long turnNumber;
  private double ratedVoltage;
  private double ratedSpeed;
  private long assetId;
  private long statu;
  private double healthIndicator;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getPhaseNumber() {
    return phaseNumber;
  }

  public void setPhaseNumber(long phaseNumber) {
    this.phaseNumber = phaseNumber;
  }


  public long getPolePairsNumber() {
    return polePairsNumber;
  }

  public void setPolePairsNumber(long polePairsNumber) {
    this.polePairsNumber = polePairsNumber;
  }


  public long getTurnNumber() {
    return turnNumber;
  }

  public void setTurnNumber(long turnNumber) {
    this.turnNumber = turnNumber;
  }


  public double getRatedVoltage() {
    return ratedVoltage;
  }

  public void setRatedVoltage(double ratedVoltage) {
    this.ratedVoltage = ratedVoltage;
  }


  public double getRatedSpeed() {
    return ratedSpeed;
  }

  public void setRatedSpeed(double ratedSpeed) {
    this.ratedSpeed = ratedSpeed;
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


  public double getHealthIndicator() {
    return healthIndicator;
  }

  public void setHealthIndicator(double healthIndicator) {
    this.healthIndicator = healthIndicator;
  }

}

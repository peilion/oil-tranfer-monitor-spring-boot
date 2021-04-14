package cn.peilion.model.pojos.asset;

/**
 * @author fpl11
 */
public class Stator {

  private long id;
  private double length;
  private double outerDiameter;
  private double innerDiameter;
  private long slotNumber;
  private long assetId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }


  public double getOuterDiameter() {
    return outerDiameter;
  }

  public void setOuterDiameter(double outerDiameter) {
    this.outerDiameter = outerDiameter;
  }


  public double getInnerDiameter() {
    return innerDiameter;
  }

  public void setInnerDiameter(double innerDiameter) {
    this.innerDiameter = innerDiameter;
  }


  public long getSlotNumber() {
    return slotNumber;
  }

  public void setSlotNumber(long slotNumber) {
    this.slotNumber = slotNumber;
  }


  public long getAssetId() {
    return assetId;
  }

  public void setAssetId(long assetId) {
    this.assetId = assetId;
  }

}

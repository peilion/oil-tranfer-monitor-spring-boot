package cn.peilion.model.pojos.asset;

public class Pump {

    private long id;
    private double flow;
    private double workPressure;
    private long bladeNumber;
    private long stageNumber;
    private long assetId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }


    public double getWorkPressure() {
        return workPressure;
    }

    public void setWorkPressure(double workPressure) {
        this.workPressure = workPressure;
    }


    public long getBladeNumber() {
        return bladeNumber;
    }

    public void setBladeNumber(long bladeNumber) {
        this.bladeNumber = bladeNumber;
    }


    public long getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(long stageNumber) {
        this.stageNumber = stageNumber;
    }


    public long getAssetId() {
        return assetId;
    }

    public void setAssetId(long assetId) {
        this.assetId = assetId;
    }

}

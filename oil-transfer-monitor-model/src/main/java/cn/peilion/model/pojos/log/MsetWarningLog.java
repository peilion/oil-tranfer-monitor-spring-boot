package cn.peilion.model.pojos.log;

import java.util.Date;

public class MsetWarningLog {

    private long id;
    private Date crTime;
    private Date mdTime;
    private String description;
    private long assetId;
    private long reporterId;


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


    public Date getMdTime() {
        return mdTime;
    }

    public void setMdTime(Date mdTime) {
        this.mdTime = mdTime;
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


    public long getReporterId() {
        return reporterId;
    }

    public void setReporterId(long reporterId) {
        this.reporterId = reporterId;
    }

}

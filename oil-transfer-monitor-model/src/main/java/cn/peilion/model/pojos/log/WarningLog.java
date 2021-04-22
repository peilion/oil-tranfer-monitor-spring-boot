package cn.peilion.model.pojos.log;

import cn.peilion.model.pojos.asset.Asset;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
public class WarningLog {

  private long id;
  private Date crTime;
  private HashMap description;
  private long severity;
  private long isRead;
  private long assetId;
  private long mpId;
  private long dataId;
  private double ibIndicator;
  private double maIndicator;
  private float[] bwIndicator;
  private double alIndicator;
  private double blIndicator;
  private double rbIndicator;
  private double sgIndicator;
  private double envKurtosis;
  private double velThd;
  private long thresholdId;
  private HashMap marks;
  private HashMap thres;
  private Asset asset;
}

package cn.peilion.model.pojos.log;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WarningLogTableItem {
    private long id;
    private Date crTime;
    private String description;
    private long severity;
    private long isRead;
    private String mpName;
    private long dataId;
    private String warningType;
    private String assetName;
    private Integer mpConfiguration;
    private Integer assetId;
    private Integer mpId;
}

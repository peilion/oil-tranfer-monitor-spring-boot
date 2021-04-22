package cn.peilion.model.pojos.log;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetEvent {

    private Date crTime;
    private String description;
    private String eventType;

}

package cn.peilion.model.pojos.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MaintenanceSuggestion {

    private long id;
    private String faultPattern;
    private long severity;
    private String suggestion;

}

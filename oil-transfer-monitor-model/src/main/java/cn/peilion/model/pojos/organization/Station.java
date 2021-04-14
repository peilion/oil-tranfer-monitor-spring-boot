package cn.peilion.model.pojos.organization;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Station {

  private long id;
  private String name;
  private String location;
  private String memo;
  private String telephone;
  private long bcId;
  private long rcId;
  private long shardingDbId;
  private double longitude;
  private double latitude;
  private String weather;

}

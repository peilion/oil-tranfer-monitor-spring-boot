package cn.peilion.model.pojos.configuration;

public class MaintenanceSuggestion {

  private long id;
  private String faultPattern;
  private long severity;
  private String suggestion;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getFaultPattern() {
    return faultPattern;
  }

  public void setFaultPattern(String faultPattern) {
    this.faultPattern = faultPattern;
  }


  public long getSeverity() {
    return severity;
  }

  public void setSeverity(long severity) {
    this.severity = severity;
  }


  public String getSuggestion() {
    return suggestion;
  }

  public void setSuggestion(String suggestion) {
    this.suggestion = suggestion;
  }

}

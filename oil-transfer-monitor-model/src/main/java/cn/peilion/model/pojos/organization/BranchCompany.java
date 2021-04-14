package cn.peilion.model.pojos.organization;

public class BranchCompany {

  private long id;
  private String name;
  private String memo;
  private String telephone;
  private long rcId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }


  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }


  public long getRcId() {
    return rcId;
  }

  public void setRcId(long rcId) {
    this.rcId = rcId;
  }

}

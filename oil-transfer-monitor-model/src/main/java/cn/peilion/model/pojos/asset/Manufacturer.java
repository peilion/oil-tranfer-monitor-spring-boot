package cn.peilion.model.pojos.asset;

import java.util.Date;

public class Manufacturer {

    private long id;
    private String name;
    private String telephone;
    private Date crTime;
    private Date mdTime;


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


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

}

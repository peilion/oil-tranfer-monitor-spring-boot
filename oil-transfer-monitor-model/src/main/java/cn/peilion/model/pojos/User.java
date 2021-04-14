package cn.peilion.model.pojos;

import java.util.Date;

public class User {

    private Long id;
    private String name;
    private Date crTime;
    private Date mdTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

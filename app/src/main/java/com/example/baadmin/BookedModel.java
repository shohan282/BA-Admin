package com.example.baadmin;

public class BookedModel {

    String date,location,name,special,time,uName,cname,type;

    BookedModel() {}

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BookedModel(String date, String location, String name, String special, String time, String uName, String cname, String type) {
        this.date = date;
        this.location = location;
        this.name = name;
        this.special = special;
        this.time = time;
        this.uName = uName;
        this.cname = cname;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

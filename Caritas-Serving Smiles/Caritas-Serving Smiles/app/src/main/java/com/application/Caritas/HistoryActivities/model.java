package com.application.Caritas.HistoryActivities;

public class model {
    String name, type, description, userid, timestamp, fooditem, link;
    long expiry;

    public model() {

    }

    public model(String name, String type, String description, String userid, String timestamp, String fooditem, long expiry, String link) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.userid = userid;
        this.timestamp = timestamp;
        this.fooditem = fooditem;
        this.expiry = expiry;
        this.link = link;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFooditem() {
        return fooditem;
    }

    public long getExpiry() {
        return expiry;
    }

    public String getLink() {
        return link;
    }
}

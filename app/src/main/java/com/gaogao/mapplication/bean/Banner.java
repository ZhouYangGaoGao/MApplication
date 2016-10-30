package com.gaogao.mapplication.bean;

import java.io.Serializable;

/**
 * Created by zhengzai on 16/8/25.
 */
public class Banner implements Serializable {
   private String type;
   private String img;
   private String url;
   private String aid;
   private String sid;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

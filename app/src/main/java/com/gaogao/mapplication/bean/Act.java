package com.gaogao.mapplication.bean;

import java.io.Serializable;

/**
 * Created by zhengzai on 16/8/25.
 */
public class Act implements Serializable {
    private String name;
    private String img;
    private String price;
    private String actTip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getActTip() {
        return actTip;
    }

    public void setActTip(String actTip) {
        this.actTip = actTip;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(String isFavor) {
        this.isFavor = isFavor;
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public int getEt() {
        return et;
    }

    public void setEt(int et) {
        this.et = et;
    }

    public int getFan() {
        return fan;
    }

    public void setFan(int fan) {
        this.fan = fan;
    }

    private String site;
    private String aid;
    private String isFavor;
    private int st;
    private int et;
    private int fan;
}

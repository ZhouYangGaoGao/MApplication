package com.gaogao.mapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengzai on 16/8/25.
 */
public class Msg implements Serializable {
    List<Act> acts;
    List<Banner> banners;

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

}

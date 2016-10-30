package com.gaogao.mapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhengzai on 16/9/12.
 */
public class MPagerAdapter extends PagerAdapter {
    private List<View> views;
    public MPagerAdapter(List<View> views){
        this.views=views;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public int getCount() {
        return  views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

}

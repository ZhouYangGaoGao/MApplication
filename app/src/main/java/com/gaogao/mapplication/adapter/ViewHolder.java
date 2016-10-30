package com.gaogao.mapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

public class ViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private ViewGroup parent;
    private Context context;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.parent = parent;
        this.context = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public void changeLayout(int layoutId) {
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;

    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImgRes(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 为View设置背景图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setBgRes(int viewId, int drawableId) {
        View view = getView(viewId);
        view.setBackgroundResource(drawableId);
        return this;
    }

    /**
     * 显示隐藏view
     *
     * @param viewId
     * @param visibility
     * @return
     */
    public ViewHolder setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * 给view设置点击事件
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setClick(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setClick(View.OnClickListener listener) {
        mConvertView.setOnClickListener(listener);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImageByUrl(int viewId, String url) {
        ImageView img = getView(viewId);
        x.image().bind(img, url);
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

    public TextView setTypeFace(int viewId, Typeface tf) {
        TextView tv = getView(viewId);
        tv.setTypeface(tf);
        return tv;
    }

}
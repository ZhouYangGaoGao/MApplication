package com.gaogao.mapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaogao.mapplication.adapter.MAdapter;
import com.gaogao.mapplication.utils.MListener;
import com.gaogao.mapplication.utils.Request;
import com.gaogao.mapplication.utils.SelecteUtil;

import org.xutils.x;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/28 下午8:14
 * 用途 :fragment基础类
 */
public abstract class MFragment extends Fragment implements MListener {
    public MAdapter adapter;
    public SelecteUtil slu;
    public View v;
    private boolean injected = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }

        v = view;
        initView();
    }

    public View findViewById(int id) {
        return v.findViewById(id);
    }

    public void get(String url, String tag, String... parameter) {
        Request.get(url, tag, this, parameter);
    }

    public void post(String url, String tag, String... bodys) {
        Request.post(url, tag, this, bodys);
    }

    public abstract void initView();

    public void setText(int id, String text) {
        TextView tv = (TextView) v.findViewById(id);
        tv.setText(text);
    }

    public View getView(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    public void setImgUrl(int id, String url) {
        ImageView img = (ImageView) v.findViewById(id);
        setImgUrl(img, url);
    }

    public void setImgUrl(ImageView img, String url) {
        x.image().bind(img, url);
    }


    public void setImgRes(int id, int rid) {
        ImageView img = (ImageView) v.findViewById(id);
        img.setImageResource(rid);
    }

    @Override
    public void onSuccess(String tag, String value) {

    }

    @Override
    public void onFailure(String tag, String value) {

    }

    @Override
    public void onException(String tag, String value) {

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onFinish(String tag) {

    }

}

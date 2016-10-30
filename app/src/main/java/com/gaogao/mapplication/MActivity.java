package com.gaogao.mapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaogao.mapplication.adapter.MAdapter;
import com.gaogao.mapplication.utils.MListener;
import com.gaogao.mapplication.utils.Request;
import com.gaogao.mapplication.utils.SelecteUtil;

import org.xutils.x;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/28 下午8:13
 * 用途 :activity基础类
 */
public abstract class MActivity extends FragmentActivity implements MListener, View.OnClickListener {
    public MAdapter adapter;
    public SelecteUtil slu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }

    public void get(String url, String tag, String... parameter) {
        Request.get(url, tag, this, parameter);
    }

    public void post(String url, String tag, String... bodys) {
        Request.get(url, tag, this, bodys);
    }

    public abstract void initView();

    public void setText(int id, String text) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(text);
    }

    public View getView(int layoutId) {
        return View.inflate(this, layoutId, null);
    }

    public void setImgUrl(int id, String url) {
        ImageView img = (ImageView) findViewById(id);
        setImgUrl(img, url);
    }

    public void setImgUrl(ImageView img, String url) {
        x.image().bind(img, url);
    }


    public void setImgRes(int id, int rid) {
        ImageView img = (ImageView) findViewById(id);
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

    @Override
    public void onClick(View v) {

    }
}

package com.gaogao.mapplication;

import android.support.v4.app.Fragment;

import com.gaogao.mapplication.utils.MListener;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/28 下午8:14
 * 用途 :
 */
public abstract class MFragment extends Fragment implements MListener {
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

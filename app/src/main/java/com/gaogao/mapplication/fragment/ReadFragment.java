package com.gaogao.mapplication.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.gaogao.mapplication.MFragment;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.adapter.MAdapter;
import com.gaogao.mapplication.adapter.ViewHolder;
import com.gaogao.mapplication.bean.ReadBean;
import com.gaogao.mapplication.utils.Request;
import com.gaogao.mapplication.utils.Urls;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/31 下午6:21
 * 用途 :阅读 赞 评论
 */
@SuppressLint("ValidFragment")
public class ReadFragment extends MFragment {
    private PullToRefreshListView listView;
    private MAdapter<ReadBean.DataBean> adapter;
    private List<ReadBean.DataBean> data = new ArrayList<>();
    private String url;

    public ReadFragment(int type) {
        switch (type) {
            case 0:
                url = Urls.READ;
                break;
            case 1:
                url = Urls.VOTE;
                break;
            case 2:
                url = Urls.COMMENT;
                break;
        }
    }

    @Override
    public View setContenView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.listviewpull, null);
    }


    @Override
    public void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.listview_pull);
        adapter = new MAdapter<ReadBean.DataBean>(getContext(), data, R.layout.item_recommend) {
            @Override
            public void convert(ViewHolder h, ReadBean.DataBean i) {
                h.setVisibility(R.id.item_recommend_icon, View.GONE);
                h.setText(R.id.item_recommend_dsc, i.getTitle());
                h.setText(R.id.item_recommend_dsc2, i.getAuthor_name());
                h.setImageByUrl(R.id.item_recommend_img, i.getThumbnail());
                h.setText(R.id.item_recommend_name, i.getAuthor_name());
            }
        };
        listView.setAdapter(adapter);
        data.clear();
        getData();
    }

    @Override
    public void onSuccess(String tag, String value) {
        ReadBean bean = JSON.parseObject(value, ReadBean.class);
        List<ReadBean.DataBean> tlist = bean.getData();
        if (tlist != null && tlist.size() > 0) {
            data.addAll(tlist);
            adapter.notifyDataSetChanged();
        }

    }

    private void getData() {
        Request.get(url, "read", this);
    }
}

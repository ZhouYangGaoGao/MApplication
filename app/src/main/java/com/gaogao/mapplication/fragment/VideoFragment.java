package com.gaogao.mapplication.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.gaogao.mapplication.MFragment;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.adapter.MAdapter;
import com.gaogao.mapplication.adapter.ViewHolder;
import com.gaogao.mapplication.bean.VideoBean;
import com.gaogao.mapplication.utils.Request;
import com.gaogao.mapplication.utils.Urls;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/31 下午6:23
 * 用途 :视频
 */
public class VideoFragment extends MFragment {
    private PullToRefreshListView listView;
    private MAdapter<VideoBean.DataBean> adapter;
    private List<VideoBean.DataBean> data = new ArrayList<>();

    @Override
    public View setContenView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.listviewpull, null);
    }

    @Override
    public void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.listview_pull);
        adapter = new MAdapter<VideoBean.DataBean>(getContext(), data, R.layout.item_video) {
            @Override
            public void convert(ViewHolder h, VideoBean.DataBean i) {
                h.setImageByUrl(R.id.item_video_img, i.getImage());
                h.setText(R.id.item_video_b, i.getPlay_count_string() + "播放");
                h.setText(R.id.item_video_p, i.getComment_count() + "");
                h.setText(R.id.item_video_z, i.getVote_count() + "");
            }
        };
        listView.setAdapter(adapter);
        data.clear();
        getData();
    }


    @Override
    public void onSuccess(String tag, String value) {
        VideoBean bean = JSON.parseObject(value, VideoBean.class);
        List<VideoBean.DataBean> tlist = bean.getData();
        if (tlist != null && tlist.size() > 0) {
            data.addAll(tlist);
            adapter.notifyDataSetChanged();
        }
    }

    private void getData() {
        Request.get(Urls.VIDIO, "video", this);
    }

}

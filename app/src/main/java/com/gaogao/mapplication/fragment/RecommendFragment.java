package com.gaogao.mapplication.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.gaogao.mapplication.MFragment;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.adapter.MAdapter;
import com.gaogao.mapplication.adapter.ViewHolder;
import com.gaogao.mapplication.bean.RecommendBean;
import com.gaogao.mapplication.utils.Request;
import com.gaogao.mapplication.utils.SelecteUtil;
import com.gaogao.mapplication.utils.Urls;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/31 下午6:21
 * 用途 :推荐页
 */
public class RecommendFragment extends MFragment {
    private PullToRefreshListView listView;
    private MAdapter<RecommendBean.DataBean> adapter;
    private List<RecommendBean.DataBean> data = new ArrayList<>();
    private List<RecommendBean.TopStoriesBean> topBean;
    private List<View> pagers = new ArrayList<>();

    @Override
    public View setContenView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.listviewpull, null);
    }

    View heard;
    SelecteUtil slu;

    @Override
    public void initView() {
        heard = getView(R.layout.viewpage_auto);
        listView = (PullToRefreshListView) findViewById(R.id.listview_pull);
        adapter = new MAdapter<RecommendBean.DataBean>(getContext(), data, R.layout.item_recommend) {
            @Override
            public void convert(ViewHolder h, RecommendBean.DataBean i) {
                h.setText(R.id.item_recommend_dsc, i.getTitle());
                h.setText(R.id.item_recommend_dsc2, i.getSource_name() + "|" + i.getHit_count_string() + "阅读");
                h.setImageByUrl(R.id.item_recommend_img, i.getThumbnail());
                h.setImageByUrl(R.id.item_recommend_icon, i.getAuthor_avatar());
                h.setText(R.id.item_recommend_name, i.getAuthor_name() + "推荐");
            }
        };
        listView.getRefreshableView().addHeaderView(heard);
        listView.setAdapter(adapter);
        slu = new SelecteUtil(heard.findViewById(R.id.viewPager_auto), pagers, heard.findViewById(R.id.dot_layout));
        data.clear();
        pagers.clear();
        getData();
    }

    @Override
    public void onSuccess(String tag, String value) {
        RecommendBean bean = JSON.parseObject(value, RecommendBean.class);
        List<RecommendBean.DataBean> tlist = bean.getData();
        if (tlist != null && tlist.size() > 0) {
            data.addAll(tlist);
            adapter.notifyDataSetChanged();
        }
        topBean = bean.getTop_stories();
        topBean.add(0, topBean.get(topBean.size() - 1));
        topBean.add(topBean.size(), topBean.get(1));
        for (int i = 0; i < topBean.size(); i++) {
            View v = getView(R.layout.item_home_pager);
            TextView tv = (TextView) v.findViewById(R.id.item_home_pager_dsc);
            tv.setText(topBean.get(i).getTitle());
            ImageView img = (ImageView) v.findViewById(R.id.item_home_pager_img);
            setImgUrl(img, topBean.get(i).getImage());
            pagers.add(v);
        }
        slu.notifyDataSetChanged(pagers);
    }

    private void getData() {
        Request.get(Urls.HOME, "home", this);
    }
}

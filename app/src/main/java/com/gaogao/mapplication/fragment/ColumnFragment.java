package com.gaogao.mapplication.fragment;

import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.gaogao.mapplication.MFragment;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.activity.BaoZouActivity;
import com.gaogao.mapplication.adapter.MAdapter;
import com.gaogao.mapplication.adapter.ViewHolder;
import com.gaogao.mapplication.bean.ColumBean;
import com.gaogao.mapplication.utils.Request;
import com.gaogao.mapplication.utils.Urls;
import com.gaogao.mapplication.view.TopBar;
import com.gaogao.mapplication.view.TopListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/31 下午11:53
 * 用途 :栏目
 */
public class ColumnFragment extends MFragment implements PullToRefreshBase.OnRefreshListener2 {
    private PullToRefreshListView listView;
    private MAdapter<ColumBean.DataBean> adapter;
    private List<ColumBean.DataBean> data = new ArrayList<>();
    TopBar topBar;
    BaoZouActivity activity;
    private int page = 1;

    @Override
    public View setContenView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_column, null);
    }

    @Override
    public void initView() {
        activity = (BaoZouActivity) getActivity();
        topBar = (TopBar) findViewById(R.id.home_topbar);
        topBar.setOnTopListener(new TopListener() {
            @Override
            public void l1Click() {
                if (activity.drawer.isDrawerOpen(GravityCompat.START)) {
                    activity.drawer.closeDrawer(GravityCompat.START);
                } else {
                    activity.drawer.openDrawer(GravityCompat.START);
                }
            }

            @Override
            public void r1Click() {
                //右边 点击事件
            }
        });

        listView = (PullToRefreshListView) findViewById(R.id.listview_pull);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(this);
        adapter = new MAdapter<ColumBean.DataBean>(getContext(), data, R.layout.item_column) {
            @Override
            public void convert(ViewHolder h, ColumBean.DataBean i) {
                h.setImageByUrl(R.id.item_column_img, i.getImage());
                h.setText(R.id.item_column_dsc, i.getName());
                h.setText(R.id.item_column_dsc2, i.getSummary());
            }
        };
        listView.setAdapter(adapter);
        data.clear();
        getData();
    }

    @Override
    public void onSuccess(String tag, String value) {
        listView.onRefreshComplete();
        ColumBean bean = JSON.parseObject(value, ColumBean.class);
        List<ColumBean.DataBean> tlist = bean.getData();
        if (tlist != null && tlist.size() > 0) {
            data.addAll(tlist);
            adapter.notifyDataSetChanged();
        } else Toast.makeText(getContext(), "就这么多了！", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onException(String tag, String value) {
        listView.onRefreshComplete();
    }

    private void getData() {
        Request.get(Urls.COLUMN, "read", this, "page", page + "");
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        data.clear();
        getData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        getData();
    }
}

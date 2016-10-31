package com.gaogao.mapplication.fragment;

import android.support.v4.view.GravityCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.gaogao.mapplication.MFragment;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.activity.BaoZouActivity;
import com.gaogao.mapplication.adapter.MAdapter;
import com.gaogao.mapplication.adapter.ViewHolder;
import com.gaogao.mapplication.bean.ReadBean;
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
 * 由 周洋 创建于 2016/11/1 上午12:24
 * 用途 :
 */
public class SearchFragment extends MFragment implements PullToRefreshBase.OnRefreshListener2 {
    private PullToRefreshListView listView;
    private MAdapter<ReadBean.DataBean> adapter;
    private List<ReadBean.DataBean> data = new ArrayList<>();
    TopBar topBar;
    BaoZouActivity activity;
    private int page = 1;
    private EditText editText;
    private String keyWord = "";

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
        editText = (EditText) findViewById(R.id.search_editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                //点击搜索按钮事件
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //你可以先判断为不为空 再搜索
                    getData(keyWord = editText.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onSuccess(String tag, String value) {
        listView.onRefreshComplete();
        ReadBean bean = JSON.parseObject(value, ReadBean.class);
        List<ReadBean.DataBean> tlist = bean.getData();
        if (tlist != null && tlist.size() > 0) {
            data.addAll(tlist);
            adapter.notifyDataSetChanged();
        } else Toast.makeText(getContext(), "就这么多了！", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onException(String tag, String value) {
        listView.onRefreshComplete();
    }

    private void getData(String keyWord) {
        Request.post(Urls.COLUMN, "search", this, "keyword", keyWord, "page", page + "");
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        page = 1;
        data.clear();
        getData(keyWord);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page++;
        getData(keyWord);
    }
}

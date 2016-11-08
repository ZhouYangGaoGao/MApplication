package com.gaogao.mapplication.fragment;

import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.gaogao.mapplication.MFragment;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.activity.BaoZouActivity;
import com.gaogao.mapplication.view.SelectBar;
import com.gaogao.mapplication.view.TopBar;
import com.gaogao.mapplication.view.TopListener;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/31 下午5:55
 * 用途 :排行棒
 */
public class ChartsFragment extends MFragment {
    BaoZouActivity activity;
    SelectBar selectBar;
    TopBar topBar;

    @Override
    public View setContenView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_charts, null);
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
            public void rTClick() {
                //点击 今天选项。。。
            }
        });
        selectBar = (SelectBar) findViewById(R.id.home_selectbar);
        selectBar.withViewPager(findViewById(R.id.home_viewpager), getChildFragmentManager(), new ReadFragment(0), new ReadFragment(1), new ReadFragment(2));
    }
}

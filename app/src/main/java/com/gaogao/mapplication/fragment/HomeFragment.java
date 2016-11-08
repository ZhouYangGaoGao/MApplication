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
 * 用途 :首页
 */
public class HomeFragment extends MFragment {
    BaoZouActivity activity;
    SelectBar selectBar;
    TopBar topBar;

    @Override
    public View setContenView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_home, null);
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
        });
        selectBar = (SelectBar) findViewById(R.id.home_selectbar);
        selectBar.withViewPager(findViewById(R.id.home_viewpager), getChildFragmentManager(), new RecommendFragment(), new VideoFragment());
    }
}

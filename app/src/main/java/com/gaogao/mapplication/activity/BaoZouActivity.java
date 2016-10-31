package com.gaogao.mapplication.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.gaogao.mapplication.MActivity;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.fragment.ChartsFragment;
import com.gaogao.mapplication.fragment.HomeFragment;

import org.xutils.view.annotation.ContentView;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/31 下午5:25
 * 用途 :
 */
@ContentView(value = R.layout.activity_baozou)
public class BaoZouActivity extends MActivity implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawer;
    private FragmentManager manager;
    private HomeFragment homeFragment;
    private ChartsFragment chartsFragment;

    @Override
    public void initView() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragmentRoot, homeFragment = new HomeFragment(), homeFragment.getClass().getName()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_firstPage:
                manager.beginTransaction().replace(R.id.fragmentRoot, homeFragment).commitAllowingStateLoss();
                break;
            case R.id.nav_charts:
                manager.beginTransaction().replace(R.id.fragmentRoot, chartsFragment==null?new ChartsFragment():chartsFragment).commitAllowingStateLoss();
                break;
            case R.id.nav_channel:
                break;
            case R.id.nav_search:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

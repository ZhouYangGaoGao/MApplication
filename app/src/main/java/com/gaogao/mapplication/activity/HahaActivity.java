package com.gaogao.mapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.gaogao.mapplication.MActivity;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.fragment.ChartsFragment;
import com.gaogao.mapplication.fragment.ColumnFragment;
import com.gaogao.mapplication.fragment.HomeFragment;
import com.gaogao.mapplication.fragment.SearchFragment;

import org.xutils.view.annotation.ContentView;

/**
 * Created by YangYang on 16/11/4.
 */
@ContentView(value = R.layout.activity_baozou)
public class HahaActivity extends MActivity implements NavigationView.OnNavigationItemSelectedListener{
    public DrawerLayout drawer;
    private FragmentManager manager;
    private HomeFragment homeFragment;
    private ChartsFragment chartsFragment;
    private ColumnFragment columnFragment;
    private SearchFragment searchFragment;

    @Override
    public void initView() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        manager = getSupportFragmentManager();
//        manager.beginTransaction().add(R.id.fragmentRoot, homeFragment = new HomeFragment(), homeFragment.getClass().getName()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_firstPage:
                manager.beginTransaction().replace(R.id.fragmentRoot, homeFragment).commitAllowingStateLoss();
                break;
            case R.id.nav_charts:
                manager.beginTransaction().replace(R.id.fragmentRoot, chartsFragment == null ? new ChartsFragment() : chartsFragment).commitAllowingStateLoss();
                break;
            case R.id.nav_channel:
                manager.beginTransaction().replace(R.id.fragmentRoot, columnFragment == null ? new ColumnFragment() : columnFragment).commitAllowingStateLoss();
                break;
            case R.id.nav_search:
                manager.beginTransaction().replace(R.id.fragmentRoot, searchFragment == null ? new SearchFragment() : searchFragment).commitAllowingStateLoss();
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

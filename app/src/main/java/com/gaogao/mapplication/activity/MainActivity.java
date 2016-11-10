package com.gaogao.mapplication.activity;

import android.view.View;
import android.view.ViewGroup;

import com.gaogao.mapplication.MActivity;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.fragment.TextFragment;
import com.gaogao.mapplication.utils.L;
import com.gaogao.mapplication.utils.SelecteUtil;
import com.gaogao.mapplication.utils.T;
import com.gaogao.mapplication.view.SelectBar;
import com.gaogao.mapplication.view.TopBar;
import com.gaogao.mapplication.view.TopListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/31 下午5:25
 * 用途 :
 */
@ContentView(value = R.layout.activity_main)
public class MainActivity extends MActivity {
    @ViewInject(value = R.id.topbar1)
    private TopBar topBar1;
    @ViewInject(value = R.id.topbar2)
    private TopBar topBar2;
    @ViewInject(value = R.id.selectbar)
    private SelectBar selectbar;
    private int[] resIds = {R.drawable.shape_dot_ff4665, R.drawable.shape_dot_ffffff, R.drawable.shape_dot_ffac3f, R.drawable.shape_dot_red, R.drawable.shape_dot_51c4ff};
    List<String> strs = new ArrayList<>();
    @ViewInject(value = R.id.gft)
    private AVLoadingIndicatorView gft;
    @Override
    public void initView() {
        topBar1.setOnTopListener(new TopListener() {
            @Override
            public void l1Click() {
                gft.show();
            }

            @Override
            public void l2Click() {gft.hide();
            }

            @Override
            public void r1Click() {

            }

            @Override
            public void r2Click() {

            }

            @Override
            public void cClick() {

            }

            @Override
            public void lTClick() {
            }

            @Override
            public void rTClick() {
            }

            @Override
            public void tClick() {

            }
        });
        List<View> views = new ArrayList<>();
        for (int i = 0; i < resIds.length; i++) {
            View view = new View(this);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setBackgroundResource(resIds[i]);
            views.add(view);
            strs.add("");
        }
        slu = new SelecteUtil(findViewById(R.id.viewPager_auto), views, findViewById(R.id.dot_layout));
        selectbar.withViewPager(findViewById(R.id.viewpager), getSupportFragmentManager(), new TextFragment(resIds[0]), new TextFragment(resIds[1]), new TextFragment(resIds[2]), new TextFragment(resIds[3]), new TextFragment(resIds[4]));
    }


}

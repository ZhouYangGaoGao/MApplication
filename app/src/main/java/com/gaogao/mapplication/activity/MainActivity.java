package com.gaogao.mapplication.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.gaogao.mapplication.MActivity;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.adapter.MAdapter;
import com.gaogao.mapplication.adapter.ViewHolder;
import com.gaogao.mapplication.bean.Act;
import com.gaogao.mapplication.bean.Banner;
import com.gaogao.mapplication.bean.Data;
import com.gaogao.mapplication.bean.Msg;
import com.gaogao.mapplication.utils.DateUtils;
import com.gaogao.mapplication.utils.SelecteUtil;
import com.gaogao.mapplication.utils.Urls;
import com.gaogao.mapplication.view.SelectBar;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/28 下午11:11
 * 用途 :
 */
@ContentView(value = R.layout.activity_main)
public class MainActivity extends MActivity {
    @ViewInject(value = R.id.listview_pull)
    private PullToRefreshListView listView;
    @ViewInject(value = R.id.selectbar)
    private SelectBar selectBar;
    private List<Act> list = new ArrayList<>();
    private List<View> imgs = new ArrayList<>();


    @Override
    public void initView() {
        adapter = new MAdapter<Act>(this, list, R.layout.item_ticket) {
            @Override
            public void convert(ViewHolder h, Act i) {
                h.setText(R.id.item_ticket_time, DateUtils.formatTime((long) i.getSt())).setText(R.id.item_ticket_place, i.getSite()).setText(R.id.item_ticket_price, i.getPrice());
                h.setText(R.id.item_ticket_introduce, i.getActTip()).setText(R.id.item_ticket_like, i.getFan() + "").setText(R.id.item_ticket_title, i.getName()).setImageByUrl(R.id.item_ticket_img, i.getImg());
                if (i.getIsFavor().equals("0")) {
                    h.setImgRes(R.id.item_ticket_like_icon, R.drawable.shape_dot_51c4ff);
                } else {
                    h.setImgRes(R.id.item_ticket_like_icon, R.drawable.shape_dot_ffffff);
                }
            }
        };
        get(Urls.TICKETLIST, "mainActivity", "auth", Urls.AUTH, "city", "北京", "page", 1 + "", "actType", "", "selDate", "", "order", "1");
        View heard = getView(R.layout.viewpage_auto);
        listView.setAdapter(adapter);
        slu = new SelecteUtil(heard.findViewById(R.id.viewPager_auto), imgs, heard.findViewById(R.id.dot_layout));
        listView.getRefreshableView().addHeaderView(heard);
        selectBar.setOnselecte(new SelectBar.Onselecte() {
            @Override
            public void onselected(View v, int index) {
                Toast.makeText(MainActivity.this, "" + index, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSuccess(String tag, String value) {
        initData(value);
    }

    private void initData(String value) {
        Msg msg = JSON.parseObject(value, Data.class).getMsg();
        if (msg != null) {
            List<Act> tlist = msg.getActs();
            if (tlist != null && tlist.size() > 0) {
                list.addAll(tlist);
                list.addAll(tlist);
                list.addAll(tlist);
                list.addAll(tlist);
                list.addAll(tlist);
                list.addAll(tlist);
            }
            adapter.notifyDataSetChanged();
            List<Banner> banners = msg.getBanners();
            if (banners != null && banners.size() > 0) {
                banners.add(banners.size(), banners.get(0));
                banners.add(0, banners.get(banners.size() - 2));
                for (int i = 0; i < banners.size(); i++) {
                    ImageView img = new ImageView(this);
                    img.setScaleType(ImageView.ScaleType.FIT_XY);
                    imgs.add(img);
                    setImgUrl(img, banners.get(i).getImg());
                }
                slu.notifyDataSetChanged(imgs);
            }

        }
    }
}

package com.gaogao.mapplication.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaogao.mapplication.R;
import com.gaogao.mapplication.adapter.MPagerAdapter;
import com.gaogao.mapplication.view.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 16/9/19
 * 用途 : 无限循环的viewpager 和文字单选封装
 */
public class SelecteUtil implements View.OnClickListener, ViewPager.OnPageChangeListener {
    public final static int ONLY_TEXT = 0, TEXT_TEXT = 1, TEXT_LINE = 2, IMAGEVIEW = 3;
    private List<TextView> tvs = new ArrayList<>(), tvs2 = new ArrayList<>();
    private List<View> lines = new ArrayList<>(), pagerViews;// 下划线  viewpager要显示的view
    private List<ImageView> imgs = new ArrayList<>();//图片集合
    private ViewPager viewPager;
    private AutoScrollViewPager mAutoScrollViewPager;
    private LinearLayout dotLayout;
    private MPagerAdapter adapter;
    private View[] mViews;
    private List<Integer> imgIdsSelected, imgIdsUnSelected;

    public Onselecte onselecte;//选择回调
    private int cloSelected = 0xff51c4ff, cloUnSelected = 0xff616161;//选择时的颜色   未选择的颜色

    /**
     * 自动巡展的viewpager 封装了可添加只是图标和无限循环
     *
     * @param autoScrollViewPager viewpager
     * @param views               展示内容
     * @param dotLayout           指示图标的容器layout 一般传Linerlayout
     * @return
     */
    public SelecteUtil(View autoScrollViewPager, List<View> views, View dotLayout) {
        startAutoScrollViewPager(autoScrollViewPager, views, dotLayout);
    }

    /**
     * 根据下标更改文字颜色和下划线显示
     *
     * @param index
     */
    public void doSelecte(int index) {
        if (lines.size() != 0) {
            for (View line : lines) {
                line.setVisibility(View.INVISIBLE);
            }
            lines.get(index).setVisibility(View.VISIBLE);
        }
        if (tvs.size() > 0) {
            for (TextView tv : tvs) {
                tv.setTextColor(cloUnSelected);
            }
            tvs.get(index).setTextColor(cloSelected);
        }
        if (tvs2.size() != 0) {
            for (TextView tv : tvs2) {
                tv.setTextColor(cloUnSelected);
            }
            tvs2.get(index).setTextColor(cloSelected);
        }
        if (imgs.size() > 0) {
            for (int i = 0; i < imgs.size(); i++) {
                imgs.get(i).setImageResource(imgIdsUnSelected.get(i));
            }
            imgs.get(index).setImageResource(imgIdsSelected.get(index));
        }
        if (mViews != null)
            onselecte.onselected(mViews[index], index);
    }


    /**
     * 文字与viewpager联动
     *
     * @param viewpager 直接传viewpager或者findviewbyid都可以
     * @param fmg       fragment管理器
     * @param fragments 传入要联动的所有fragment
     */
    public SelecteUtil withViewPager(View viewpager, FragmentManager fmg, final Fragment... fragments) {
        this.viewPager = (ViewPager) viewpager;
        viewPager.setAdapter(new FragmentPagerAdapter(fmg) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });
        viewPager.addOnPageChangeListener(this);
        return this;
    }

    /**
     * 文字与viewpager联动
     *
     * @param viewpager 直接传viewpager或者findviewbyid都可以
     * @param fmg       fragment管理器
     * @param fragments 传入要联动的所有fragment
     */
    public SelecteUtil(View viewpager, FragmentManager fmg, final Fragment... fragments) {
        withViewPager(viewpager, fmg, fragments);
    }

    public void selectePage(int index) {
        viewPager.setCurrentItem(index);
    }

    /**
     * 文字和图标选择器
     *
     * @param type      选择器类型 ONLY_TEXT: 文本 TEXT_TEXT: 双文本 TEXT_LINE: 文本下划线
     * @param onselecte 选择监听
     * @param views     前一半传TextView 后一半传view(必须)
     */
    public SelecteUtil(int type, Onselecte onselecte, View... views) {
        setTextSelect(type, onselecte, views);
    }

    /**
     * 文字和图标选择器
     *
     * @param type      选择器类型 ONLY_TEXT: 文本 TEXT_TEXT: 双文本 TEXT_LINE: 文本下划线
     * @param onselecte 选择监听
     * @param views     前一半传TextView 后一半传view(必须)
     */
    public SelecteUtil setTextSelect(int type, Onselecte onselecte, View... views) {
        this.onselecte = onselecte;
        mViews = views;
        for (View v : views)
            v.setOnClickListener(this);

        switch (type) {
            case ONLY_TEXT:
                for (View tv : views)
                    tvs.add((TextView) tv);

                break;
            case IMAGEVIEW:
                for (View img : views)
                    imgs.add((ImageView) img);

                break;
            default:
                if (views.length % 2 == 0) {
                    for (int i = 0; i < views.length / 2; i++)
                        tvs.add((TextView) views[i]);

                    if (type == TEXT_TEXT) {
                        for (int i = views.length / 2; i < views.length; i++)
                            tvs2.add((TextView) views[i]);

                    } else {
                        for (int i = views.length / 2; i < views.length; i++)
                            lines.add(views[i]);

                    }
                } else {
                    L.e("参数必须为2的整数倍");
                }
                break;
        }
        return this;
    }


    /**
     * 自动巡展的viewpager 封装了可添加只是图标和无限循环
     *
     * @param autoScrollViewPager viewpager
     * @param views               展示内容
     * @param dotLayout           指示图标的容器layout 一般传Linerlayout
     * @return
     */
    public SelecteUtil startAutoScrollViewPager(View autoScrollViewPager, List<View> views, View dotLayout) {
        mAutoScrollViewPager = (AutoScrollViewPager) autoScrollViewPager;
        this.dotLayout = (LinearLayout) dotLayout;
        pagerViews = views;
        initDot(autoScrollViewPager.getContext());
        initViewpager();
        mAutoScrollViewPager.addOnPageChangeListener(this);
        mAutoScrollViewPager.setCurrentItem(1);
        return this;
    }

    public void notifyDataSetChanged(List<View> views) {
        pagerViews = views;
        if (mAutoScrollViewPager != null) {
            adapter.notifyDataSetChanged();
            mAutoScrollViewPager.setCurrentItem(1);
        }
        initDot(mAutoScrollViewPager.getContext());
    }

    /**
     * 初始化指示图标
     *
     * @param c 上下文
     */
    private void initDot(Context c) {
        if (pagerViews.size() > 3) {
            if (dotLayout != null) {
                dotLayout.setVisibility(View.VISIBLE);
                dotLayout.removeAllViews();
                int dotW = (int) (ScreenUtils.getScreenWidth(c) * 0.014);
                for (int i = 0; i < pagerViews.size() - 2; i++) {
                    ImageView img = new ImageView(c);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dotW, dotW);
                    lp.setMargins(dotW, 0, dotW, 0);
                    img.setScaleType(ImageView.ScaleType.FIT_XY);
                    img.setLayoutParams(lp);
                    if (i == 0) {
                        img.setImageResource(R.drawable.shape_dot_51c4ff);
                    } else {
                        img.setImageResource(R.drawable.shape_dot_ffffff);
                    }
                    dotLayout.addView(img);
                }
            }
        } else {
//            dotLayout.setVisibility(View.INVISIBLE);
            mAutoScrollViewPager.stopAutoScroll();
        }
    }

    public void stop() {
        mAutoScrollViewPager.stopAutoScroll();
    }

    public void start() {
        mAutoScrollViewPager.startAutoScroll();
    }

    private void initViewpager() {
        adapter = new MPagerAdapter(pagerViews);
        mAutoScrollViewPager.setAdapter(adapter);
        mAutoScrollViewPager.setOffscreenPageLimit(5);
        mAutoScrollViewPager.setOnPageChangeListener(this);
        mAutoScrollViewPager.setInterval(4000);// 设置播放间隔时间
        mAutoScrollViewPager.setAutoScrollDurationFactor(10);
        mAutoScrollViewPager.setCycle(true);// 设置是否循环
        mAutoScrollViewPager.startAutoScroll();
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < (imgs.size() > 0 ? imgs.size() : tvs.size()); i++) {
            if ((imgs.size() != 0 && v.getId() == imgs.get(i).getId()) || (tvs.size() != 0 && v.getId() == tvs.get(i).getId()) || (tvs2.size() != 0 && v.getId() == tvs2.get(i).getId()) || (lines.size() != 0 && v.getId() == lines.get(i).getId())) {
                if (viewPager != null) {
                    viewPager.setCurrentItem(i);
                } else {
                    doSelecte(i);
                }
            }
        }
    }

    //设置选的后的文字颜色
    public SelecteUtil setSelectedClo(int cloSelected) {
        this.cloSelected = cloSelected;
        return this;
    }

    //设置未选择文字颜色
    public SelecteUtil setUnSelectedClo(int cloUnSelected) {
        this.cloUnSelected = cloUnSelected;
        return this;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (viewPager != null) {
            doSelecte(position);
        } else {
            if (pagerViews.size() > 1) { //多于1，才会循环跳转
                if (position < 1) { //首位之前，跳转到末尾（N）
                    position = pagerViews.size() - 2;
                    mAutoScrollViewPager.setCurrentItem(position, false);
                } else if (position > pagerViews.size() - 2) { //末位之后，跳转到首位（1）
                    mAutoScrollViewPager.setCurrentItem(1, false); //false:不显示跳转过程的动画
                    position = 1;
                }
            }
            if (dotLayout != null) {
                for (int i = 0; i < dotLayout.getChildCount(); i++) {
                    ImageView img = (ImageView) dotLayout.getChildAt(i);
                    if (i == position - 1) {
                        img.setImageResource(R.drawable.shape_dot_51c4ff);
                    } else {
                        img.setImageResource(R.drawable.shape_dot_ffffff);
                    }
                }
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public AutoScrollViewPager getAVp() {
        return mAutoScrollViewPager;
    }


    public interface Onselecte {
        void onselected(View v, int index);
    }

    public void setImgResouce(int... ids) {
        imgIdsUnSelected = new ArrayList<>();
        imgIdsSelected = new ArrayList<>();
        if (ids.length % 2 == 0) {
            for (int i = 0; i < ids.length / 2; i++)
                imgIdsSelected.add(ids[i]);

            for (int i = ids.length / 2; i < ids.length; i++)
                imgIdsUnSelected.add(ids[i]);

        } else {
        }
    }
}

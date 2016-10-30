package com.gaogao.mapplication.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaogao.mapplication.R;
import com.gaogao.mapplication.utils.SelecteUtil;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/26 下午3:07
 * 用途 :通用topbar
 */
public class TopBar extends LinearLayout {
    private SelectBar selectBar;
    private boolean isFinish;
    private LayoutParams imglp, tvlp, ctvlp;
    private Context context;
    public ImageView L1, L2;
    public TextView LT;
    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        if (getBackground() == null)
            setBackgroundColor(0xff232323);
        Drawable l1, l2, r1, r2, c, selectTline;
        float ts, iw, selectts;
        int tc, selecttc, selecttcun;
        String t1, t2, lt, rt, ct;
        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.TopBar);
        t1 = t.getString(R.styleable.TopBar_T1);
        t2 = t.getString(R.styleable.TopBar_T2);
        selectTline = t.getDrawable(R.styleable.TopBar_SelectTLineSrc);
        selectts = t.getFloat(R.styleable.TopBar_SelectTsize, sp2px(14));
        selecttc = t.getColor(R.styleable.TopBar_SelectTcolor, 0xff51c4ff);
        selecttcun = t.getColor(R.styleable.TopBar_SelectTcolorUn, 0xff616161);
        l1 = t.getDrawable(R.styleable.TopBar_L1src);
        l2 = t.getDrawable(R.styleable.TopBar_L2src);
        c = t.getDrawable(R.styleable.TopBar_CSrc);
        r1 = t.getDrawable(R.styleable.TopBar_R1src);
        r2 = t.getDrawable(R.styleable.TopBar_R2src);
        lt = t.getString(R.styleable.TopBar_LT);
        rt = t.getString(R.styleable.TopBar_RT);
        ct = t.getString(R.styleable.TopBar_CT);
        ts = t.getDimension(R.styleable.TopBar_Tsize, sp2px(14));
        iw = t.getDimension(R.styleable.TopBar_ImgWidth, dip2px(26));
        tc = t.getColor(R.styleable.TopBar_Tcolor, 0xffffffff);
        isFinish = t.getBoolean(R.styleable.TopBar_isFinish, false);
        t.recycle();

        LinearLayout letf, right, center;

        ctvlp = new LayoutParams(0, dip2px(44), 1);
        tvlp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imglp = new LayoutParams((int) iw, ViewGroup.LayoutParams.MATCH_PARENT);
        imglp.setMargins(dip2px(5), 0, dip2px(5), 0);
        letf = new LinearLayout(context);
        letf.setPadding(dip2px(8), 0, 0, 0);
        letf.setGravity(Gravity.START);
        right = new LinearLayout(context);
        right.setGravity(Gravity.END);
        right.setPadding(0, 0, dip2px(8), 0);
        center = new LinearLayout(context);
        center.setGravity(Gravity.CENTER);


        if (l1 != null) {
            L1 = new ImageView(context);
            L1.setImageDrawable(l1);
            letf.addView(L1, imglp);
            L1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.l1Click();
                    if (isFinish)
                        ((Activity) context).finish();

                }
            });
        }
        if (lt != null) {
            LT = new TextView(context);
            LT.setText(lt);
            LT.setGravity(Gravity.CENTER);
            LT.setTextSize(TypedValue.COMPLEX_UNIT_PX, ts);
            LT.setTextColor(tc);
            letf.addView(LT, tvlp);
            LT.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.lTClick();
                }
            });
        }
        if (l2 != null) {
            L2 = new ImageView(context);
            L2.setImageDrawable(l2);
            letf.addView(L2, imglp);
            L2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.l2Click();
                }
            });
        }

        if (r2 != null) {
            ImageView R2 = new ImageView(context);
            R2.setImageDrawable(r2);
            right.addView(R2, imglp);
            R2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.r2Click();
                }
            });
        }
        if (rt != null) {
            TextView RT = new TextView(context);
            RT.setText(rt);
            RT.setGravity(Gravity.CENTER);
            RT.setTextSize(TypedValue.COMPLEX_UNIT_PX, ts);
            right.addView(RT, tvlp);
            RT.setTextColor(tc);
            RT.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.rTClick();
                }
            });
        }
        if (r1 != null) {
            ImageView R1 = new ImageView(context);
            R1.setImageDrawable(r1);
            right.addView(R1, imglp);
            R1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.r1Click();
                }
            });
        }
        if (c != null) {
            ImageView C = new ImageView(context);
            C.setImageDrawable(c);
            center.addView(C, imglp);
            C.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.cClick();
                }
            });

        }


        if (ct != null) {
            TextView CT = new TextView(context);
            CT.setText(ct);
            CT.setGravity(Gravity.CENTER);
            CT.setTextColor(tc);
            CT.setTextSize(TypedValue.COMPLEX_UNIT_PX, ts);
            center.addView(CT, tvlp);
            CT.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.tClick();
                }
            });
        }
        addView(letf, ctvlp);
        if (t1 != null && t2 != null) {
            selectBar = new SelectBar(context);
            selectBar.addText(t1, selectts, selecttc, selectTline, true).addText(t2, selectts, selecttcun, selectTline, false).setSelect(selecttc, selecttcun);
            center.addView(selectBar, ctvlp);
        }
        addView(center, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dip2px(44)));
        addView(right, ctvlp);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    private TopListener listener;

    public void setOnTopListener(TopListener listener) {
        this.listener = listener;
    }

    public SelecteUtil withViewPager(View viewpager, FragmentManager fmg, Fragment... fragments) {
        return selectBar.withViewPager(viewpager, fmg, fragments);
    }

    public SelecteUtil setOnselecte(SelectBar.Onselecte listener) {
        return selectBar.setOnselecte(listener);
    }

    private int dip2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private int sp2px(float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}

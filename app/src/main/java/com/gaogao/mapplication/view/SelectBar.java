package com.gaogao.mapplication.view;

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

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/10/29 下午5:24
 * 用途 :
 */
public class SelectBar extends LinearLayout {
    int id = 0xabcdef;
    private SelecteUtil slu;
    private Context mContext;
    LayoutParams itemlp, textlp, linelp, llp;

    List<TextView> tvs = new ArrayList<>();
    List<ImageView> lines = new ArrayList<>();

    public SelectBar(Context context) {
        this(context, null);
    }

    public SelectBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        if (getBackground() == null) {
            setBackgroundColor(0xff232323);
        }
        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.TopBar);
        String t1, t2, t3, t4, t5;
        Drawable line;
        float ts;
        int tc, tcUn;
        t1 = t.getString(R.styleable.TopBar_T1);
        t2 = t.getString(R.styleable.TopBar_T2);
        t3 = t.getString(R.styleable.TopBar_T3);
        t4 = t.getString(R.styleable.TopBar_T4);
        t5 = t.getString(R.styleable.TopBar_T5);
        line = t.getDrawable(R.styleable.TopBar_SelectTLineSrc);
        ts = t.getFloat(R.styleable.TopBar_SelectTsize, sp2px(14));
        tc = t.getColor(R.styleable.TopBar_SelectTcolor, 0xff51c4ff);
        tcUn = t.getColor(R.styleable.TopBar_SelectTcolorUn, 0xff616161);
        t.recycle();
        llp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        llp.setMargins(dip2px(5), 0, dip2px(5), 0);
        itemlp = new LayoutParams(0, sp2px(44), 1);
        textlp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textlp.setMargins(0, sp2px(4), 0, sp2px(4));
        linelp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(2));

        addText(t1, ts, tc, line, true);
        addText(t2, ts, tcUn, line, false);
        addText(t3, ts, tcUn, line, false);
        addText(t4, ts, tcUn, line, false);
        addText(t5, ts, tcUn, line, false);
        setSelect(tc, tcUn);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    public void setSelect(int selectColor, int selectUnColor) {
        View[] views = new View[tvs.size() * 2];
        for (int i = 0; i < tvs.size(); i++) {
            views[i] = tvs.get(i);
        }
        for (int j = 0; j < lines.size(); j++) {
            views[j + tvs.size()] = lines.get(j);
        }
        slu = new SelecteUtil(SelecteUtil.TEXT_LINE, new SelecteUtil.Onselecte() {
            @Override
            public void onselected(View v, int index) {
                if (listener != null) {
                    listener.onselected(v, index);
                }
            }
        }, views).setSelectedClo(selectColor).setUnSelectedClo(selectUnColor);
    }

    private Onselecte listener;

    public SelecteUtil setOnselecte(Onselecte listener) {
        this.listener = listener;
        return slu;
    }

    public interface Onselecte {
        void onselected(View v, int index);
    }


    private int dip2px(float dipValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private int sp2px(float spValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public SelectBar addText(String text, float ts, int tc, Drawable line, boolean visiable) {
        if (text != null) {
            LinearLayout ll = new LinearLayout(mContext);
            LinearLayout item = new LinearLayout(mContext);
            item.setGravity(Gravity.CENTER);
            ll.setGravity(Gravity.CENTER);
            ll.setOrientation(VERTICAL);
            TextView tv = new TextView(mContext);
            tv.setText(text);
            tv.setId(++id);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, ts);
            tv.setTextColor(tc);
            ImageView img = new ImageView(mContext);
            img.setId(id++);
            if (line != null) {
                img.setImageDrawable(line);
            } else {
                img.setBackgroundColor(0xff51c4ff);
            }
            if (!visiable)
                img.setVisibility(INVISIBLE);
            ll.addView(tv, textlp);
            ll.addView(img, linelp);
            item.addView(ll, llp);
            addView(item, itemlp);
            tvs.add(tv);
            lines.add(img);
            return this;
        }
        return null;
    }

    public SelecteUtil withViewPager(View viewpager, FragmentManager fmg, final Fragment... fragments) {
        slu.withViewPager(viewpager, fmg, fragments);
        return slu;
    }

}

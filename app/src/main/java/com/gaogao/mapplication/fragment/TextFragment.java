package com.gaogao.mapplication.fragment;

import android.annotation.SuppressLint;
import android.widget.GridView;
import android.widget.TextView;

import com.gaogao.mapplication.MFragment;
import com.gaogao.mapplication.R;
import com.gaogao.mapplication.adapter.MAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件: ZhouYangGaoGao@163.com
 * 由 周洋 创建于 2016/11/9 下午3:38
 * 用途 :
 */
@SuppressLint("ValidFragment")
@ContentView(value = R.layout.fragment_text)
public class TextFragment extends MFragment {
    private int resid;
    @ViewInject(value = R.id.textview)
    private TextView textView;
    @ViewInject(value = R.id.recyclview)
    private GridView recyclerView;
    private static final String[] INDICATORS = new String[]{
            "BallPulseIndicator",
            "BallGridPulseIndicator",
            "BallClipRotateIndicator",
            "BallClipRotatePulseIndicator",
            "SquareSpinIndicator",
            "BallClipRotateMultipleIndicator",
            "BallPulseRiseIndicator",
            "BallRotateIndicator",
            "CubeTransitionIndicator",
            "BallZigZagIndicator",
            "BallZigZagDeflectIndicator",
            "BallTrianglePathIndicator",
            "BallScaleIndicator",
            "LineScaleIndicator",
            "LineScalePartyIndicator",
            "BallScaleMultipleIndicator",
            "BallPulseSyncIndicator",
            "BallBeatIndicator",
            "LineScalePulseOutIndicator",
            "LineScalePulseOutRapidIndicator",
            "BallScaleRippleIndicator",
            "BallScaleRippleMultipleIndicator",
            "BallSpinFadeLoaderIndicator",
            "LineSpinFadeLoaderIndicator",
            "TriangleSkewSpinIndicator",
            "PacmanIndicator",
            "BallGridBeatIndicator",
            "SemiCircleSpinIndicator",
    };

    public TextFragment(int resid) {
        this.resid = resid;
    }

    @Override
    public void initView() {
        if (resid == R.drawable.shape_dot_ff4665) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < INDICATORS.length; i++) {
                list.add(INDICATORS[i]);
            }
            recyclerView.setAdapter(new MAdapter<String>(getActivity(), list, R.layout.item_recyclerview) {
                @Override
                public void convert(MAdapter.ViewHolder h, String i) {
                    AVLoadingIndicatorView v = h.getView(R.id.AVLoadingIndicatorView);
                    v.setIndicator(i);
                }
            });
        }
    }


}

package com.example.myvalueadapter.fragment;

import android.util.Log;
import android.view.View;

import com.example.myvalueadapter.R;
import com.example.myvalueadapter.base.BaseFragment;

/**
 * Created by hasee on 2017/8/13.
 */

public class TradeStageFragment extends BaseFragment {
    public static TradeStageFragment fragment;

    public static TradeStageFragment newInstance() {
        if (fragment == null) {
            fragment = new TradeStageFragment();
        }
        return fragment;
    }

    @Override
    protected void lazyLoad() {
        Log.d("加载", "2");
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_game_center;
    }

    @Override
    protected void initView(View view) {

    }
}

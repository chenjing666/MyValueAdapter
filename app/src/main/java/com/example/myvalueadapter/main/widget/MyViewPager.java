package com.example.myvalueadapter.main.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Rickey on 2017/3/21.
 */

public class MyViewPager extends ViewPager {

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v != this && v instanceof NoScrollViewPager) {
            if (((NoScrollViewPager) v).getNoScroll()) {
                return false;
            }
        }
        return super.canScroll(v, checkV, dx, x, y);
    }
}

package com.example.myvalueadapter.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Holiday on 2017/2/22.
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mContextView = inflater.inflate(bindLayout(), container, false);
        initView(mContextView);
        return mContextView;
    }

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart("Fragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd("Fragment");
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {


    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    /**
     * [绑定布局]
     */
    protected abstract int bindLayout();

    /**
     * [初始化控件]
     */
    protected abstract void initView(final View view);
}

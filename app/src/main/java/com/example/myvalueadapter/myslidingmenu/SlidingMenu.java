package com.example.myvalueadapter.myslidingmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * 仿QQ侧滑
 * Created by Administrator on 2017/8/14 0014.
 */

public class SlidingMenu extends ViewGroup {
    private Scroller mScroller;
    private int mMenuRightPadding;
    private int heightPixels;
    private int widthPixels;
    private int mMenuWidth;
    private View mMenu;
    private View mContent;
    private int mLastY;
    private int mLastX;
    private boolean isOpen;
    private int mLastYIntercept;
    private int mLastXIntercept;

    public SlidingMenu(Context context) {
        this(context, null, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        //获取屏幕的宽高
        widthPixels = metrics.widthPixels;
        heightPixels = metrics.heightPixels;
        //初始化mMenuRightPadding，作为侧边view到屏幕右边的距离，converToDp方法将100转换为100dp
        mMenuRightPadding = converToDp(context, 100);
//        初始化一个Scroller
        mScroller = new Scroller(context);
    }

    /**
     * 然后重写computeScroll方法，这个方法是保证Scroller自动滑动的必须方法
     */
    @Override
    public void computeScroll() {
        //动画是否已经结束
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) ev.getX() - mLastXIntercept;
                int deltaY = (int) ev.getY() - mLastYIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {//左右活动
                    intercept = true;
                } else {//上下滑动
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }

        mLastX = mLastXIntercept = x;
        mLastY = mLastYIntercept = y;

        return intercept;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //侧滑view要放置的位置，左上右下分别是menu的负宽度，因为要超出屏幕的左边，而上边跟右边都是0，下边就是屏幕的高度值，因为要占满全屏，如果不需要，可以改动
        mMenu.layout(-mMenuWidth, 0, 0, heightPixels);
        //content区域位置，屏幕的中间
        mContent.layout(0, 0, widthPixels, heightPixels);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取SlidingMenu的第一个子孩子控件，即我们的侧边栏view
        mMenu = this.getChildAt(0);
        //获取SlidingMenu的第二个子孩子控件，即我们的内容区域
        mContent = this.getChildAt(1);
        //计算menu要使用的宽度为屏幕宽度减去menu要距离屏幕右侧的距离
        mMenuWidth = widthPixels - mMenuRightPadding;
        //设置menu的宽度
        mMenu.getLayoutParams().width = mMenuWidth;
        //设置content的宽度
        mContent.getLayoutParams().width = widthPixels;
        //测量mMenu
        this.measureChild(mMenu, widthMeasureSpec, heightMeasureSpec);
        //测量content
        this.measureChild(mContent, widthMeasureSpec, heightMeasureSpec);
        //测量自己，自己的宽度为侧边栏已经内容区域的宽度总和，高度为全屏
        this.setMeasuredDimension(widthPixels + mMenuWidth, heightPixels);


    }

    /**
     * 转换px为pd
     *
     * @param context 上下文
     * @param px      要转换的px数值
     * @return 返回转换好的结果值
     */
    private int converToDp(Context context, int px) {
        int result = 0;
        result = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, context.getResources().getDisplayMetrics());
        return result;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            //手指按下
            case MotionEvent.ACTION_DOWN:
                //记录按下时的x跟y值
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            //手指移动
            case MotionEvent.ACTION_MOVE:
                //时刻捕捉移动的手指位置的x跟y值
                int currentX = (int) event.getX();
                int currentY = (int) event.getY();
                //计算出当前的x值偏移量
                int offsetX = currentX - mLastX;
                if (offsetX > 0) {//大于0代表着当前手指是往右滑的
                    //首先要判断是不是滑到了最右边（menu已经全部显示出来了），如果全部显示了，再继续往右边滑，会造成超出了menu
                    if (getScrollX() - offsetX <= -mMenuWidth) {
                        scrollTo(-mMenuWidth, 0);
                    } else {
                        scrollBy(-offsetX, 0);
                    }

                } else {
                    //否则是往左边滑动
                    if (getScrollX() + Math.abs(offsetX) >= 0) {
                        scrollTo(0, 0);
                    } else {
                        scrollBy(-offsetX, 0);
                    }

                }

                //移动过程中，修改记录每一次移动到的x跟y的位置
                mLastX = currentX;
                mLastY = currentY;
                break;
            //如果手指离开屏幕
            case MotionEvent.ACTION_UP:

                if (getScrollX() < -mMenuWidth / 2) {
                    //打开menu
                    //使用startScroll方法，第一个参数是X的起始坐标，第二个参数是Y的起始坐标，第三个参数是X方向偏移量（即要移动多少），第四个参数是Y方向偏移量，第五个参数是动画持续时间
                    mScroller.startScroll(getScrollX(), 0, -mMenuWidth - getScrollX(), 0, 200);
                    isOpen = true;
                    invalidate();
                } else {
                    //如果当前滚动到的X位置是大于侧边栏的宽度的一半的，说明是没达到打开menu的要求
                    mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0, 200);
                    isOpen = false;
                    invalidate();
                }
                break;
        }
        return true;
    }
}

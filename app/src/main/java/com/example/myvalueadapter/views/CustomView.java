package com.example.myvalueadapter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 可移动的view
 * 怎么对绘制的矩形进行操作，暂时不知道
 * Created by Administrator on 2017/8/14 0014.
 */

public class CustomView extends View {
    private static final int WIDTH = 300;

    private Rect rect = new Rect(0, 0, WIDTH, WIDTH);//绘制矩形的区域
    private int deltaX, deltaY;//点击位置和图形边界的偏移量
    private static Paint paint = new Paint();//画笔

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);//填充红色
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(rect, paint);//画矩形

//        paint.setTextSize(50);
//        String text = "自由移动";
//        // 获取文字的宽和高
//        paint.getTextBounds(text, 0, text.length(), rect);
//        float textWidth = rect.width();
//        float textHeight = rect.height();
//        // 绘制字符串
//        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2
//                + textHeight / 2, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!rect.contains(x, y)) {
                    return false;//没有在矩形上点击，不处理触摸消息
                }
                deltaX = x - rect.left;
                deltaY = y - rect.top;
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                Rect old = new Rect(rect);
                //更新矩形的位置
                rect.left = x - deltaX;
                rect.top = y - deltaY;
                rect.right = rect.left + WIDTH;
                rect.bottom = rect.top + WIDTH;
                old.union(rect);//要刷新的区域，求新矩形区域与旧矩形区域的并集
                invalidate(old);//出于效率考虑，设定脏区域，只进行局部刷新，不是刷新整个view
                break;
        }
        return true;//处理了触摸消息，消息不再传递
    }
}

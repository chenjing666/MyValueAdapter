package com.example.myvalueadapter.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.myvalueadapter.MainActivity;
import com.example.myvalueadapter.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.loginactivity)
    RelativeLayout loginactivity;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏(不管用)
//        getSupportActionBar().hide();//去掉标题栏
        setContentView(R.layout.activity_login);

        //沉浸式状态栏
//        //显示状态栏，Activity不全屏显示(恢复到有状态的正常情况)
//        loginactivity.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//        //隐藏状态栏，同时Activity会伸展全屏显示
//        loginactivity.setSystemUiVisibility(View.INVISIBLE);
//        //Activity全屏显示，且状态栏被隐藏覆盖掉。
//        loginactivity.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        //Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住
//        loginactivity.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        //同mRLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        loginactivity.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
//        //同mRLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        loginactivity.setSystemUiVisibility(View.SYSTEM_UI_LAYOUT_FLAGS);
//        //隐藏虚拟按键(导航栏)
//        loginactivity.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        //状态栏显示处于低能显示状态(low profile模式)，状态栏上一些图标显示会被隐藏。
//        loginactivity.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        //隐藏虚拟按键(导航栏)
        loginactivity.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        }).start();
    }
}
package com.example.myvalueadapter.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myvalueadapter.MainActivity;
import com.example.myvalueadapter.test.SlideActivity;
import com.example.myvalueadapter.R;
import com.example.myvalueadapter.common.EditTextClearTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.loginactivity)
    RelativeLayout loginactivity;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.phonenumber)
    EditText phonenumber;
    @BindView(R.id.del_phonenumber)
    ImageView delPhonenumber;
    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.del_password)
    ImageView delPassword;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.portrait)
    ImageView portrait;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.relativeLayoutaotu)
    RelativeLayout relativeLayoutaotu;
    @BindView(R.id.textView4)
    TextView textView4;

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
        EditTextClearTools.addclerListener(phonenumber, delPhonenumber);
        EditTextClearTools.addclerListener(password, delPassword);
    }

    @OnClick({R.id.btn_login, R.id.loginButton})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.btn_login:
                //隐藏虚拟按键(导航栏)
                loginactivity.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SystemClock.sleep(2000);
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                finish();
//            }
//        }).start();
                break;
        }
    }

}

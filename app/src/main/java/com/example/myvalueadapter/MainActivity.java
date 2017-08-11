package com.example.myvalueadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myvalueadapter.net.NetChangeObserver;
import com.example.myvalueadapter.net.NetStateReceiver;
import com.example.myvalueadapter.net.NetUtils;

public class MainActivity extends AppCompatActivity {
    private NetChangeObserver mNetChangeObserver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyNetMonitor();
    }


    private void MyNetMonitor() {
        mNetChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(NetUtils.NetType type) {//网络类型：CMWAP,WIFI
                Log.e("name", type.name());
                if (type.name().equals("WIFI")) {
                    Toast.makeText(MainActivity.this, "WIFI任性", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "使用数据网", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNetDisConnect() {
                Toast.makeText(MainActivity.this, "网络连接断开", Toast.LENGTH_SHORT).show();
            }
        };
        //开启广播去监听 网络 改变事件
        NetStateReceiver.registerObserver(mNetChangeObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
    }
}

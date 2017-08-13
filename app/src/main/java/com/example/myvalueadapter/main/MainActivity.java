package com.example.myvalueadapter.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.myvalueadapter.R;
import com.example.myvalueadapter.base.BaseFragment;
import com.example.myvalueadapter.fragment.GameCenterFragment;
import com.example.myvalueadapter.fragment.LevelingMainFragment;
import com.example.myvalueadapter.fragment.TradeStageFragment;
import com.example.myvalueadapter.main.widget.MyViewPager;
import com.example.myvalueadapter.net.NetChangeObserver;
import com.example.myvalueadapter.net.NetStateReceiver;
import com.example.myvalueadapter.net.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    MyViewPager viewPager;
    @BindView(R.id.nav)
    BottomNavigationBar nav;
    @BindView(R.id.img_add)
    ImageView imgAdd;
    private NetChangeObserver mNetChangeObserver = null;
    private FragmentManager fm = getSupportFragmentManager();
    private List<BaseFragment> fragments;

    private GameCenterFragment gameCenterFragment;//游戏中心
    private LevelingMainFragment levelingMainFragment;//代练订单(竞技厅)
    private TradeStageFragment tradeStageFragment;//交易厅

    private BottomNavigationItem nav1;//对应 游戏中心
    private BottomNavigationItem nav2;//对应 竞技厅
    private BottomNavigationItem nav3;//对应 发布
    private BottomNavigationItem nav4;//对应 交易厅
    private BottomNavigationItem nav5;//对应 个人中心

    private int lastSelectedNavIndex = 0;//记录上一次选中的nav，用于控制选择"我的"时，焦点不变

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MyNetMonitor();//网络检测
        initView();
        imgAdd.setBackgroundResource(R.drawable.img_add_blue);
    }

    private void initView() {
        initNav();
        setUpViewPager();
    }

    @OnClick({R.id.img_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_add:
                imgAdd.setBackgroundResource(R.drawable.img_add_orange);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgAdd.setBackgroundResource(R.drawable.img_add_blue);
                        //if (TextUtils.isEmpty(SPUserUtils.getString(AppConsts.SP_TOKEN))) {
                        //    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        //} else {
               //         ReleaseActivity.start(MainActivity.this);
                        //}
                    }
                }, 50);
                break;
        }
    }

    /**
     * 底部导航设置
     */
    private void initNav() {
        nav1 = new BottomNavigationItem(R.drawable.ic_nav_charge, getResources().getString(R.string.module_games));
        nav2 = new BottomNavigationItem(R.drawable.ic_nav_athletic_selected, getResources().getString(R.string.module_leveling));
        nav3 = new BottomNavigationItem(R.mipmap.ic_launcher, "发布"); //该ITEM为占位用，实际是被其他图片覆盖的
        nav4 = new BottomNavigationItem(R.drawable.ic_nav_trade_selected, getResources().getString(R.string.module_trade_stage));
        nav5 = new BottomNavigationItem(R.drawable.icon_user_center, getResources().getString(R.string.module_user_center));

        //个人中心 按钮设置
        nav5.setInActiveColorResource(R.color.color6b9fe5);

        nav.addItem(nav1).addItem(nav2).addItem(nav3).addItem(nav4).addItem(nav5)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setActiveColor(R.color.color6b9fe5)
                .initialise();

        nav.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        lastSelectedNavIndex = position;
                        viewPager.setCurrentItem(position);
                        break;
                    case 1:
                        lastSelectedNavIndex = position;
                        viewPager.setCurrentItem(position);
                        break;
                    case 2:
                        break;
                    case 3:
                        lastSelectedNavIndex = position;
                        viewPager.setCurrentItem(position - 1);
                        break;
                    case 4:
//                        if (TextUtils.isEmpty(SPUserUtils.getString(AppConsts.SP_TOKEN))) {
//                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                        } else {
//                            UserCenterActivity.start(MainActivity.this);//跳转到个人中心
//                        }
                        nav.selectTab(lastSelectedNavIndex);//焦点维持不变
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
    /**
     * viewPager设置
     */
    private void setUpViewPager() {
        fragments = new ArrayList<>();

        gameCenterFragment = GameCenterFragment.newInstance();
        levelingMainFragment = LevelingMainFragment.newInstance();
        tradeStageFragment = TradeStageFragment.newInstance();

        fragments.add(gameCenterFragment);//0 游戏中心
        fragments.add(levelingMainFragment);//1 竞技厅
        fragments.add(tradeStageFragment);//2 交易厅

        MyPagerAdapter adapter = new MyPagerAdapter(fm, fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        nav.selectTab(position, false);
                        break;
                    case 1:
                        nav.selectTab(position, false);
                        break;
                    case 2:
                        nav.selectTab(position + 1, false);
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {

        List<BaseFragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
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

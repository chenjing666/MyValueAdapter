package com.example.myvalueadapter.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myvalueadapter.R;
import com.example.myvalueadapter.myslidingmenu.SlidingMenu;
import com.example.myvalueadapter.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySlideActivity extends AppCompatActivity {

    @BindView(R.id.menu_lv)
    ListView menuLv;
    @BindView(R.id.content_lv)
    ListView contentLv;
    @BindView(R.id.slidingmenu)
    SlidingMenu slidingmenu;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_slide);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.background_color));
        for (int i = 0; i <= 20; i++) {
            list.add("测试" + i);
        }
        menuLv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        contentLv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        contentLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MySlideActivity.this, "点击" + i, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

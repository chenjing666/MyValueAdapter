package com.example.myvalueadapter.test;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myvalueadapter.R;
import com.example.myvalueadapter.slidingmenu.ContentAdapter;
import com.example.myvalueadapter.slidingmenu.ContentModel;
import com.example.myvalueadapter.slidingmenu.NewsFragment;
import com.example.myvalueadapter.slidingmenu.SubscriptionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 侧拉菜单Demo
 *
 * @author chenjing
 */
public class SlideActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private RelativeLayout rightLayout;
    private List<ContentModel> list;
    private ContentAdapter adapter;
    private ImageView leftMenu, rightMenu;
    private ListView listView;
    private FragmentManager fm;
    private TextView textView_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        leftMenu = (ImageView) findViewById(R.id.leftmenu);
        rightMenu = (ImageView) findViewById(R.id.rightmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        rightLayout = (RelativeLayout) findViewById(R.id.right);
        listView = (ListView) findViewById(R.id.left_listview);
        textView_bar = (TextView) findViewById(R.id.title_bar2);
        fm = getSupportFragmentManager();
        //默认新闻
        FragmentTransaction bt = fm.beginTransaction();
        bt.replace(R.id.content, new NewsFragment());
        bt.commit();
        textView_bar.setText("新闻");

        initData();
        adapter = new ContentAdapter(this, list);
        listView.setAdapter(adapter);
        leftMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                FragmentTransaction bt = fm.beginTransaction();
                switch ((int) id) {
                    case 1:
                        bt.replace(R.id.content, new NewsFragment());
                        textView_bar.setText("新闻");
                        break;
                    case 2:
                        bt.replace(R.id.content, new SubscriptionFragment());
                        textView_bar.setText("subscription");
                        break;

                    default:
                        break;
                }
                bt.commit();
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        rightMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        rightLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }

    private void initData() {
        list = new ArrayList<ContentModel>();
        list.add(new ContentModel(R.drawable.doctoradvice2, "新闻", 1));
        list.add(new ContentModel(R.drawable.infusion_selected, "订阅", 2));
        list.add(new ContentModel(R.drawable.mypatient_selected, "图片", 3));
        list.add(new ContentModel(R.drawable.mywork_selected, "视频", 4));
        list.add(new ContentModel(R.drawable.nursingcareplan2, "跟帖", 5));
        list.add(new ContentModel(R.drawable.personal_selected, "投票", 6));
    }
}

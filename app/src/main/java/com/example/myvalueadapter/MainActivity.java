package com.example.myvalueadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myvalueadapter.common.AvatarLoadOptions;
import com.example.myvalueadapter.message.AppConsts;
import com.example.myvalueadapter.utils.GlideUtis;
import com.example.myvalueadapter.utils.PowerBarBg;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity {
    private String imageUrl = "http://macprovid.vo.llnwd.net/o43/hub/media/1090/6882/01_headline_Muse.jpg";
    private String imageUrl2 = "http://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png";
    private ImageView imageView;
    private boolean image = false;
    private GlideUtis glideUtis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glideUtis = new GlideUtis(this);
        PowerBarBg.setPowerBarBg(this, getResources().getColor(R.color.background_color));
        imageView = (ImageView) findViewById(R.id.iv);
        glideUtis.glideCircle(imageUrl, imageView, false);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!image) {
                    ImageLoader.getInstance().displayImage(imageUrl2, imageView, AvatarLoadOptions.build_item());
                    image = true;
                } else {
                    ImageLoader.getInstance().displayImage(imageUrl, imageView, AvatarLoadOptions.build_item());
                    image = false;
                }
            }
        });
    }
}

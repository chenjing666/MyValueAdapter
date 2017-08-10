package com.example.myvalueadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myvalueadapter.common.AvatarLoadOptions;
import com.example.myvalueadapter.common.CountDownProgressView;
import com.example.myvalueadapter.utils.GlideUtis;
import com.example.myvalueadapter.utils.PowerBarBg;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogoActivity extends AppCompatActivity {
    @BindView(R.id.mCountDownProgressView)
    CountDownProgressView mCountDownProgressView;
    @BindView(R.id.iv)
    ImageView imageView;
    private String imageUrl = "http://macprovid.vo.llnwd.net/o43/hub/media/1090/6882/01_headline_Muse.jpg";
    private String imageUrl2 = "http://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png";
    private boolean image = false;
    private GlideUtis glideUtis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        ButterKnife.bind(this);
        glideUtis = new GlideUtis(this);
        glideUtis.glideCircle(imageUrl, imageView, false);
        PowerBarBg.setPowerBarBg(this, getResources().getColor(R.color.background_color));
        imageView.setOnClickListener(listener);
        CountProgress();//倒计时
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv:
                    if (!image) {
                        ImageLoader.getInstance().displayImage(imageUrl2, imageView, AvatarLoadOptions.build_item());
                        image = true;
                    } else {
                        ImageLoader.getInstance().displayImage(imageUrl, imageView, AvatarLoadOptions.build_item());
                        image = false;
                    }
                    break;
                case R.id.mCountDownProgressView:
                    mCountDownProgressView.stop();
                    Toast.makeText(LogoActivity.this, "跳过", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogoActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };

    private void CountProgress() {
        mCountDownProgressView.setOnClickListener(listener);
        mCountDownProgressView.setText("跳");
        mCountDownProgressView.setTimeMillis(6000);
        mCountDownProgressView.start();
        mCountDownProgressView.setProgressListener(new CountDownProgressView.OnProgressListener() {
            @Override
            public void onProgress(int progress) {//progress是从大到小
                Log.e("progress:", progress + "");
                if (progress == 20) {
                    Toast.makeText(LogoActivity.this, "即将跳过", Toast.LENGTH_SHORT).show();
                } else if (progress == 1) {
                    startActivity(new Intent(LogoActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

}

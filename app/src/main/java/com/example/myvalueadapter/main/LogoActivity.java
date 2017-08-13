package com.example.myvalueadapter.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.example.myvalueadapter.R;
import com.example.myvalueadapter.common.AvatarLoadOptions;
import com.example.myvalueadapter.common.CountDownProgressView;
import com.example.myvalueadapter.user.LoginActivity;
import com.example.myvalueadapter.utils.GlideUtis;
import com.example.myvalueadapter.utils.StatusBarUtil;
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
        //LogUtils使用
        LogUtils.e("hhh");
        LogUtils.e("12%s3", "a", 0);
        LogUtils.d("12%s3%d45", "a", 0);
        double[][] doubles = {{1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33}};
        LogUtils.e(doubles);

        //利用glide加载图片
        glideUtis = new GlideUtis(this);
        glideUtis.glideCircle(imageUrl, imageView, false);
        //设置状态栏颜色
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent));
//        PowerBarBg.setPowerBarBg(this, getResources().getColor(R.color.background_color));

        //6.0之后权限的动态申请
        if (ContextCompat.checkSelfPermission(LogoActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LogoActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            Toast.makeText(LogoActivity.this, "已经给过权限", Toast.LENGTH_SHORT).show();
        }
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
                    if (!image) {//利用imageloader加载图片
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
                    startActivity(new Intent(LogoActivity.this, LoginActivity.class));
                    finish();
                    break;
            }
        }
    };

    private void CountProgress() {
        mCountDownProgressView.setOnClickListener(listener);
        mCountDownProgressView.setText("跳");
        mCountDownProgressView.setTimeMillis(20000);
        mCountDownProgressView.start();
        mCountDownProgressView.setProgressListener(new CountDownProgressView.OnProgressListener() {
            @Override
            public void onProgress(int progress) {//progress是从大到小
                Log.e("progress:", progress + "");
                if (progress == 20) {
                    Toast.makeText(LogoActivity.this, "即将跳过", Toast.LENGTH_SHORT).show();
                } else if (progress == 1) {
                    startActivity(new Intent(LogoActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(LogoActivity.this, "给权限了", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogoActivity.this, "没给权限啊", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}

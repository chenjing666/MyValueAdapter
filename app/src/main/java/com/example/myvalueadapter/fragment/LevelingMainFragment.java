package com.example.myvalueadapter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.example.myvalueadapter.R;
import com.example.myvalueadapter.base.BaseFragment;
import com.example.myvalueadapter.fragment.couponview.CouponImageView;
import com.example.myvalueadapter.fragment.couponview.CouponView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 优惠券格式view
 * Created by hasee on 2017/8/13.
 */

public class LevelingMainFragment extends BaseFragment {
    public static LevelingMainFragment fragment;
    @BindView(R.id.couponView)
    CouponView mCouponView;
    @BindView(R.id.semicircle_top)
    CheckBox mSemicircleTop;
    @BindView(R.id.semicircle_bottom)
    CheckBox mSemicircleBottom;
    @BindView(R.id.semicircle_left)
    CheckBox mSemicircleLeft;
    @BindView(R.id.semicircle_right)
    CheckBox mSemicircleRight;
    @BindView(R.id.dash_line_top)
    CheckBox mDashLineTop;
    @BindView(R.id.dash_line_bottom)
    CheckBox mDashLineBottom;
    @BindView(R.id.dash_line_left)
    CheckBox mDashLineLeft;
    @BindView(R.id.dash_line_right)
    CheckBox mDashLineRight;
    @BindView(R.id.sbSemicircleRadius)
    SeekBar mSbSemicircleRadius;
    @BindView(R.id.sbSemicircleCap)
    SeekBar mSbSemicircleCap;
    @BindView(R.id.sbDashLineLength)
    SeekBar mSbDashLineLength;
    @BindView(R.id.sbDashLineGap)
    SeekBar mSbDashLineGap;
    @BindView(R.id.sbDashLineHeight)
    SeekBar mSbDashLineHeight;
    @BindView(R.id.sbTopDashLineMargin)
    SeekBar mSbTopDashLineMargin;
    @BindView(R.id.sbBottomDashLineMargin)
    SeekBar mSbBottomDashLineMargin;
    @BindView(R.id.sbLeftDashLineMargin)
    SeekBar mSbLeftDashLineMargin;
    @BindView(R.id.sbRightDashLineMargin)
    SeekBar mSbRightDashLineMargin;
    Unbinder unbinder;
    @BindView(R.id.couponImageView)
    CouponImageView mCouponImageView;

    public static LevelingMainFragment newInstance() {
        if (fragment == null) {
            fragment = new LevelingMainFragment();
        }
        return fragment;
    }

    @Override
    protected void lazyLoad() {
        Log.d("加载", "2");
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_leveling_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initOnCheckedListener();
        initProgress();
        mCouponImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCouponImageView.setVisibility(View.GONE);
            }
        });
    }

    private void initOnCheckedListener() {
        mSemicircleTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setSemicircleTop(isChecked);
            }
        });
        mSemicircleBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setSemicircleBottom(isChecked);
            }
        });
        mSemicircleLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setSemicircleLeft(isChecked);
            }
        });
        mSemicircleRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setSemicircleRight(isChecked);
            }
        });

        mDashLineTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setDashLineTop(isChecked);
            }
        });
        mDashLineBottom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setDashLineBottom(isChecked);
            }
        });
        mDashLineLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setDashLineLeft(isChecked);
            }
        });
        mDashLineRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCouponView.setDashLineRight(isChecked);
            }
        });
    }

    private void initProgress() {
        mSbSemicircleRadius.setProgress((int) mCouponView.getSemicircleRadius());
        mSbSemicircleRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCouponView.setSemicircleRadius(dp2Px(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSbSemicircleCap.setProgress((int) mCouponView.getSemicircleGap());
        mSbSemicircleCap.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCouponView.setSemicircleGap(dp2Px(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSbDashLineLength.setProgress((int) mCouponView.getDashLineLength());
        mSbDashLineLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCouponView.setDashLineLength(dp2Px(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSbDashLineGap.setProgress((int) mCouponView.getDashLineGap());
        mSbDashLineGap.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCouponView.setDashLineGap(dp2Px(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSbDashLineHeight.setProgress((int) mCouponView.getDashLineHeight() * 10);
        mSbDashLineHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCouponView.setDashLineHeight(dp2Px(progress) / 10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSbTopDashLineMargin.setProgress((int) mCouponView.getDashLineMarginTop());
        mSbTopDashLineMargin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCouponView.setDashLineMarginTop(dp2Px(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mSbBottomDashLineMargin.setProgress((int) mCouponView.getDashLineMarginBottom());
        mSbBottomDashLineMargin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCouponView.setDashLineMarginBottom(dp2Px(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSbLeftDashLineMargin.setProgress((int) mCouponView.getDashLineMarginLeft());
        mSbLeftDashLineMargin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCouponView.setDashLineMarginLeft(dp2Px(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSbRightDashLineMargin.setProgress((int) mCouponView.getDashLineMarginRight());
        mSbRightDashLineMargin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCouponView.setDashLineMarginRight(dp2Px(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private int dp2Px(float dp) {
        return (int) (dp * getActivity().getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

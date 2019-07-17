package com.jupiter.androidstudy.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jupiter.androidstudy.R;


/**
 * 这个功能暂时没有实现
 */
public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView textView;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.vp_pic);
        textView = findViewById(R.id.tv_des);
        linearLayout = findViewById(R.id.ll_point_container);
    }
}

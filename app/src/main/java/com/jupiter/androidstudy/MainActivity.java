package com.jupiter.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jupiter.androidstudy.calculate.CalculateActivity;
import com.jupiter.androidstudy.circelprogress.CircleProgressActivity;
import com.jupiter.androidstudy.customizeProgressButton.CustomizeProgressButtonActivity;
import com.jupiter.androidstudy.differentdialog.DifferentDialogActivity;
import com.jupiter.androidstudy.recyclerviewdemo.RecyclerViewDemoActivity;
import com.jupiter.androidstudy.smsintersection.SMSIntersectionActivity;
import com.jupiter.androidstudy.sqlite.SQLiteActivity;
import com.jupiter.androidstudy.takephoto.TakePhotoActivity;
import com.jupiter.androidstudy.viewpager.ViewPagerActivity;
import com.jupiter.androidstudy.volley.VolleyActivity;

/**
 * Lomok注解的使用
 * 一、在Android Studio的中安装Lomok插件
 * Go to File > Settings > Plugins
 * Click on Browse repositories...
 * Search for Lombok Plugin
 * Click on Install plugin
 * Restart Android Studio
 * 二、在项目的中添加依赖
 * 在build.gradle(Mdoule:app)中添加
 * dependencies {
 *     compileOnly "org.projectlombok:lombok:1.18.8"
 *     annotationProcessor 'org.projectlombok:lombok:1.18.8'
 * }
 * 三、参考
 * Lomok官网指导: https://www.projectlombok.org/setup/android
 */

public class MainActivity extends AppCompatActivity {

    private Button btnTakePhoto;
    private Intent intent;
    private Button btnSqlite;
    private Button btnVolley;
    private Button btnSmsDemo;
    private Button btnProgress;
    private Button btnCircelProgress;
    private Button btnViewPageer;
    private Button button;
    private Button btnDifferentDialog;
    private Button btnRecyclerView;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btnTakePhoto = findViewById(R.id.btnTakephoto);
        btnSqlite = findViewById(R.id.btnSqlite);
        btnVolley = findViewById(R.id.btnVolley);
        btnSmsDemo = findViewById(R.id.btnSmsDemo);
        btnProgress = findViewById(R.id.btnProgress);
        btnCircelProgress = findViewById(R.id.btnCircleProgress);
        button = btnViewPageer = findViewById(R.id.btnViewPager);
        btnDifferentDialog = findViewById(R.id.btnDifferentDialog);
        btnRecyclerView = findViewById(R.id.btnRecyclerView);
        btnCalculate = findViewById(R.id.btnCalculate);
    }

    public void onMainClick(View view) {
        if(view == btnTakePhoto){
            intent = new Intent(this, TakePhotoActivity.class);;
        }else if(view == btnSqlite){
            intent = new Intent(this, SQLiteActivity.class);
        }else if(view == btnVolley){
            intent = new Intent(this, VolleyActivity.class);
        }else if(view == btnSmsDemo){
            intent = new Intent(this, SMSIntersectionActivity.class);
        }else if(view == btnProgress){
            intent = new Intent(this, CustomizeProgressButtonActivity.class);
        }else if(view == btnCircelProgress){
            intent = new Intent(this, CircleProgressActivity.class);
        }else if(view == btnViewPageer){
            intent = new Intent(this, ViewPagerActivity.class);
        }else if(view == btnDifferentDialog){
            intent = new Intent(this, DifferentDialogActivity.class);
        }else if(view == btnRecyclerView){
            intent = new Intent(this, RecyclerViewDemoActivity.class);
        }else if(view == btnCalculate){
            intent = new Intent(this, CalculateActivity.class);
        }
        startActivity(intent);
    }
}

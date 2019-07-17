package com.jupiter.androidstudy.customizeProgressButton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.jupiter.androidstudy.R;

/**
 * 1.定义个继承于AppCompatButton的类，作为自定义进度条
 * 2.在布局文件中加入这个自定义进度条的控件
 * 3.创建一个异步任务触发自定义进度条的重新绘制，异步任务主要分为3个步骤
 *  3.1 在执行前，调用onPreExecute， 准备工作，运行在调用线程中
 *  3.2 执行异步任务doInBackground， 运行在异步任务线程中
 *  3.3 通过异步线程的publishProgress触发回调方法onProgressUpdate，运行在调用线程中，在这个方法中调用Button的invalidate方法来触发画布的重新绘制
 */


public class CustomizeProgressButtonActivity extends AppCompatActivity {
    ProgressButton progressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_progress_button);
        progressButton = findViewById(R.id.btnCustomizeProgress);
    }

    public void onCustomizeProgressClick(View view) {

        if(view != progressButton){
            return;
        }

        new AsyncTask<Void, Integer, Void>(){

            //异步任务的执行主题，运行在异步任务线程中
            @Override
            protected Void doInBackground(Void... voids) {

                for(int i = 0 ; i <= 100 ; i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i);
                }
                return null;
            }

            //异步任务执行之前的准备工作，运行在调用线程中
            @Override
            protected void onPreExecute(){
                progressButton.setProgressable(true);
                progressButton.setMaxValue(100);
                progressButton.setProgressDrawable(new ColorDrawable(Color.parseColor("#ffff0000")));
            }

            //异步任务执行过程中对中间结果的回调，相对于于异步任务线程的publishProgress方法， 运行在调用线程中
            @Override
            protected void onProgressUpdate(Integer... values){
                progressButton.setCurrentProgress(values[0]);
                progressButton.setText(values[0] + "%");
            }

        }.execute();
    }

}

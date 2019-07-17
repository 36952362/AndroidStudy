package com.jupiter.androidstudy.circelprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.jupiter.androidstudy.R;

public class CircleProgressActivity extends AppCompatActivity {

    private CircleProgress circleProgress;
    private final String  TAG = CircleProgressActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);
        circleProgress = findViewById(R.id.cp_circle_progress);
    }

    public void onCircleProgressClick(View view) {
        if(circleProgress != view){
            return;
        }

        new AsyncTask<Void, Integer, Void>(){


            @Override
            protected Void doInBackground(Void... voids) {
                for(int i = 0 ; i <= 100; i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i);
                }
                return null;
            }

            @Override
            protected void onPreExecute(){
                circleProgress.setProgressEnable(true);
                circleProgress.setMaxValue(100);

            }

            @Override
            protected void onProgressUpdate(Integer... values){
                circleProgress.setCurrentProgress(values[0]);
                circleProgress.setText(values[0] + "%");
            }
        }.execute();



    }
}

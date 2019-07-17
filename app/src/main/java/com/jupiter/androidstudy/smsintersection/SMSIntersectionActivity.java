package com.jupiter.androidstudy.smsintersection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jupiter.androidstudy.R;

/**
 * 在AndroidManifest.xml中注册接受短信的权限
 */


public class SMSIntersectionActivity extends AppCompatActivity {

    private Button btnStartSmsIntersection;
    private Button btnStopSmsIntersection;
    private TextView txtSmsContent;
    private Intent smsIntersectionIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsintersection);
        init();
    }

    private void init() {
        btnStartSmsIntersection = findViewById(R.id.btnStartSmsIntersection);
        btnStopSmsIntersection = findViewById(R.id.btnStopSmsIntersection);
        //TODO: 这个功能暂时不能运行
        btnStartSmsIntersection.setEnabled(false);
        btnStopSmsIntersection.setEnabled(false);
        txtSmsContent = findViewById(R.id.txtSmsContent);
    }

    public void onSmsIntersectionClick(View view) {
        if(view == btnStartSmsIntersection){
            startSmsIntersection(this);
        }
        else{
            stopSmsIntersection(this);
        }
    }

    private void startSmsIntersection(Context context) {
        smsIntersectionIntent = new Intent(context, SMSIntersectionService.class);
        startService(smsIntersectionIntent);
        btnStartSmsIntersection.setEnabled(false);
        btnStopSmsIntersection.setEnabled(true);
    }

    private void stopSmsIntersection(Context context) {
        stopService(smsIntersectionIntent);
    }
}

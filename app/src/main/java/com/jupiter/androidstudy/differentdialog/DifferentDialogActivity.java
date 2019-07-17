package com.jupiter.androidstudy.differentdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Person;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import com.jupiter.androidstudy.MainActivity;
import com.jupiter.androidstudy.R;

public class DifferentDialogActivity extends AppCompatActivity {

    private Button notifyButton;
    private Button singleButton;
    private Button multiButton;
    private Button progressButton;
    private Button listButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_dialog);
        initView();
    }

    private void initView() {
        notifyButton = findViewById(R.id.btnNotifyDialog);
        singleButton = findViewById(R.id.btnSingleDialog);
        multiButton = findViewById(R.id.btnMultiDialog);
        progressButton = findViewById(R.id.btnProgressDialog);
        listButton = findViewById(R.id.btnListDialog);
    }

    public void onDifferentDialogClick(View view) {
        if(view == notifyButton){
            notifyDialog(view);
        }
        else if(view == singleButton){
            singleDialog(view);
        }
        else if(view == multiButton){
            multiDialong(view);
        }
        else if(view == progressButton){
            progressDialog(view);
        }
        else if(view == listButton){
            listDialog(view);
        }
    }

    private void listDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("列表对话框");
        builder.setIcon(R.drawable.ic_launcher_background);
        final String[] cities = {"上海", "杭州", "北京", "合肥"};
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DifferentDialogActivity.this, "你选择的是："+cities[i], 0).show();
            }
        });
        builder.show();
    }

    private void progressDialog(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("进度对话框");
        progressDialog.setMessage("拼命下载中...");
        progressDialog.setIcon(R.drawable.ic_launcher_background);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);

        new Thread(){
            public void run(){
                while (true){
                    SystemClock.sleep(100);
                    progressDialog.incrementProgressBy(2);
                    if(progressDialog.getMax() == progressDialog.getProgress()){
                        Message message = new Message();
                        message.obj = "数据下载成功";
                        handler.sendMessage(message);
                        progressDialog.dismiss();

                    }
                }
            }
        }.start();
        progressDialog.show();
    }

    private void multiDialong(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("多选对话框");
        builder.setIcon(R.drawable.ic_launcher_background);
        final String [] hobbies = {"旅行", "写代码", "赚钱", "钓鱼", "骑行"};
        boolean[] checkedItems = {false, false, true, true, false};
        builder.setMultiChoiceItems(hobbies, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                Toast.makeText(DifferentDialogActivity.this, "你选择的是 : " + hobbies[i],
                        Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DifferentDialogActivity.this, "点击了确定按钮",Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DifferentDialogActivity.this, "点击了取消按钮", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    private void singleDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("单选对话框");
        builder.setIcon(R.drawable.ic_launcher_background);
        final String[] cities = {"上海", "杭州", "北京", "合肥"};
        builder.setSingleChoiceItems(cities, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DifferentDialogActivity.this, "你选择了" + cities[i], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DifferentDialogActivity.this, "点击了确定按钮",Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DifferentDialogActivity.this, "点击了取消按钮", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();

    }

    private void notifyDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("通知对话框");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setMessage("这个是通知消息对话框");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DifferentDialogActivity.this, "点击了确定按钮",Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DifferentDialogActivity.this, "点击了取消按钮", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    //处理子线程的消息
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收数据
            Toast.makeText(DifferentDialogActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
        }
    };
}

package com.jupiter.androidstudy.takephoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jupiter.androidstudy.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


/**
 * 在这个Activity中演示了如何调用系统照相机App进行拍照
 * 1. 发送一个隐式Intent 去启动照相机App
 * 2. 拍照后获取照片并显示在ImageView控件中
 * 3. 判断是否有外置内存卡，如果有，则把拍的照片存储在外置内存卡中
 * 4. 由于要使用照相机和外置内存卡，所以要在Manifest中设置访问权限
 *    <uses-feature android:name="android.hardware.Camera2"/>
 *    <uses-feature android:name="android.hardware.Camera.autofocus"/>
 *    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 **/

public class TakePhotoActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private static final int TAKE_PIC = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        init();
    }

    private void init() {
        textView = findViewById(R.id.viewTakePhoto);
        button = findViewById(R.id.btnTakephotoDemo);
        imageView = findViewById(R.id.imgTakePhoto);
    }

    public void onTakePhotoClick(View view) {
        if (view == button){
            //跳转到拍照APP
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, TAKE_PIC);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != TAKE_PIC) {
            //不是我们期望的返回结果
            return;
        }

        if (RESULT_OK != resultCode) {
            //拍照失败
            return;

        }

        //获取到照片
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");

        //显示照片
        imageView.setImageBitmap(bitmap);

        if(!ExCardIsMount()){
            Toast.makeText(this, "没有外置内存卡", Toast.LENGTH_SHORT).show();
            return;
        }

        //存储在照相机目录
        try {
            File file = new File(Environment.getDownloadCacheDirectory(), "/storege/sdcard0/IMG_" + System.currentTimeMillis() + ".jpg");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    //判断是否有外置内存卡
    private boolean ExCardIsMount() {
        String isMount = Environment.getExternalStorageState();
        if(isMount.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }

        return false;
    }
}

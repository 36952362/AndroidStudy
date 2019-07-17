package com.jupiter.androidstudy.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jupiter.androidstudy.R;

/**
 * SQLite数据库的使用
 * 一、创建一个子类集成于SQLiteOpenHelper,并复写onCreate 和 onUpgrade 方法
 * 1. onCreate 方法只有在数据库第一次创建时调用，并且只调用一次，通常用来建立数据库表结构
 * 2. onUpgrade 方法在升级时使用， 但是只能升级不能降级
 * 二、操作数据库
 * 1. 调用getReadableDatabase()方法或者getWritableDatabase()方法获取SQLiteDatabase对象
 * 2. 调用SQLiteDatabase对象的execSQL方法对数据库进行CRUD操作
 * 3. 操作解释后调用SQLiteDatabase对象的close方法关闭数据库链接
 */


public class SQLiteActivity extends AppCompatActivity {

    private EditText editUserName;
    private EditText editPassword;
    private EditText editEmail;
    private RadioGroup radioGroup;
    private TextView textDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        Init();
    }

    private void Init() {
        editUserName = findViewById(R.id.editUserName);
        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.editEmail);
        radioGroup = findViewById(R.id.radioGroup);
        textDisplay = findViewById(R.id.txtDisplay);
    }

    public void onSQLiteClick(View view) {
        String userName = editUserName.getText().toString();
        String password = editPassword.getText().toString();


        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = editEmail.getText().toString();
        int radioId = radioGroup.getCheckedRadioButtonId();

        String gender = "male";
        if(radioId == R.id.radioFemale){
            gender = "female";
        }

        //检查用户名是否已经存在
        StudentDao studentDao = new StudentDao(this);
        StudentBasicInfo studentBasicInfo = studentDao.query(userName);
        if(null != studentBasicInfo){
            Toast.makeText(this, "该用户已经存在", Toast.LENGTH_SHORT).show();
            return;
        }

        studentBasicInfo = new StudentBasicInfo(userName,password, gender, email);
        studentDao.insert(studentBasicInfo);
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        studentBasicInfo.setPassword("******");
        textDisplay.setText(studentBasicInfo.toString());

    }
}

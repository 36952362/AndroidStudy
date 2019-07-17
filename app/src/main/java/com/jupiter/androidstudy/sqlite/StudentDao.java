package com.jupiter.androidstudy.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StudentDao {
    private StudentDBHelper studentDBHelper;


    public StudentDao(Context context){

        //创建数据库
        studentDBHelper = new StudentDBHelper(context);
    }

    //插入一条记录
    public  void insert(StudentBasicInfo studentBasicInfo){
        SQLiteDatabase database = studentDBHelper.getReadableDatabase();
        String sql = "insert into BasicInfo(name, password, gender, email) values(?, ?, ?, ?)";
        database.execSQL(sql, new Object[]{studentBasicInfo.getName(), studentBasicInfo.getPassword(), studentBasicInfo.getGender(), studentBasicInfo.getEmail()});
        database.close();
    }

    //根据名字删除一条记录
    public void delete(String name){
        SQLiteDatabase database = studentDBHelper.getReadableDatabase();
        String sql = "delete from BasicInfo where name=?";
        database.execSQL(sql, new Object[]{name});
        database.close();
    }

    //根据用户名和密码更新一条记录
    public void update(StudentBasicInfo studentBasicInfo){
        SQLiteDatabase database = studentDBHelper.getReadableDatabase();
        String sql = "update BasicInfo set gender = ? and email = ? where name = ? and password = ?";
        database.execSQL(sql, new Object[]{studentBasicInfo.getGender(), studentBasicInfo.getEmail(), studentBasicInfo.getName(), studentBasicInfo.getPassword()});
        database.close();
    }

    //通过用户名和密码查询一条记录
    public StudentBasicInfo query(String name){
        SQLiteDatabase database = studentDBHelper.getReadableDatabase();
        String sql = "select * from BasicInfo where name = ? and password = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{name});
        StudentBasicInfo studentBasicInfo = null;
        if(cursor.moveToNext()){
            String gender = cursor.getString(cursor.getColumnIndex("gender"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            studentBasicInfo = new StudentBasicInfo(name, "", gender, email);
        }
        cursor.close();
        database.close();
        return studentBasicInfo;
    }


}

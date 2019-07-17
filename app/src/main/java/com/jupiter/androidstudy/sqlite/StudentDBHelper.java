package com.jupiter.androidstudy.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

public class StudentDBHelper extends SQLiteOpenHelper {

    public StudentDBHelper(Context context){
        super(context, "Student2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //初始化数据库字段
        String sql = "Create table BasicInfo (_id integer primary key  autoincrement, name varchar, password varchar, gender varchar, email varchar)";

        //执行语句
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        String updateTelSql = "";
        String updateAddressSql = "";
        /* 这里当数据库版本升级的时候才会调用，所以这里用来升级，添加或者删除字段，绝大多数是增加字段 */
        /*
         * 假设第一个版本里头有id，有name,有sex， 第二个版本里有id,name,sex,address,
         * 第三个版本里有id,name,sex,address,tel
         * 那么问题就来了，有些用户可能是第一个版本，有些用户是第二个版本，他们的字段不一样。所以呢，升级也不一样。
         * 因些，我们要对这些用户的老版本进行判断，然后对应升级即可。如下：
         */
        switch (oldVersion){
            case 1:
                updateTelSql = "alter table BasicInfo ADD address varchar";
                updateAddressSql = "alter table BasicInfo ADD address varchar";
                break;
            case 2:
                updateAddressSql = "alter table BasicInfo ADD address varchar";
        }

        if(!TextUtils.isEmpty(updateTelSql)) {
            sqLiteDatabase.execSQL(updateTelSql);
        }

        if(!TextUtils.isEmpty(updateAddressSql)){
            sqLiteDatabase.execSQL(updateAddressSql);
        }
    }
}

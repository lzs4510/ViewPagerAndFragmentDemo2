package com.baway.lizongshu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 李宗书 on 2017/6/23.
 */

public class Sqlhelp extends SQLiteOpenHelper {
    //建数据库
    public Sqlhelp(Context context) {
        super(context, "Book.db", null, 1);
        // TODO Auto-generated constructor stub
    }

    //建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table user(id integer primary key autoincrement,title varchar(200),content varchar(2000))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}

package com.example.logonrm.persistencia.persistencia.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luiz Aquino on 2017-06-17.
 */

public class LoginDb extends SQLiteOpenHelper {
    public LoginDb(Context context){
        super(context, "logindb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = "CREATE TABLE TAB_LOGIN (COD_LOGIN INTEGER PRIMARY KEY, USER_NAME TEXT, PASSWORD TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TAB_LOGIN");
        onCreate(db);
    }
}

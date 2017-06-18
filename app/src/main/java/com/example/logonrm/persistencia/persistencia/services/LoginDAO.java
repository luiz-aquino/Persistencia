package com.example.logonrm.persistencia.persistencia.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.logonrm.persistencia.persistencia.Models.Login;

/**
 * Created by Luiz Aquino on 2017-06-17.
 */

public class LoginDAO {
    private static final String DATABASE_NAME = "logindb.db";
    private static final int DATABASE_VERSION = 1;
    private static  final String TABLE_NAME = "TAB_LOGIN";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(COD_LOGIN, USER_NAME, PASSWORD) VALUES (?,?,?)";

    public LoginDAO(Context context){
        this.context = context;
        LoginDb loginDb = new LoginDb(context);
        this.db = loginDb.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    public long insert(Login login){
        this.insertStmt.bindLong(1, login.getCodUser());
        this.insertStmt.bindString(2, login.getUsername());
        this.insertStmt.bindString(3, login.getPassword());
        return this.insertStmt.executeInsert();
    }

    public void deleteAll(){
        this.db.delete(TABLE_NAME, null, null);
    }

    public Login getById(long cod){
        Cursor c = db.query(TABLE_NAME, new String[]{ "COD_LOGIN", "USER_NAME", "PASSWORD" }, "COD_LOGIN = ?", new String[]{ String.valueOf(cod) }, null, null, null);

        Login l = null;

        if(c.getCount() > 0){
            c.moveToFirst();
            l = new Login();
            l.setCodUser(c.getLong(0));
            l.setUsername(c.getString(1));
            l.setPassword(c.getString(2));
        }

        return  l;
    }
}


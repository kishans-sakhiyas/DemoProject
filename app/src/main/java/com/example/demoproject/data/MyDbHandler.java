package com.example.demoproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.demoproject.model.UserModel;
import com.example.demoproject.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(@Nullable Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String query = "create table " + Params.TABLE_NAME + "("
//                + Params.COL_ID + "integer primary key, " + Params.COL_NAME
//                + "text," + Params.COL_PHONE_NO + "text, " + Params.COL_EMAIL
//                + "text," + Params.COL_PASSWORD + "text" + ")";

        String query = "create table if not exists user_table (id integer primary key, name text, phone_no text, email text, password text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addUser(UserModel userModel) {
//        Log.d("TAG", "addUser: "+userModel.getEmail() + userModel.getNumber());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.COL_NAME, userModel.getName());
        values.put(Params.COL_PHONE_NO, userModel.getNumber());
        values.put(Params.COL_EMAIL, userModel.getEmail());
        values.put(Params.COL_PASSWORD, userModel.getPassword());
        db.insert(Params.TABLE_NAME, null, values);
        db.close();
    }


    public List<UserModel> getAllUser(){
        List<UserModel> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "select * from user_table";
        Cursor cursor = db.rawQuery(select, null);

        if(cursor.moveToFirst()){
            do{
                UserModel userModel= new UserModel("","","","");
                userModel.setName(cursor.getString(1));
                userModel.setNumber(cursor.getString(2));
                userModel.setEmail(cursor.getString(3));
                userModel.setPassword(cursor.getString(4));
                userList.add(userModel);
            }while (cursor.moveToNext());
        }
        return  userList;
    }

    public int updateUser(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(Params.COL_PHONE_NO, userModel.getNumber());
        values.put(Params.COL_NAME, userModel.getName());

        return  db.update(Params.TABLE_NAME, values, Params.COL_PHONE_NO +"=?",
                new String[]{String.valueOf(userModel.getNumber())});
    }

}

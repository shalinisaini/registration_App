package com.example.nyx.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(Context context)
    //  public DbHelper(Context context,String name,SQLiteDatabase.CursorFactory,int version)

    {
        super(context, "userinfo", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table userreg(username text primary key,name text,password text,phone text,city text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    long registration(String username, String name, String password, String phone, String city) {

        //insertion user_registration
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("any query"); any query=insert into userreg value('a','b','c','d',.....);

        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("name", name);
        cv.put("password", password);
        cv.put("phone", phone);
        cv.put("city", city);

        long result = db.insert("userreg", null, cv);// registration returns long value

        db.close();
        return result;

        //no. of rows affected

    }

    Object[] login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        String[] column = {"username"};

        Cursor cursor = db.query("userreg"//table name
                , column//column name
                , "username=? and password =?"//where clause
                , new String[]{username, password}//where args
                , null//group by
                , null//having
                , null//order by
        );
        //query=select * from userreg wher username=... and userid=..


        if (cursor.getCount() > 0) //row count >0
        {
            //login successful

            cursor.moveToFirst();
            String user_name = cursor.getString(0);
            db.close();
            return new Object[]{true, user_name};
        } else {
            //login failed
            db.close();
            return new Object[]{false, null};
        }

    }

    Cursor getFellowMember() {
        SQLiteDatabase db = getReadableDatabase();
        String[] column = {"username", "name", "password", "phone", "city"};

        return db.query("userreg", column,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    Cursor getMember(String username) {
        SQLiteDatabase db = getReadableDatabase();
        String[] column = {"username", "name", "password", "phone", "city"};

        return db.query("userreg", column,
                "username=?",//where clause
                new String[]{username},//where arg
                null,
                null,//having
                null,//group by
                null//order by
                //select* from userreg where username=.....;
        );
    }

    long deleteUser(String username) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("userreg", "username=?",
                new String[]{username});


    }


    long updateinfo(String username, String name,String phone,String city) {

        //insertion user_registration
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("any query"); any query=insert into userreg value('a','b','c','d',.....);

        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("city", city);

        long result = db.update("userreg"//Table name
        , cv //content Value
        ," username=?"//where clause
        ,new String[] {username}//where args
         );//update returns long value

                db.close();
        return result;

        //no. of rows affected

    }
}




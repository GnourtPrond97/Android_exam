package com.example.dawd_exam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static   final String DB_NAME = "Product_manager";
    public static   final int DB_VERSION = 1;

    public static String TABLE_NAME = "Product";

    public static String ID= "_id";
    public static String NAME= "name";
    public static String QUANTITY= "quantity";

    public DBHelper(Context context){
        super(context, DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                QUANTITY + " TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS "  + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public String addProduct(String name , int quantity){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(QUANTITY,quantity);

        long isAdd = db.insert(TABLE_NAME,null,contentValues);
        if(isAdd == -1){
            return  "Add fail !";
        }
        db.close();
        return "Add success !";
    }

    public Cursor getAllProduct(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DB_NAME;
        Cursor c = db.rawQuery(sql,null);
        return c;
    }


}

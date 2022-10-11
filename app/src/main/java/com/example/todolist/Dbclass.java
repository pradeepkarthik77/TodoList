package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Dbclass extends SQLiteOpenHelper
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "tododb";
    private static final String Table_Users = "todotable";
    private static final String Key_id = "_id";
    private static final String Key_name = "Content";

    public Dbclass(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String create_table = "CREATE TABLE IF NOT EXISTS "+Table_Users+"( "+Key_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Key_name+" TEXT "+")";
        //System.out.println(create_table);
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
    }

    public void insert_todo(String content,DbAdapter dbAdapter)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cvalues = new ContentValues();

        cvalues.put(Key_name,content);

        long newrowid = db.insert(Table_Users,null,cvalues);

        db.close();
    }

    public void delete_todo(long id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_Users,Key_id+" = ?",new String[] {String.valueOf(id)});
        db.close();
    }

    public Cursor getCursor()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("Select * from "+Table_Users,null);

        return res;
    }

}

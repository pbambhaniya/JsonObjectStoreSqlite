package com.multipz.maindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Admin on 20-03-2018.
 */

public class Database extends SQLiteOpenHelper {
    SQLiteDatabase db;

    public Database(Context context)

    {

        super(context, "EmployeeDatabase.db", null, 1);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        String tableEmp = "create table emp(id text,name text)";

        db.execSQL(tableEmp);

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(String id, String name)

    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        sqLiteDatabase.insert("emp", null, values);

    }

    public ArrayList fetchData()

    {

        ArrayList<StatusModel> stringArrayList = new ArrayList<StatusModel>();

        String fetchdata = "select * from emp";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);

        if (cursor.moveToFirst()) {
            do {
                stringArrayList.add(new StatusModel(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());

        }

        return stringArrayList;

    }

    public ArrayList fetchDataPage(int page) {
        int nor = 20;

        ArrayList<StatusModel> stringArrayList = new ArrayList<StatusModel>();

        String fetchdata = "select * from emp limit " + page * nor + "," + nor;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);

        if (cursor.moveToFirst()) {
            do {
                stringArrayList.add(new StatusModel(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());

        }

        return stringArrayList;

    }


    public void deleteAll() {
        //db.delete("emp", null, null);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from emp");
    }


}

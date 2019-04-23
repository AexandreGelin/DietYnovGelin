package com.ynov.dietynovgelin.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MeasurementSQLite extends SQLiteOpenHelper {

    private static final String TABLE_WEIGHT = "table_weight";
    private static final String COL_ID = "ID";
    private static final String COL_DATE = "Date";
    private static final String COL_MESURE = "Mesure";
    private static final String COL_TYPE = "Type";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_WEIGHT + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_DATE + " TIMESTAMP NOT NULL," + COL_MESURE + " NUMBER NOT NULL, " + COL_TYPE +" TEXT NOT NULL);";

    public MeasurementSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE table with the "SQL" String CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_WEIGHT+ ";");
        onCreate(db);
    }

}

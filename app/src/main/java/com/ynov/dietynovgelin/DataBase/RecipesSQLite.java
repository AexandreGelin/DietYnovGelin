package com.ynov.dietynovgelin.DataBase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class RecipesSQLite extends SQLiteOpenHelper {

    private static final String TABLE_WEIGHT = "table_weight";
    private static final String COL_ID = "ID";
    private static final String COL_TITLE = "Title";
    private static final String COL_PORTION = "Portion";
    private static final String COL_IMAGE = "Image";
    private static final String COL_ISFAV = "Is_fav";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_WEIGHT + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITLE + " TEXT NOT NULL," + COL_PORTION + " NUMBER NOT NULL, " + COL_IMAGE +" TEXT NOT NULL,"
        + COL_ISFAV +" BOOLEAN NOT NULL);";

    public RecipesSQLite(Context context, String name, CursorFactory factory, int version) {
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

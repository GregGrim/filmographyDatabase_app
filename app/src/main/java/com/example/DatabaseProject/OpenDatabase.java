package com.example.DatabaseProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class OpenDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "actor.db";
    private static final int DATABASE_VERSION = 1;
    OpenDatabase(Context context)
    {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String allRecordsInActorTable(SQLiteDatabase sqdb)
    {
        StringBuilder result = new StringBuilder();
        Cursor c = sqdb.rawQuery("SELECT * FROM FilmographyTable", null);
        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    String id = c.getString(0);
                    result.append(id).append(",");
                    String film = c.getString(1);
                    result.append(film).append(",");
                    String year = c.getString(2);
                    result.append(year).append(",");
                    String role = c.getString(3);
                    result.append(role).append(",");
                    String director = c.getString(4);
                    result.append(director).append("\n");
                } while (c.moveToNext());
            }
        }
        c.close();
        return result.toString();
    }
    public String recordsMatchingCodeword(SQLiteDatabase sqdb, String codeword){
        StringBuilder result = new StringBuilder();
        String[] selectionArgs = {"%" + codeword + "%", "%" + codeword + "%", "%" + codeword + "%", "%" + codeword + "%"};

        Cursor c = sqdb.rawQuery("SELECT * FROM FilmographyTable WHERE film LIKE ? OR year LIKE ? OR role LIKE ? OR director LIKE ?", selectionArgs);
        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    String film = c.getString(1);
                    result.append(film).append(",");
                    String year = c.getString(2);
                    result.append(year).append(",");
                    String role = c.getString(3);
                    result.append(role).append(",");
                    String director = c.getString(4);
                    result.append(director).append("\n");
                } while (c.moveToNext());
            }
            else
            {
                result = new StringBuilder("No Records Found for the codeword = " + codeword);
            }
        }
        c.close();
        return result.toString();
    }

    public void addNewRecord(SQLiteDatabase sqdb, List<String> params) {
        ContentValues values = new ContentValues();
        values.put("film", params.get(0));
        values.put("year", params.get(1));
        values.put("role", params.get(2));
        values.put("director", params.get(3));
        // Insert the record into the database
        sqdb.insert("FilmographyTable", null, values);
    }
    public boolean checkExistence(SQLiteDatabase sqdb, int targetID) {
        Cursor c = sqdb.rawQuery("SELECT 1 FROM FilmographyTable WHERE id = " + targetID, null);
        boolean recordExists = c != null && c.moveToFirst();

        if (c != null) {
            c.close();
        }
        return recordExists;
    }
    public void deleteRecordByID(SQLiteDatabase sqdb, int targetID) {
        sqdb.delete("FilmographyTable","id = " + targetID, null);
    }

    public void changeFilmByID(SQLiteDatabase sqdb, String targetID, String parameter) {
        ContentValues values = new ContentValues();
        values.put("film", parameter);
        sqdb.update("FilmographyTable", values, "id="+targetID, null);
    }

    public void changeYearByID(SQLiteDatabase sqdb, String targetID, String parameter) {
        ContentValues values = new ContentValues();
        values.put("year", parameter);
        sqdb.update("FilmographyTable", values, "id="+targetID, null);
    }

    public void changeRoleByID(SQLiteDatabase sqdb, String targetID, String parameter) {
        ContentValues values = new ContentValues();
        values.put("role", parameter);
        sqdb.update("FilmographyTable", values, "id="+targetID, null);
    }

    public void changeDirectorByID(SQLiteDatabase sqdb, String targetID, String parameter) {
        ContentValues values = new ContentValues();
        values.put("director", parameter);
        sqdb.update("FilmographyTable", values, "id="+targetID, null);
    }
}


package com.example.DatabaseProject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}


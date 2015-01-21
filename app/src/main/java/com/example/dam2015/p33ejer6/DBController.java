package com.example.dam2015.p33ejer6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Hector on 21/01/2015.
 */
public class DBController extends SQLiteOpenHelper {
    private static final String LOGCAT = null;

    public DBController(Context applicationcontext) {
        super(applicationcontext, "DBCanciones.db", null, 1);
        Log.d(LOGCAT, "Created");
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE Canciones (id integer PRIMARY KEY AUTOINCREMENT,idfoto INTEGER, titulo TEXT,autor TEXT, duracion TEXT)";
        database.execSQL(query);
        Log.d(LOGCAT,"animals Created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS Canciones";
        database.execSQL(query);
        onCreate(database);
    }

    public void insertCancion(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idfoto", queryValues.get("idfoto"));
        values.put("titulo", queryValues.get("titulo"));
        values.put("autor", queryValues.get("autor"));
        values.put("duracion", queryValues.get("duracion"));
        database.insert("Canciones", null, values);
        database.close();
    }
    public int updateCancion(HashMap<String, String> queryValues) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idfoto", queryValues.get("idfoto"));
        values.put("titulo", queryValues.get("titulo"));
        values.put("autor", queryValues.get("autor"));
        values.put("duracion", queryValues.get("duracion"));
        return database.update("Canciones", values, "id" + " = ?", new String[] { queryValues.get("id") });
    }

    public void deleteCancion(String id) {
        Log.d(LOGCAT,"delete");
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM  Canciones where id='"+ id +"'";
        Log.d("query",deleteQuery);
        database.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String, String>> getAllCanciones() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM Canciones";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("idfoto", cursor.getString(1));
                map.put("titulo", cursor.getString(2));
                map.put("autor", cursor.getString(3));
                map.put("duracion", cursor.getString(4));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        // return contact list
        return wordList;
    }
    public HashMap<String, String> getCancionInfo(String id) {
        HashMap<String, String> wordList = new HashMap<String, String>();
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM Canciones where id='"+id+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //HashMap<String, String> map = new HashMap<String, String>();
                wordList.put("id", cursor.getString(1));
                //wordList.add(map);
            } while (cursor.moveToNext());
        }
        return wordList;
    }


}

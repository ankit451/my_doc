package com.example.android.pokedr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Microsoft on 21-01-2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static  final String DATABASE_NAME="medicalHistoryManager";
    private static final String TABLE_MEDICAL_HISTORY= "medicalHistory";


    private static final String KEY_ID="id";
    private static final String KEY_DATE="name";
    private static final String KEY_ILLNESS="illness";
    private static final String KEY_PRESCRIPTION="prescription";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEDICAL_HISTORY = "CREATE TABLE " + TABLE_MEDICAL_HISTORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," +KEY_DATE + " TEXT,"
                +KEY_PRESCRIPTION + " TEXT,"
                + KEY_ILLNESS + " TEXT" + ")";
        db.execSQL(CREATE_MEDICAL_HISTORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICAL_HISTORY);

        // Create tables again
        onCreate(db);

    }
    public void addHistory(MedHistory medHistory){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_DATE,medHistory.getDate());
        values.put(KEY_ILLNESS,medHistory.getIllness());
        values.put(KEY_PRESCRIPTION, medHistory.getPrescription());

        //Inserting Row
        db.insert(TABLE_MEDICAL_HISTORY,null,values);
        db.close();// closing the database connection
    }

    //getting all the medical history
    public List<MedHistory> getAllHistory(){
        List<MedHistory> historyList=new ArrayList<>();
        String selectQuery="SELECT * FROM " +TABLE_MEDICAL_HISTORY;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        //looping through all rows in the list
        if(cursor.moveToFirst()){
            do{
                MedHistory history=new MedHistory();
                history.setId(Integer.parseInt(cursor.getString(0)));
                history.setDate(cursor.getString(1));
                history.setIllness(cursor.getString(2));
                history.setPrescription(cursor.getString(3));


            }while(cursor.moveToNext());
        }

        //return the data in the form of list--array of objects
        return historyList;
    }

}//IMPLEMENTATION OF THE DATABASE HANDLER CLASS ENDS HERE




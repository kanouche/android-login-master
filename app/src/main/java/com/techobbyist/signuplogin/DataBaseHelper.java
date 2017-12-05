package com.techobbyist.HouseHold;


/**
 * Created by Acer on 11/26/2017.
 */


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;


public class DataBaseHelper  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String TAG = DataBaseHelper.class.getSimpleName();

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "_username";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_SEXE = "sexe";

    StringBuilder query = null;
    Cursor cursor = null;
    private SQLiteDatabase database = null;


    SQLiteDatabase db;


    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_SEXE + " TEXT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PASS + " TEXT);";

    /*
        private static final String TABLE_CREATE= "create table contacts (id integer primary key not null , "+
                "name text not null, email text not null, uname text not null, pass text not null);";
    */
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "Select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();


        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_PASS, c.getPassword());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_SEXE, c.getSexe());
        values.put(COLUMN_USERNAME, c.getUsername());


        long id = db.insert(TABLE_NAME, null, values);
        db.close();

        Log.d(TAG, "user inserted " + id);

    }


    public boolean getUser(String email, String pass) {
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + TABLE_NAME + " where " +
                COLUMN_EMAIL + " = " + "'" + email + "'" + " and " + COLUMN_PASS + " = " + "'" + pass + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }


    /*

    public String searchPass(String uname)
    {
        db =this.getReadableDatabase();
        String query="Select * from" + TABLE_NAME;
        Cursor cursor= db.rawQuery(query,null);
        String a,b;
        b="not found";
        if(cursor.moveToFirst())
        {
            do{
                a=cursor.getString(2);
                if(a.equals(uname))
                {
                    b=cursor.getString(3);
                    break;
                }

            }
            while(cursor.moveToNext());
        }
        return b;


    }

*/


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM" + TABLE_NAME + "where" + COLUMN_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;


    }

    public ArrayList<String> getAppCategoryDetail() {

        String selectQuery = "select * from  " + TABLE_NAME + " where " +
                COLUMN_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<String> data = new ArrayList<>();
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            data.add(cursor.getString(1));
        }
        cursor.close();
        db.close();


        return data;


    }

    public ArrayList<Contact> getAllProfiles() {
        ArrayList<Contact> profiles = new ArrayList<>();

        // select recipe query
        String query = "SELECT  * FROM " + TABLE_NAME;

        // get reference of the RecipesDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // parse all results
        Contact contact;
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                contact = new Contact();
                contact.setName(name);

                // Add book to books
                profiles.add(contact);
            } while (cursor.moveToNext());
        }

        db.close();
        return profiles;
    }

    public ArrayList<String> getAllUserNames() {

        ArrayList<String> profiles = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1 ";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if ((c.getString(c.getColumnIndex("_name")) != null)) {
                String userName = c.getString(c.getColumnIndex("_name"));
                profiles.add(userName);
            }
            c.moveToNext();
        }
        c.close();
        //db.close();
        return profiles;
    }
}
    /**
    public void insertImg(int id , Bitmap img ) {


        byte[] data = getBitmapAsByteArray(img); // this is a function

        insertStatement_logo.bindLong(1, id);
        insertStatement_logo.bindBlob(2, data);

        insertStatement_logo.executeInsert();
        insertStatement_logo.clearBindings() ;

    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
    public Bitmap getImage(int i){

        String qu = "select img  from table where feedid=" + i ;
        Cursor cur = db.rawQuery(qu, null);

        if (cur.moveToFirst()){
            byte[] imgByte = cur.getBlob(0);
            cur.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

        return null ;
    }


}
     */
package com.hasi.easyfineimposer;

/**
 * Created by hasintha on 8/12/15.
 */
//https://www.thenewboston.com/forum/topic.php?id=3767

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.widget.Toast;


import java.sql.SQLException;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FineImposerDB.db";

    public static final String Table_User = "user";
    public static final String Column_UserName = "userName";
    public static final String Column_PW = "password";

    public static final String Table_Fine = "fine";
    public static final String Column_VNumber = "vehicleNo";
    public static final String Column_Description = "description";
    public static final String Column_Amount = "amount";
    public static final String Column_NIC = "nic";
    public static final String Column_Name = "name";

    public static final String Table_Owner = "owner";


    public static final String Table_ImposedFines = "ImposedFines";
    public static final String Column_FID = "id";
    public static final String Column_UNIC = "nic";

    public SQLiteDatabase db;





    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creating table fine
        String query = "CREATE TABLE " + Table_Fine + "(" +
                Column_NIC + " TEXT , " +
                Column_Name+ " TEXT, " +
                Column_Description + " TEXT ," +
                Column_VNumber + " TEXT ," +
                Column_Amount + " TEXT " +
                ");";
        db.execSQL(query);
        //creating table user
         query = "CREATE TABLE " + Table_User + "(" +
                Column_UserName + " TEXT PRIMARY KEY , " +
                Column_PW + " TEXT " +
                ");";
        db.execSQL(query);

        // creating table owner
      /*  query = "CREATE TABLE " + Table_Owner + "(" +
                Column_NIC + " TEXT PRIMARY KEY , " +
                Column_Name+ " TEXT " +
                ");";
        db.execSQL(query);



        //create table imposedFines

        query = "CREATE TABLE " + Table_ImposedFines+ "(" +
                Column_FID + " TEXT,"+
                Column_UNIC+ " TEXT," +
                "FOREIGN KEY ("+Column_FID+") REFERENCES "+Table_Fine+"("+Column_ID+")," +
                "FOREIGN KEY ("+Column_UNIC+") REFERENCES "+Table_Owner+"("+Column_NIC+")" +
                ");";


        db.execSQL(query);*/





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Table_Fine);
        db.execSQL("DROP TABLE IF EXISTS " + Table_User);
        onCreate(db);
    }



    public void addUser(String uName, String pw){
        ContentValues values = new ContentValues();
        db=this.getWritableDatabase();
        values.put(Column_UserName, uName);
        values.put(Column_PW, pw);

        db.insert(Table_User, null, values);
        db.close();
    }

    //insert fine values into a database
    public void addFine(String nic, String name,String description,String vehNo, String amount){
        ContentValues values = new ContentValues();
        db=this.getWritableDatabase();
            try {
                values.put(Column_NIC, nic);
                values.put(Column_Name, name);
                values.put(Column_VNumber, vehNo);
                values.put(Column_Amount, amount);
                values.put(Column_Description, description);
                values.put(Column_Amount, amount);

                db.insert(Table_Fine, null, values);
                db.close();

            }catch (Exception e){


            }



    }

    public FineData[] getReport(){
        SQLiteDatabase db = getReadableDatabase();

        FineData[] fData=new FineData[200];
        int i=0;

        for(int j=0;j<200;j++){

            fData[j]=new FineData();

        } try {
            db = getReadableDatabase();
            i=0;
            String query = "SELECT * FROM " + Table_Fine+";";//Cursor points to a location in your results
            Cursor cusr = db.rawQuery(query, null);
            cusr.moveToFirst();//move the curser to the first result


            while (!cusr.isAfterLast()) {
                if (cusr.getString(cusr.getColumnIndex(Column_NIC))!=null) {

                    fData[i].setNic(cusr.getString(cusr.getColumnIndex(Column_NIC)));
                    fData[i].setName(cusr.getString(cusr.getColumnIndex(Column_Name)));
                    fData[i].setAmount(cusr.getString(cusr.getColumnIndex(Column_Amount)));
                    fData[i].setVehicleNo(cusr.getString(cusr.getColumnIndex(Column_VNumber)));
                    fData[i].setDescription(cusr.getString(cusr.getColumnIndex(Column_Description)));
                    i++;
                    cusr.moveToNext();

                }

            }

        }catch (Exception es){


        }

        return  fData;

    }










    //authenticate user login data
    public Boolean authenticate(String userName,String password){
        boolean isTrue=false;


        SQLiteDatabase db = getReadableDatabase();

       try {

           String query = "SELECT * FROM " + Table_User + " WHERE " + Column_UserName + "='" + userName + "' ";
           //Cursor points to a location in your results
           Cursor c = db.rawQuery(query, null);
           //Move to the first row in your results
           c.moveToFirst();
           c.moveToFirst();//move the curser to the first result



           if (c.getString(c.getColumnIndex(Column_PW)).equals(password)) {
               isTrue = true;
           }

       }catch(Exception e){


       }

            return isTrue;





    }
}

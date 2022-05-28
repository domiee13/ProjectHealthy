package com.example.projecthealthy.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.projecthealthy.model.Food;
import com.example.projecthealthy.model.Step;
import com.example.projecthealthy.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HealthyApp.db";
    private static int DATABASE_VERSION = 1;
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String createUser = "CREATE TABLE \"users\" (\n" +
//                "\t\"id\"\tINTEGER NOT NULL,\n" +
//                "\t\"fullname\"\tTEXT NOT NULL,\n" +
//                "\t\"username\"\tTEXT NOT NULL,\n" +
//                "\t\"password\"\tTEXT NOT NULL,\n" +
//                "\t\"weight\"\tREAL NOT NULL,\n" +
//                "\t\"height\"\tREAL NOT NULL,\n" +
//                "\t\"age\"\tINTEGER NOT NULL,\n" +
//                "\t\"gender\"\tINTEGER NOT NULL,\n" +
//                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
//                ");";
//
        String createUser = "CREATE TABLE users("+"id integer primary key autoincrement,fullname text, " +
                "username text,password text, weight real, height real, age integer, gender integer)";
        db.execSQL(createUser);
//        String createFoods = "CREATE TABLE \"foods\" (\n" +
//                "\t\"id\"\tINTEGER NOT NULL AUTOINCREMENT,\n" +
//                "\t\"user_id\"\tINTEGER NOT NULL,\n" +
//                "\t\"name\"\tTEXT NOT NULL,\n" +
//                "\t\"kcal\"\tREAL NOT NULL,\n" +
//                "\t\"date\"\tTEXT NOT NULL,\n" +
//                "\tPRIMARY KEY(\"id\")\n" +
//                ");";
        String createFoods = "create table foods("+"id integer primary key autoincrement, " +
                "user_id integer, name text,kcal real,date text)";

        db.execSQL(createFoods);
//        String createSteps = "CREATE TABLE \"steps\" (\n" +
//                "\t\"id\"\tINTEGER NOT NULL AUTOINCREMENT,\n" +
//                "\t\"user_id\"\tINTEGER NOT NULL,\n" +
//                "\t\"count\"\tINTEGER NOT NULL,\n" +
//                "\t\"date\"\tTEXT NOT NULL,\n" +
//                "\t\"kcal\"\tREAL NOT NULL,\n" +
//                "\tPRIMARY KEY(\"id\")\n" +
//                ");";
        String createSteps = "create table steps("+ "id integer primary key autoincrement, user_id integer,count integer, date text, kcal real)";
        db.execSQL(createSteps);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //get user by name to check authentication
//    public User getUserByName(String name){
//
//    }

    public boolean checkAuth(String username, String password){
        // array of columns to fetch
        String[] columns = {"id"};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = "username = ? AND  password = ?";
        // selection arguments
        String[] selectionArgs = {username, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query("users", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public User getUserByUsername(String usernameLogin) {
        String whereClause = "username=?";
        String[] whereArgs = {usernameLogin};
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor rs = db.rawQuery("SELECT * FROM users where username = 'domiee13'",null,null);
        Cursor rs = db.query("users", null, whereClause, whereArgs, null, null, null);
        if (rs.moveToFirst() && rs.getCount()>=1) {
            rs.moveToFirst();
            int id = rs.getInt(0);
            String fullname = rs.getString(1);
            String username = rs.getString(2);
            String password = rs.getString(3);
            float weight = rs.getFloat(4);
            float height = rs.getFloat(5);
            int age = rs.getInt(6);
            int gender = rs.getInt(7);
            return new User(id, fullname, username, password, age, gender, weight, height);
        }
        return null;
    }

    public List<Step> getAllSteps(int user_id, String date){
        List<Step>list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = "user_id = ? AND date like ?";
        String[] whereArgs = {user_id+"",date};
        Cursor rs = db.query("steps",null,whereClause,whereArgs,null,null,null);
        while(rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            int userId = rs.getInt(1);
            int count = rs.getInt(2);
            String datetime = rs.getString(3);
            float kcal = rs.getFloat(4);
            list.add(new Step(user_id,count,datetime,kcal));
        }
        return list;
    }

    public List<Food> getAllFood(int user_id, String date){
        List<Food> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = "user_id = ? AND date like ?";
        String[] whereArgs = {user_id+"",date};
        Cursor rs = db.query("foods",null,whereClause,whereArgs,null,null,null);
        while(rs!=null && rs.moveToNext()){
            int id = rs.getInt(0);
            int userId = rs.getInt(1);
            String name = rs.getString(2);
            float kcal = rs.getFloat(3);
            String datetime = rs.getString(4);
            list.add(new Food(userId,kcal,name,datetime));
        }
        return list;

    }

    public long addFood(Food item){
        ContentValues values = new ContentValues();
        values.put("user_id",item.getUser_id());
        values.put("name",item.getName());
        values.put("kcal",item.getKcal());
        values.put("date",item.getDate());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("foods",null,values);
    }

    public long removeFood(int id){
        String whereClause = "id=?";
        String[] whereAgrs = {id+""};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("foods",whereClause,whereAgrs);
    }

    public Food getFoodById(int id){
        String whereClause = "id=?";
        String[] whereArgs = {id+""};
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor rs = db.rawQuery("SELECT * FROM users where username = 'domiee13'",null,null);
        Cursor rs = db.query("foods", null, whereClause, whereArgs, null, null, null);
        if (rs.moveToFirst() && rs.getCount()>=1) {
            rs.moveToFirst();
            int foodid = rs.getInt(0);
            int user_id = rs.getInt(1);
            String name = rs.getString(2);
            float kcal = rs.getFloat(3);
            String date = rs.getString(4);
            return new Food(foodid,user_id,kcal,name,date);
        }
        return null;
    }

    public long addUser(User user){
        ContentValues values = new ContentValues();
        values.put("fullname",user.getFullname());
        values.put("username",user.getUsername());
        values.put("password",user.getPassword());
        values.put("weight",user.getWeight());
        values.put("height",user.getHeight());
        values.put("age",user.getAge());
        values.put("gender",user.getGender());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("users",null,values);
    }

    public int deleteFood(int id){
        String whereClause = "id=?";
        String[] whereAgrs = {id+""};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("foods",whereClause,whereAgrs);
    }
    public int deleteFoodByName(String name){
        String whereClause = "name=?";
        String[] whereAgrs = {name+""};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("foods",whereClause,whereAgrs);
    }

    public int updateFoodByName(String name,float kcal){
        String whereClause = "name=?";
        String[] whereAgrs = {name};
        ContentValues values = new ContentValues();
        values.put("kcal",kcal);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("foods",values,whereClause,whereAgrs);
    }
}

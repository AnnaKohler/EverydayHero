package com.example.student.everydayhero;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Anna on 28.03.2017.
 */

public class DBHandler extends SQLiteOpenHelper implements IDataBaseHandler {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="everydayhero";

    private static final String TABLE_OBJECTIVES = "objectives";

    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "obj_title";
    private static final String KEY_GROUP = "obj_group";
    private static final String KEY_DETAILS = "obj_details";
    private static final String KEY_DURATION = "obj_duration";
    private static final String KEY_DONE_DAYS = "obj_done_days";
    private static final String KEY_BEG_DATE="obj_begin_date";

    //TABLE USER:

    private static final String TABLE_USER="user";
    private static final String KEY_NAME = "username";
    private static final String KEY_AGE = "age";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_LASTSEEN = "lastseen";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_OBJECTIVES_TABLE= "CREATE TABLE "+TABLE_OBJECTIVES+"("+
                KEY_ID+" INTEGER PRIMARY KEY,"+ KEY_TITLE + " TEXT,"
                + KEY_GROUP + " TEXT," + KEY_DETAILS + " TEXT," +KEY_BEG_DATE +" INTEGER,"
                +KEY_DURATION +" INTEGER,"+ KEY_DONE_DAYS +" INTEGER" +")";

        db.execSQL(CREATE_OBJECTIVES_TABLE);

        String CREATE_USER_TABLE="CREATE TABLE " + TABLE_USER + "(" + KEY_ID +" bool PRIMARY KEY DEFAULT TRUE," + KEY_NAME+ " TEXT," +KEY_AGE+ " INTEGER,"
                +KEY_HEIGHT + " REAL," + KEY_WEIGHT + " REAL,"
                + KEY_LASTSEEN +" INTEGER" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_OBJECTIVES);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " +TABLE_USER);
        onCreate(db);
    }

    @Override
    public void addObjective(Objective objective) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(KEY_TITLE, objective.getTitle());
        values.put(KEY_GROUP, objective.getGroup());
        values.put(KEY_DETAILS, objective.getDetails());
        values.put(KEY_DURATION, objective.getDuration());
        values.put(KEY_BEG_DATE, objective.getBeginDate().getTime());
        values.put(KEY_DONE_DAYS, objective.getDone());

        db.insert(TABLE_OBJECTIVES, null, values);
        db.close();
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put(KEY_NAME, user.getName());
        values.put(KEY_AGE, user.getAge());
        values.put(KEY_HEIGHT, user.getHeight());
        values.put(KEY_WEIGHT, user.getWeight());
        values.put(KEY_LASTSEEN, user.getLastSeen().getTime());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    @Override
    public Objective getObjective(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_OBJECTIVES, new String[]{ KEY_TITLE, KEY_GROUP, KEY_DETAILS, KEY_BEG_DATE, KEY_DURATION,KEY_DONE_DAYS},
                KEY_ID+" = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        Objective objective = new Objective();
            if(cursor.moveToFirst()) {
                //TODO: DAFUQ вооще курсор и как заставить его все это считать
                //TODO: Убрать логи
                objective.setID(Integer.parseInt(cursor.getString(0)));
                Log.e("OBJID", "getObjective: " +objective.getID());
                objective.setTitle(cursor.getString(1));
                Log.e("OBJTITLE", "getObjective: " +objective.getTitle());
                objective.setGroup(cursor.getString(2));
                Log.e("OBJID", "getObjective: " +objective.getID());
                objective.setDetails(cursor.getString(3));
                Log.e("OBJDETAILS", "getObjective: " +objective.getDetails());
                objective.setBeginDate(new Date(Long.valueOf(cursor.getString(4))));
                Log.e("OBJBEGDATE", "getObjective: " +objective.getBeginDate().toString());
                objective.setDuration(Integer.parseInt(cursor.getString(5)));
                Log.e("OBJDURATION", "getObjective: " +objective.getDuration());
                objective.setDone(Integer.parseInt(cursor.getString(6)));
                Log.e("OBJIDONE", "getObjective: " +objective.getDone());
            }
        return objective;
    }

    @Override
    public User getUserInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_USER, null);
        User user = new User();
        if(cursor.moveToFirst()){
            user.setName(cursor.getString(1));
            user.setAge(Integer.parseInt(cursor.getString(2)));
            user.setHeight(Float.parseFloat(cursor.getString(3)));
            user.setWeight(Float.parseFloat(cursor.getString(4)));
            user.setLastSeen(new Date(Long.valueOf(cursor.getString(5))));
            user.getLastSeen().setHours(0);
        }
        return user;
    }

    @Override
    public List<Objective> getAllObjectives() {
        List<Objective> objectiveList=new ArrayList<>();
        String selectQuery="SELECT * FROM "+TABLE_OBJECTIVES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {

                Objective objective=new Objective();
                objective.setID(Integer.parseInt(cursor.getString(0)));
                objective.setTitle(cursor.getString(1));
                objective.setGroup(cursor.getString(2));
                objective.setDetails(cursor.getString(3));
                objective.setBeginDate(new Date(Long.valueOf(cursor.getString(4))));
                objective.setDuration(Integer.parseInt(cursor.getString(5)));
                objective.setDone(Integer.parseInt(cursor.getString(6)));

                objectiveList.add(objective);
            } while (cursor.moveToNext());
        }
        return objectiveList;
    }


    @Override
    public int updateObjective(Objective objective) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(KEY_TITLE, objective.getTitle());
        cv.put(KEY_GROUP, objective.getGroup());
        cv.put(KEY_DURATION, objective.getDuration());
        cv.put(KEY_BEG_DATE, objective.getBeginDate().getTime());
        cv.put(KEY_DONE_DAYS, objective.getDone());

        return db.update(TABLE_OBJECTIVES, cv, KEY_ID+" = ?", new String[]{String.valueOf(objective.getID())});

    }
    public int updateUser(User user){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, user.getName());
        cv.put(KEY_AGE, user.getAge());
        cv.put(KEY_HEIGHT, user.getHeight());
        cv.put(KEY_AGE, user.getWeight());
        cv.put(KEY_LASTSEEN, user.getLastSeen().getTime());

        return db.update(TABLE_USER, cv, null, null);
    }

    @Override
    public void deleteObjective(Objective objective) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_OBJECTIVES, KEY_ID+" = ?", new String[]{String.valueOf(objective.getID())});
        db.close();
    }

    @Override
    public void deleteUserInfo(User user) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_OBJECTIVES, null, null);
        db.close();
    }

    @Override
    public int getObjectivesCount() {

        String countQuery="SELECT * FROM "+ TABLE_OBJECTIVES;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count;

    }


    public boolean checkForTables(String tableName){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " +tableName, null);

        if(cursor != null){

            cursor.moveToFirst();

            int count = cursor.getInt(0);

            if(count > 0){
                return true;
            }

            cursor.close();
        }
        return false;
    }
}

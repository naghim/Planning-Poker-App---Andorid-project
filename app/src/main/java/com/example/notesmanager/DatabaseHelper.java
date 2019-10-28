package com.example.notesmanager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Votes.db";
    public final String TABLE_USER = "users_table";
    public final String COL_USER_1 = "USER_ID";
    public final String COL_USER_2 = "USER_NAME";

    public final String TABLE_VOTES = "votes_table";
    public final String COL_VOTES_1 = "VOTE_ID";
    public final String COL_VOTES_2 = "USER_ID";
    public final String COL_VOTES_3 = "TASK_ID";
    public final String COL_VOTES_4 = "VOTE_VALUE";

    public final String TABLE_TASKS = "tasks_table";
    public final String COL_TASKS_1 = "TASK_ID";
    public final String COL_TASKS_2 = "TASK_DESCRIPTION";

    /* Constructor */
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     *  Creates the database */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + " (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_NAME TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_TASKS + " (TASK_ID INTEGER PRIMARY KEY AUTOINCREMENT, TASK_DESCRIPTION TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_VOTES + " (VOTE_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " USER_ID INTEGER, TASK_ID INTEGER, VOTE_VALUE INTEGER, " +
                "FOREIGN KEY (USER_ID) REFERENCES " +TABLE_USER+" (USER_ID), " +
                " FOREIGN KEY (TASK_ID) REFERENCES " +TABLE_TASKS+ " (TASK_ID))");
    }

    public void insertDummyData(){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TASKS_2, "Alexa");
        db.insert(TABLE_USER, null, contentValues);

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(COL_TASKS_2, "Database design.");
        db.insert(TABLE_TASKS, null, contentValues1);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(COL_VOTES_2, 1);
        contentValues2.put(COL_VOTES_3, 1);
        contentValues2.put(COL_VOTES_4, 5);
        db.insert(TABLE_VOTES, null, contentValues2);
    }

    /**
     * On upgrade
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTES);
        this.onCreate(db);
    }

    /**
     * Inserts a new user to the database
     * @param userName
     * @return boolean
     */
    public boolean insertUser(String userName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_2, userName);
        long result = db.insert(TABLE_USER, null, contentValues);
        if(result == -1){
            return false;
        }

        return true;
    }

    /**
     * Inserts a new vote into the votes table
     * @param userID
     * @param taskID
     * @param voteValue
     * @return
     */
    public boolean insertVotes(int userID, int taskID, int voteValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_VOTES_2, userID);
        contentValues.put(COL_VOTES_3, taskID);
        contentValues.put(COL_VOTES_4, voteValue);
        long result = db.insert(TABLE_VOTES, null, contentValues);
        if(result == -1){
            return false;
        }

        return true;
    }

    /**
     * Returns a Cursor with the votes. Only returns votes for the specified task_id.
     * @param task_id
     * @return
     */
    public Cursor fetchVotes(int task_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select u."+COL_USER_2+", t."+COL_TASKS_2+", v."+COL_VOTES_4+" from "+ TABLE_VOTES +" v join "+TABLE_USER+" u on v."+COL_VOTES_2+" = u."+COL_USER_1+" join "+ TABLE_TASKS +" t on v."+COL_VOTES_3+" = t."+COL_TASKS_1+" where v."+COL_VOTES_3+" = ?", new String[]{Integer.toString(task_id)});
        return res;
    }

    /**
     * Returns all tasks that haven't received a vote from the user.
     * @param user_id
     * @return
     */
    public Cursor fetchTasks(int user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select t."+COL_TASKS_1+", t."+COL_TASKS_2 +" from "+ TABLE_TASKS+ " t natural join " + TABLE_VOTES +
                " v EXCEPT select t."+ COL_TASKS_1+", t."+COL_TASKS_2+" from "+TABLE_TASKS + " t natural join " + TABLE_VOTES + " v where v."+ COL_VOTES_2 + " = ?",new String[]{Integer.toString(user_id)});
        return res;
    }

    public Cursor getLastUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_USER+" ORDER BY USER_ID DESC LIMIT 1", null);
        return res;
    }
}






package com.example.project2_chohss;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class QuizDatabase extends SQLiteOpenHelper {

    //db name
    public static String DATABASE_QUESTION = "questionBank.db";
    //db version
    private static final int DATABASE_VERSION = 1;
    //db table name
    private static final String TABLE_QUESTION = "QuestionBank";

    private static final String KEY_ID = "id";
    private static final String QUESTION = "question";
    private static final String CHOICE1 = "choice1";
    private static final String CHOICE2 = "choice2";
    private static final String CHOICE3 = "choice3";
    private static final String CHOICE4 = "choice4";
    private static final String ANSWER = "answer";

    //create question table in this query
    private static final String CREATE_TABLE_QUESTION = "CREATE TABLE " + TABLE_QUESTION + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + QUESTION + " TEXT," + CHOICE1 + " TEXT, " + CHOICE2 + " TEXT, " + CHOICE3 + " TEXT, " + CHOICE4 + " TEXT, " + ANSWER + " TEXT);";

    public QuizDatabase(Context context) {
        super(context, DATABASE_QUESTION, null, DATABASE_VERSION);
    }

    //call method when database is accessed but not created
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUESTION); // create question table
    }

    //call method when database is modified
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_QUESTION); // drop table if exists
        onCreate(db);
    }

    public long addInitialQuestion (question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        //create content values
        ContentValues values = new ContentValues();
        values.put(QUESTION, question.getQuestion());
        values.put(CHOICE1, question.getChoice(0));
        values.put(CHOICE2, question.getChoice(1));
        values.put(CHOICE3, question.getChoice(2));
        values.put(CHOICE4, question.getChoice(3));
        values.put(ANSWER, question.getAnswer());
        //insert row in question table
        long insert = db.insert(TABLE_QUESTION, null, values);
        return insert;
    }


    //used to get data from database and save in an array list
    public List<question> getAllQuestionsList() {
        List<question> questionArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        //looping through all records and adding to the list
        if (c.moveToFirst()) {
            do {
                question question = new question();

                String questText= c.getString(c.getColumnIndex(QUESTION));
                question.setQuestion(questText);

                String choice1Text= c.getString(c.getColumnIndex(CHOICE1));
                question.setChoice(0,choice1Text);

                String choice2Text= c.getString(c.getColumnIndex(CHOICE2));
                question.setChoice(1,choice2Text);

                String choice3Text= c.getString(c.getColumnIndex(CHOICE3));
                question.setChoice(2,choice3Text);

                String choice4Text= c.getString(c.getColumnIndex(CHOICE4));
                question.setChoice(3,choice4Text);

                String answerText= c.getString(c.getColumnIndex(ANSWER));
                question.setAnswer(answerText);

                //add to question list
                questionArrayList.add(question);
            } while (c.moveToNext());
            Collections.shuffle(questionArrayList);
        }
        return questionArrayList;
    }
}

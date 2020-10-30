package com.example.project2_chohss;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    private QuestionBankActivity mQuestionLibrary = new QuestionBankActivity();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button btnChoice1;
    private Button btnChoice2;
    private Button btnChoice3;
    private Button btnChoice4;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        btnChoice1 = (Button)findViewById(R.id.choice1);
        btnChoice2 = (Button)findViewById(R.id.choice2);
        btnChoice3 = (Button)findViewById(R.id.choice3);
        btnChoice4 = (Button)findViewById(R.id.choice4);

        mQuestionLibrary.initQuestions(getApplicationContext());
        updateQuestion();

        updateScore(mScore);
    }

    private void updateQuestion(){

        if(mQuestionNumber < mQuestionLibrary.getLength() ){
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            btnChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            btnChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            btnChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            btnChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber,4));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }
        else {
            Toast.makeText(QuizActivity.this, "Quiz has been completed!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this, HighScoreActivity.class);
            intent.putExtra("score", mScore);
            startActivity(intent);
        }
    }

    //current total score
    private void updateScore(int point) {
        mScoreView.setText(""+mScore+"/" + mQuestionLibrary.getLength());
    }

    public void onClick(View view) {

        Button answer = (Button) view;
        //if answer is correct, increase the score
        if (answer.getText().equals(mAnswer)){
            mScore = mScore + 1;
            Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(QuizActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
        //current total score for the user
        updateScore(mScore);
        //move to next question
        updateQuestion();
    }
}

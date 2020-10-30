package com.example.project2_chohss;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class QuestionBankActivity {
    //declare objects
    List <question> list = new ArrayList<>();
    QuizDatabase quizData;

    public int getLength(){
        return list.size();
    }

    //retrieves the question
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    //retrieves all the choices for the question
    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num-1);
    }

    //returns correct answer
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }

    public void initQuestions(Context context) {
        quizData = new QuizDatabase(context);
        list = quizData.getAllQuestionsList();

        //list of questions to add in the questions database
        if (list.isEmpty()) {
            quizData.addInitialQuestion(new question("Who is the CEO of GOOGLE?", new String[]{"Mark Zuckerberg", "Sundar Pichai", "Elon Musk", "Donald Trump"}, "Sundar Pichai"));
            quizData.addInitialQuestion(new question("When was Android Studio released?", new String[]{"May 2013", "December 2014", "July 1998", "January 2015"}, "December 2014"));
            quizData.addInitialQuestion(new question("Where is Google's HQ located?", new String[]{"Mountain View, CA", "Manhattan, NY", "Long Island, NY", "Pheonix, AZ"}, "Mountain View, CA"));
            quizData.addInitialQuestion(new question("Who was the first CEO of Google?", new String[]{"George Washington", "Sundar Pichai", "Eric Schmidt", "Jeff Bezos"}, "Eric Schmidt"));
            quizData.addInitialQuestion(new question("What year did GOOGLE release?", new String[]{"2007", "2019", "1996", "1998"}, "1996"));

            list = quizData.getAllQuestionsList();

        }
    }
}

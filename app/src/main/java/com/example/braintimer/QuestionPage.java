package com.example.braintimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuestionPage extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score =0;
    int numberofQuestion=0;
    TextView scoretext;
    TextView questionTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    GridLayout gridLayout;

    public void playAgain(View view){
        gridLayout.setVisibility(View.VISIBLE);
    resultTextView.setVisibility(View.INVISIBLE);
    score= 0;
    numberofQuestion =0;
        scoretext.setText(Integer.toString(score)+"/"+Integer.toString(numberofQuestion));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);

        updateTimer(60);
    new CountDownTimer(60100,1000){

        @Override
        public void onTick(long l) {
            updateTimer((int) l / 1000);
        }

        @Override
        public void onFinish() {
            resultTextView.setText("Times Out");
            resultTextView.setVisibility(View.VISIBLE);
            playAgainButton.setVisibility(View.VISIBLE);
            gridLayout.setVisibility(View.INVISIBLE);
        }
    }.start();

}

    public void chooseAnswer(View view){
        resultTextView.setVisibility(View.VISIBLE);
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("CORRECT");
            score++;
        }else{
            resultTextView.setText("Wrong :(");
        }
        numberofQuestion++;
        scoretext.setText(Integer.toString(score)+"/"+Integer.toString(numberofQuestion));
        newQuestion();
    }

    CountDownTimer countDownTimer;
    TextView timertextView;

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds= secondsLeft-(minutes*60);

        String secondsString = Integer.toString(seconds);
        if(seconds <= 9){
            secondsString = "0"+ secondsString ;
        }
        timertextView.setText(Integer.toString(minutes)+ ":" + secondsString);

    }

    public void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        questionTextView.setText(String.valueOf(a)+" + "+String.valueOf(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();
        for(int i =0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else {
                int wrongAnswer= random.nextInt(41);
                while (wrongAnswer == a+b){
                    wrongAnswer= random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);
        questionTextView = findViewById(R.id.sumTextView);

        resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setVisibility(View.INVISIBLE);
        scoretext = findViewById(R.id.scoreTextView);

         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
         playAgainButton = findViewById(R.id.playAgainButton);

        timertextView = (TextView) findViewById(R.id.timerTextView);
        gridLayout = findViewById(R.id.gridLayout);

        playAgain(findViewById(R.id.playAgainButton));

    }
}
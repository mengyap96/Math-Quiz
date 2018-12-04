package com.example.user.mathquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txt_timer, txt_question, txt_score;
    private CountDownTimer countDownTimer;
    private long timeLeft;
    private int number1, number2 , operator, answer, answerPos, dummy1, dummy2, dummy3, score = 0;
    private Button button_a, button_b, button_c, button_d;
    Random random = new Random();

    private int difficult, question_difficult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);

        txt_timer = (TextView) findViewById(R.id.txt_timer);
        txt_question = (TextView) findViewById(R.id.txt_question);
        txt_score = (TextView) findViewById(R.id.txt_score);

        button_a = (Button) findViewById(R.id.button_a);
        button_b = (Button) findViewById(R.id.button_b);
        button_c = (Button) findViewById(R.id.button_c);
        button_d = (Button) findViewById(R.id.button_d);

        button_a.setOnClickListener(this);
        button_b.setOnClickListener(this);
        button_c.setOnClickListener(this);
        button_d.setOnClickListener(this);

        difficult = getIntent().getExtras().getInt("difficult");
        question_difficult = difficult*10;


        updateQuestion();
        startTimer();
    }

    public void startTimer(){

        timeLeft = 30000; //30second answering time for each turn
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                Intent intent_scorebaord = new Intent(QuizActivity.this,ScoreBoardActivity.class);
                intent_scorebaord.putExtra("score",score);
                intent_scorebaord.putExtra("difficult",difficult);
                startActivity(intent_scorebaord);
                finish();
            }
        }.start();
    }

    public void updateTimer(){
        int seconds = (int) timeLeft /1000;
        String timeLeftText = "" + seconds;

        txt_timer.setText(timeLeftText);
    }

    public void updateQuestion(){


        number1 = random.nextInt(question_difficult) + 1;
        number2 = random.nextInt(question_difficult) + 1;

        txt_score.setText(""+score);

        //Randomize the operator of question
        operator = random.nextInt(4);
        switch(operator){
            case 0:
                answer = number1 + number2;
                txt_question.setText(number1 + " + " + number2 + " =");
                break;

            case 1:
                //Prevent number from negative number
                do{
                    number1 = random.nextInt(question_difficult) + 1;
                    number2 = random.nextInt(question_difficult) + 1;
                }while(number1-number2<=0);
                answer = number1 - number2;
                txt_question.setText(number1 + " - " + number2 + " =");
                break;

            case 2:
                answer = number1 * number2;
                txt_question.setText(number1 + " * " + number2 + " =");
                break;

            case 3:
                //Prevent answer from decimal number
                do{
                    number1 = random.nextInt(question_difficult) + 1;
                    number2 = random.nextInt(question_difficult) + 1;
                }while(number1%number2!=0);

                answer = number1 / number2;
                txt_question.setText(number1 + " / " + number2 + " =");
                break;
        }

        //Produce wrong answer without repeating number
        answerPos = random.nextInt(4);
        do {
            dummy1 = answer + random.nextInt(question_difficult);
            dummy2 = answer * random.nextInt(question_difficult);
            dummy3 = answer - random.nextInt(question_difficult)+1;

            while(dummy3<=0){
                dummy3 = answer - random.nextInt(question_difficult)+1;
            }

        }while(dummy1==answer||dummy2==answer||dummy3==answer||dummy2==dummy1||dummy2==dummy3||dummy3==dummy1);

        //Randomize the answer position
        switch(answerPos){
            case 0:
                button_a.setText(""+answer);
                button_b.setText(""+dummy1);
                button_c.setText(""+dummy2);
                button_d.setText(""+dummy3);
                break;

            case 1:
                button_a.setText(""+dummy2);
                button_b.setText(""+answer);
                button_c.setText(""+dummy3);
                button_d.setText(""+dummy1);
                break;

            case 2:
                button_a.setText(""+dummy3);
                button_b.setText(""+dummy1);
                button_c.setText(""+answer);
                button_d.setText(""+dummy2);
                break;

            case 3:
                button_a.setText(""+dummy3);
                button_b.setText(""+dummy2);
                button_c.setText(""+dummy1);
                button_d.setText(""+answer);
                break;
        }
    }

    @Override
    public void onClick(View view) {

        final MediaPlayer correct_sound = MediaPlayer.create(this, R.raw.correct);
        final MediaPlayer wrong_sound = MediaPlayer.create(this, R.raw.wrong);


        switch (view.getId())
        {
            case R.id.button_a:
                if(answerPos==0){
                    score+= question_difficult;
                    correct_sound.start();
                }
                else{
                    wrong_sound.start();
                    score-= question_difficult /2;
                }
                break;

            case R.id.button_b:
                if(answerPos==1){
                    score+= question_difficult;
                    correct_sound.start();
                }
                else{
                    wrong_sound.start();
                    score-= question_difficult /2;
                }
                break;

            case R.id.button_c:
                if(answerPos==2){
                    score+= question_difficult;
                    correct_sound.start();
                }
                else{
                    wrong_sound.start();
                    score-= question_difficult /2;
                }
                break;

            case R.id.button_d:
                if(answerPos==3){
                    score+= question_difficult;
                    correct_sound.start();
                }
                else{
                    wrong_sound.start();
                    score-= question_difficult /2;
                }
                break;
        }

        updateQuestion();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
    }
}

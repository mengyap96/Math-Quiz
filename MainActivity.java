package com.example.user.mathquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_play, button_highscore, button_difficult, button_exit;
    private int difficult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        //Make the application fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        button_play = (Button) findViewById(R.id.button_play);
        button_highscore = (Button) findViewById(R.id.button_highscore);
        button_difficult = (Button) findViewById(R.id.button_difficult);
        button_exit = (Button) findViewById(R.id.button_exit);


        difficult = 1;


        button_play.setOnClickListener(this);
        button_highscore.setOnClickListener(this);
        button_difficult.setOnClickListener(this);
        button_exit.setOnClickListener(this);



    }

    public void createExitDialog(){
        AlertDialog.Builder exit_confirm = new AlertDialog.Builder(this);
        exit_confirm.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = exit_confirm.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_play:
                Intent intent_quiz = new Intent(MainActivity.this,QuizActivity.class);
                intent_quiz.putExtra("difficult",difficult);
                startActivity(intent_quiz);
                break;

            case R.id.button_highscore:
                Intent intent_highscore = new Intent(MainActivity.this,HighscoreActivity.class);
                startActivity(intent_highscore);
                break;

            case R.id.button_difficult:
                if(difficult == 1){
                    button_difficult.setText("DIFFICULT    NORMAL");
                    difficult = 2;

                }
                else if(difficult == 2){
                    button_difficult.setText("DIFFICULT    HARD");
                    difficult = 3;
                }
                else{
                    button_difficult.setText("DIFFICULT     EASY");
                    difficult = 1;
                }
                break;

            case R.id.button_exit:
                createExitDialog();
                break;
        }
    }

    @Override
    public void onBackPressed(){
        createExitDialog();
    }
}

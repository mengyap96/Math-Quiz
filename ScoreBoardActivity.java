package com.example.user.mathquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ScoreBoardActivity extends AppCompatActivity implements View.OnClickListener, RequestNameDialog.RequestNameDialogListener{

    private TextView txt_result, buffer;
    private Button button_play_again, button_highscore_result, button_main_menu;
    private int difficult;
    private HighscoreDataSource dataSource;
    private String name;
    private int score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score_board);

        dataSource = new HighscoreDataSource(this);

        txt_result = (TextView) findViewById(R.id.txt_result);
        buffer = (TextView) findViewById(R.id.buffer);
        button_play_again = (Button) findViewById(R.id.button_play_again);
        button_highscore_result = (Button) findViewById(R.id.button_highscore_result);
        button_main_menu = (Button) findViewById(R.id.button_main_menu);

        score = getIntent().getExtras().getInt("score");
        difficult = getIntent().getExtras().getInt("difficult");

        txt_result.setText(""+score);

        button_play_again.setOnClickListener(this);
        button_main_menu.setOnClickListener(this);
        button_highscore_result.setOnClickListener(this);

        dataSource.open();


        if(dataSource.checkHighscore(score)==true){
            openDialog();
        }
//        name = buffer.getText().toString();
//        dataSource.createHighscore(name,score);



    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_play_again:
                Intent intent_play_again = new Intent(ScoreBoardActivity.this, QuizActivity.class);
                intent_play_again.putExtra("difficult",difficult);
                startActivity(intent_play_again);
                finish();
                break;

            case R.id.button_main_menu:
                finish();
                break;

            case R.id.button_highscore_result:
                Intent intent_highscore = new Intent(ScoreBoardActivity.this,HighscoreActivity.class);
                startActivity(intent_highscore);
                finish();
                break;
        }
    }

    public void openDialog(){
        RequestNameDialog dialog = new RequestNameDialog();
        dialog.show(getSupportFragmentManager(),"request name dialog");
    }

    @Override
    public void applyTexts(String string){
        name = string;
        dataSource.createHighscore(name,score);
        dataSource.close();

    }

}

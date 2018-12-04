package com.example.user.mathquiz;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HighscoreActivity extends AppCompatActivity {

    private HighscoreDataSource dataSource;
    private SQLiteDatabase db;

    private TextView name, score;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_highscore);



        name = (TextView) findViewById(R.id.textView);
        score = (TextView) findViewById(R.id.txt_name_highscore);
        listView = (ListView) findViewById(R.id.listView);

        dataSource = new HighscoreDataSource(this);
        dataSource.open();

        List<Highscore> values = dataSource.getAllHighscore();

        HighscoreListAdapter adapter = new HighscoreListAdapter(this, R.layout.list_highscore, (ArrayList<Highscore>) values);

        listView.setAdapter(adapter);

        dataSource.close();

    }


}

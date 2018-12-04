package com.example.user.mathquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class HighscoreListAdapter extends ArrayAdapter<Highscore> {
    private static final String TAG = "HighscoreListAdapter";

    private Context mContext;
    int mResource;
    ArrayList<Highscore> mObjects;



    public HighscoreListAdapter(Context context, int resource, ArrayList<Highscore> objects){
        super(context,resource, (List<Highscore>) objects);
        mContext = context;
        mResource = resource;
        mObjects = objects;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        long id = getItem(position).getId();
        String name = getItem(position).getName();
        int score = getItem(position).getScore();

        Highscore highscore = new Highscore(id,name,score);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView txt_rank_list = (TextView) convertView.findViewById(R.id.txt_rank_list);
        TextView txt_name_list = (TextView) convertView.findViewById(R.id.txt_name_list);
        TextView txt_score_list = (TextView) convertView.findViewById(R.id.txt_score_list);

        txt_rank_list.setText(""+(position+1));
        txt_name_list.setText(name);
        txt_score_list.setText(""+score);

        return convertView;

    }

    @Override
    public int getCount() {
        if(mObjects.size()<10)
            return mObjects.size();
        else
            return 10;
    }

}

package com.example.android.miwok;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WordArrayAdapter extends ArrayAdapter<Word> {

    private int viewBgColor;

    public WordArrayAdapter(Activity context, ArrayList<Word> Words,int bgColor){
        super(context,0,Words);
        viewBgColor = bgColor;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word word = getItem(position);

        TextView miwokTranslationtextView = listItemView.findViewById(R.id.view1);
        miwokTranslationtextView.setText(word.getMiwokTranslation());


        TextView defaultTranslationtextView = listItemView.findViewById(R.id.view2);
        defaultTranslationtextView.setText(word.getDefaultTranslation());

        ImageView listImage = listItemView.findViewById(R.id.listImage);
        if(word.getListImage() != 0){
            listImage.setImageResource(word.getListImage());
            listImage.setBackgroundColor(Color.parseColor("#FFF7DA"));
        }
        else{
            listImage.setVisibility(View.GONE);
        }
        listItemView.setBackgroundColor(viewBgColor);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            MediaPlayer.create(getContext(),word.getListItemAudio()).start();
            }
        });

        return listItemView;
    }
}

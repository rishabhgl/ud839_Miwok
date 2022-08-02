package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WordArrayAdapter extends ArrayAdapter<Word> {

    public WordArrayAdapter(Activity context, ArrayList<Word> Words){
        super(context,0,Words);
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

        return listItemView;
    }
}

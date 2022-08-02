package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        ListView colorsList = findViewById(R.id.list);

        ArrayList<Word> colors = new ArrayList<Word>(8);
        colors.add(new Word("red","wetetti"));
        colors.add(new Word("green","chokokki"));
        colors.add(new Word("brown","takaakki"));
        colors.add(new Word("grey","topoppi"));
        colors.add(new Word("black","tululli"));
        colors.add(new Word("white","kelelli"));
        colors.add(new Word("dusty yellow","ṭopiisә"));
        colors.add(new Word("mustard yellow","chiwiiṭә"));

        WordArrayAdapter colorsAdapter = new WordArrayAdapter(this,colors);
        colorsList.setAdapter(colorsAdapter);
    }


}
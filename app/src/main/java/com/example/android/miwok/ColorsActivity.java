package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
        colors.add(new Word("red","wetetti",R.drawable.color_red));
        colors.add(new Word("green","chokokki",R.drawable.color_green));
        colors.add(new Word("brown","takaakki",R.drawable.color_brown));
        colors.add(new Word("grey","topoppi",R.drawable.color_gray));
        colors.add(new Word("black","tululli",R.drawable.color_black));
        colors.add(new Word("white","kelelli",R.drawable.color_white));
        colors.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow));
        colors.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow));

        WordArrayAdapter colorsAdapter = new WordArrayAdapter(this,colors, Color.parseColor("#8800A0"));
        colorsList.setAdapter(colorsAdapter);
    }


}
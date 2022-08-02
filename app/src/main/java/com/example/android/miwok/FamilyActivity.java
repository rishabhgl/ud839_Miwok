package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        ArrayList<Word> familyMembers = new ArrayList<Word>();
        familyMembers.add(new Word("father","әpә"));
        familyMembers.add(new Word("mother","әṭa"));
        familyMembers.add(new Word("son","angsi"));
        familyMembers.add(new Word("daughter","tune"));
        familyMembers.add(new Word("older brother","taachi"));
        familyMembers.add(new Word("younger brother","chalitti"));
        familyMembers.add(new Word("older sister","teṭe"));
        familyMembers.add(new Word("younger sister","kolliti"));
        familyMembers.add(new Word("grandmother","ama"));
        familyMembers.add(new Word("grandfather","paapa"));

        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this,familyMembers);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);


    }
}
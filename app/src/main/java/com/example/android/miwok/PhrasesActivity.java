package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = null;

    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager miwokAudioManager;

    private AudioManager.OnAudioFocusChangeListener miwokAudioFocusChangeListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        miwokAudioManager= (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        miwokAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int audioFocusChange) {
                switch (audioFocusChange){
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                        mediaPlayer.pause();
                        break;
                    case AudioManager.AUDIOFOCUS_GAIN:
                        mediaPlayer.start();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        releaseMediaPlayer();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        mediaPlayer.pause();
                        break;

                }
            }
        };

        final ArrayList<Word> phrases = new ArrayList<Word>();
        phrases.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        phrases.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        phrases.add(new Word("My name is..." ,"oyaaset...",R.raw.phrase_my_name_is));
        phrases.add(new Word("How are you feeling?" ,"michәksәs?",R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        phrases.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        phrases.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        phrases.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        phrases.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        phrases.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));


        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this,phrases, Color.parseColor("#16AFCA"));
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(PhrasesActivity.this,phrases.get(i).getListItemAudio());
                requestAudio();
            }
        });
    }

    public void releaseMediaPlayer()
    {
        if (mediaPlayer != null )
        {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void requestAudio()
    {
        int audioRequest = miwokAudioManager.requestAudioFocus(miwokAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        switch(audioRequest)
        {
            case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(completionListener);
                break;
            case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                Toast.makeText(this,"Audio Focus Request failed!!",Toast.LENGTH_SHORT).show();
                break;
            case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                Toast.makeText(this,"Audio will be played shortly!",Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(completionListener);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
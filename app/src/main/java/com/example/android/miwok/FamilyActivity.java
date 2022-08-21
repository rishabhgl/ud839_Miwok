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

public class FamilyActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer = null;
    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private boolean mAudioFocusPlaybackDelayed;

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
                        mediaPlayer.seekTo(0);
                        break;
                    case AudioManager.AUDIOFOCUS_GAIN:
                        if (mAudioFocusPlaybackDelayed) {
                            mAudioFocusPlaybackDelayed = false;}
                        mediaPlayer.start();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS:
                        releaseMediaPlayer();
                        break;
                    case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                        break;

                }
            }
        };

        final ArrayList<Word> familyMembers = new ArrayList<Word>();
        familyMembers.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        familyMembers.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        familyMembers.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        familyMembers.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        familyMembers.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        familyMembers.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        familyMembers.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        familyMembers.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        familyMembers.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        familyMembers.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordArrayAdapter itemsAdapter = new WordArrayAdapter(this,familyMembers, Color.parseColor("#379237"));
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(FamilyActivity.this, familyMembers.get(i).getListItemAudio());
                mediaPlayer.setOnCompletionListener(completionListener);
                requestAudio();
            }
        });
    }


    public void releaseMediaPlayer()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
            miwokAudioManager.abandonAudioFocus(miwokAudioFocusChangeListener);
        }
    }

    public void requestAudio()
    {
        int audioRequest = miwokAudioManager.requestAudioFocus(miwokAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        switch(audioRequest)
        {
            case AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                mediaPlayer.start();

                break;
            case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                releaseMediaPlayer();
                Toast.makeText(this,"Audio Focus Request failed!!",Toast.LENGTH_SHORT).show();
                break;
            case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                Toast.makeText(this,"Audio will be played shortly!",Toast.LENGTH_SHORT).show();
                mAudioFocusPlaybackDelayed = true;
                mediaPlayer.start();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
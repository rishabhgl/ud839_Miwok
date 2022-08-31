package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ColorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ColorsFragment extends Fragment {

    public ColorsFragment() {
        // Required empty public constructor
    }
    public static ColorsFragment newInstance(String param1, String param2) {
        ColorsFragment fragment = new ColorsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    MediaPlayer mediaPlayer  = null;
    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager miwokAudioManager;

    private AudioManager.OnAudioFocusChangeListener miwokAudioFocusChangeListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        miwokAudioManager= (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListView colorsList = (ListView) inflater.inflate(R.layout.list_layout, container, false);


        final ArrayList<Word> colors = new ArrayList<Word>(8);
        colors.add(new Word("red","wetetti",R.drawable.color_red,R.raw.color_red));
        colors.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        colors.add(new Word("brown","takaakki",R.drawable.color_brown,R.raw.color_brown));
        colors.add(new Word("grey","topoppi",R.drawable.color_gray,R.raw.color_gray));
        colors.add(new Word("black","tululli",R.drawable.color_black,R.raw.color_black));
        colors.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        colors.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        colors.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        WordArrayAdapter colorsAdapter = new WordArrayAdapter(getActivity(),colors, Color.parseColor("#8800A0"));
        colorsList.setAdapter(colorsAdapter);
        colorsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mediaPlayer  = MediaPlayer.create(getActivity(),colors.get(i).getListItemAudio());
                requestAudio();
            }
        });
        return colorsList;
    }


    public void releaseMediaPlayer(){
        if (mediaPlayer != null)
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
                Toast.makeText(getActivity(),"Audio Focus Request failed!!",Toast.LENGTH_SHORT).show();
                break;
            case AudioManager.AUDIOFOCUS_REQUEST_DELAYED:
                Toast.makeText(getActivity(),"Audio will be played shortly!",Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(completionListener);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
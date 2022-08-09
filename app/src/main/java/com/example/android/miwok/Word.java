package com.example.android.miwok;

public class Word {

    private String defaultTranslation;
    private String miwokTranslation;
    private int listImage;
    private int listItemAudio;

    public Word(String defaultTranslation,String miwokTranslation,int image,int audio){
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.listImage = image;
        this.listItemAudio = audio;
    }
    public Word(String defaultTranslation,String miwokTranslation,int audio){
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.listItemAudio = audio;
    }

    public String getDefaultTranslation(){
        return defaultTranslation;
    }
    public String getMiwokTranslation(){
        return miwokTranslation;
    }
    public int getListImage(){
        return listImage;
    }
    public int getListItemAudio(){
        return listItemAudio;
    }
}

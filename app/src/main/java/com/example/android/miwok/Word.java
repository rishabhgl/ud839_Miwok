package com.example.android.miwok;

public class Word {

    private String defaultTranslation;
    private String miwokTranslation;
    private int listImage;

    public Word(String defaultTranslation,String miwokTranslation,int image){
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.listImage = image;
    }
    public Word(String defaultTranslation,String miwokTranslation){
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
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
}

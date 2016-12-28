package com.example.android.miwok;

import static android.R.attr.category;

/**
 * Created by Lawrey on 27/12/16.
 */

public class Word {

    private String mDefaultTranslation;
    private String mMiworkTranslation;

    private int mImageResourceID = NO_IMAGE_CONSTANT;
    private String mAudioResourceID = NO_AUDIO_CONSTANT;

    private static final int NO_IMAGE_CONSTANT = -1;
    private static final String NO_AUDIO_CONSTANT = "NA";

    public Word(String defaultTransation, String miwokTranslation) {
        mDefaultTranslation = defaultTransation;
        mMiworkTranslation = miwokTranslation;
    }

    public Word(String defaultTransation, String miwokTranslation, int imageResourceID) {
        mDefaultTranslation = defaultTransation;
        mMiworkTranslation = miwokTranslation;
        mImageResourceID =  imageResourceID;
    }

    public Word(String defaultTransation, String miwokTranslation, int imageResourceID, String category) {
        mDefaultTranslation = defaultTransation;
        mMiworkTranslation = miwokTranslation;
        mImageResourceID = imageResourceID;
        mAudioResourceID = category + "_" + defaultTransation;
    }

    public Word(String defaultTransation, String miwokTranslation, String category) {
        mDefaultTranslation = defaultTransation;
        mMiworkTranslation = miwokTranslation;
        mAudioResourceID = category + "_" + defaultTransation;
        mAudioResourceID = mAudioResourceID.replace(" ", "_");
        mAudioResourceID = mAudioResourceID.replace("?", "");
        mAudioResourceID = mAudioResourceID.replace(".", "");
        mAudioResourceID = mAudioResourceID.replace("", "");
        mAudioResourceID = mAudioResourceID.toLowerCase();
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiworkTranslation;
    }

    public int getImageResourceID() {
        return mImageResourceID;
    }

    public String getAudioResourceID() {
        return mAudioResourceID;
    }

    public boolean hasImage() {
        return mImageResourceID != NO_IMAGE_CONSTANT;
    }

    public boolean hasAudio() {
        return mAudioResourceID != NO_AUDIO_CONSTANT;
    }

}

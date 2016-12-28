package com.example.android.miwok;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lawrey on 27/12/16.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int color;



    public WordAdapter(NumbersActivity numbersActivity, ArrayList<Word> words, int color) {
        super(numbersActivity, 0, words);
        this.color = color;
    }

    public WordAdapter(FamilyMembersActivity familyMembersActivity, ArrayList<Word> words, int color) {
        super(familyMembersActivity, 0, words);
        this.color = color;
    }

    public WordAdapter(PhrasesActivity phrasesActivity, ArrayList<Word> words, int color) {
        super(phrasesActivity, 0, words);
        this.color = color;
    }

    public WordAdapter(ColorsActivity colorsActivity, ArrayList<Word> words, int color) {
        super(colorsActivity, 0, words);
        this.color = color;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        titleTextView.setText(currentWord.getMiwokTranslation());

        TextView subTitleTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        subTitleTextView.setText(currentWord.getDefaultTranslation());

        ImageView iconImageView = (ImageView) listItemView.findViewById(R.id.icon_image_view);

        if (currentWord.hasImage()) {
            iconImageView.setVisibility(View.VISIBLE);
            iconImageView.setImageResource(currentWord.getImageResourceID());
        }else{
            iconImageView.setVisibility(View.GONE);
        }

        LinearLayout content_layout = (LinearLayout) listItemView.findViewById(R.id.content_layout);
        int color = ContextCompat.getColor(getContext(), this.color);
        content_layout.setBackgroundColor(color);

        LinearLayout play_icon = (LinearLayout) listItemView.findViewById(R.id.image_layout);
        play_icon.setBackgroundColor(color);



        return listItemView;

    }
}

package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә", R.drawable.family_father, "family"));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, "family"));
        words.add(new Word("son", "angsi", R.drawable.family_son, "family"));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, "family"));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother, "family"));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, "family"));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, "family"));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, "family"));
        words.add(new Word("grandmother", "ama", R.drawable.family_grandmother, "family"));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, "family"));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_family);

        final ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word listItem = (Word) listView.getItemAtPosition(position);
                int res = getResources().getIdentifier(listItem.getAudioResourceID(), "raw", getPackageName());

                releaseMediaPlayer();

                mediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, res);
                //Log.v("Word", uri.toString());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(FamilyMembersActivity.this, "I'm Finished", Toast.LENGTH_SHORT).show();
                        releaseMediaPlayer();
                    }
                });
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }
}
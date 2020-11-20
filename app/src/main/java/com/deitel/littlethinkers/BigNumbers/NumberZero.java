package com.deitel.littlethinkers.BigNumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.deitel.littlethinkers.R;

public class NumberZero extends AppCompatActivity {

    private SoundPool soundPool;
    private int s0;
    private Button btnZero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_zero);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        s0 = soundPool.load(this, R.raw.zero,1);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.zero);

        mediaPlayer.start();

    }

    public void playSound(View v){
        switch (v.getId()) {
            case R.id.btnZero:
                soundPool.play(s0, 1, 1, 0, 0, 1);
                break;
        }
    }
}
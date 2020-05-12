package com.example.videodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    // creating a new media player object
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    // method called when play button is tapped
    public void play(View view)
    {
        mediaPlayer.start();
    }

    // method called when pause button is tapped
    public void pause(View view)
    {
        mediaPlayer.pause();
    }

    // method called when restart button is tapped
    public void restart(View view)
    {
        // media player is reinitialised, then restarted
        mediaPlayer.pause();
        mediaPlayer = MediaPlayer.create(this, R.raw.peaky);
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            VIDEO PLAYER

            // creating a new video player and assigning it a path
            VideoView videoPlayer = (VideoView) findViewById(R.id.videoView);
            videoPlayer.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));

            // creating a media controller (play, pause, forward)
            MediaController mediaController= new MediaController(this);
            mediaController.setAnchorView(videoPlayer);

            // assign video player to media controller and start playing video
            videoPlayer.setMediaController(mediaController);
            videoPlayer.start();
         */

        // media player & audio manager objects initialised on onCreate
        mediaPlayer = MediaPlayer.create(this,R.raw.peaky);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // variables that fetch the maximum volume of the device (differs) and the current volume
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumeControl = (SeekBar) findViewById(R.id.volumeSeekBar);
        final SeekBar scrubControl = (SeekBar) findViewById(R.id.scrubSeekBar);

        // the 100 of the seek bar will now correspond to maximum of device, and so will current
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currentVolume);

        // creating the listener for seekbar
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            // method when the progress of the seekbar is changed
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                Log.i("Info", Integer.toString(i));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            // method when the user taps the seekbar
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            // method when the user lets go of the seekbar
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });


        scrubControl.setMax(mediaPlayer.getDuration());

        scrubControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                Log.i("ScrubInfo", Integer.toString(i));
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        // creating a new timer object to update the scrub bar
        new Timer().scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                // sets scrub to current position of media player
                scrubControl.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,1500);

        // you can use a shorter time period if you have a better RAM but none of my devices are that cool sigh



    }
}

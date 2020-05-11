package com.example.videodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    // creating a new media player object
    MediaPlayer mediaPlayer;

    // method called when play button is tappedd
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
    protected void onCreate(Bundle savedInstanceState) {
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

        mediaPlayer = MediaPlayer.create(this,R.raw.peaky);

    }
}

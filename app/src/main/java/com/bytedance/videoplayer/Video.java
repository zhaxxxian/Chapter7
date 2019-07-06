package com.bytedance.videoplayer;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class Video<autoRotateOn> extends AppCompatActivity {
    private Button buttonPlay;
    private Button buttonPause;
    private VideoView videoView;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_video);//竖屏
        }else if(currentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_video_land);//横屏
        }

        setTitle("VideoView");


        buttonPause = findViewById(R.id.buttonPause);
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.pause();
            }
        });



        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.start();
                //handler.sendEmptyMessage(1);
            }
        });

        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(getVideoPath(R.raw.yuminhong));


        seekBar = findViewById(R.id.seekBar);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                videoView.seekTo(videoView.getDuration() * i / 100 );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                videoView.start();
            }
        });


    }
//    final Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg){
//            super.handleMessage(msg);
//            if(msg.what == 1){
//                seekBar.setMax(videoView.getDuration());
//                seekBar.setProgress(videoView.getCurrentPosition());
//                sendEmptyMessageDelayed(1,500);
//            }
//        }
//    };

    private String getVideoPath(int resID){
        return "android.resource://" + this.getPackageName() + "/" + resID;
    }

}

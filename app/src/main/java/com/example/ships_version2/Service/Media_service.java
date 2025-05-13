package com.example.ships_version2.Service;



import static com.example.ships_version2.AppCompactAtcivity.GameMatch.winn_ent;
import static com.example.ships_version2.AppCompactAtcivity.GameMatch.winn_plr;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.example.ships_version2.R;

public class Media_service extends Service {
    MediaPlayer ambientMediaPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate(){
        ambientMediaPlayer=MediaPlayer.create(this, R.raw.music);
        ambientMediaPlayer.setLooping(true);
        Log.d("ActivityPlayingField", "Start music");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        ambientMediaPlayer.start();
        while (winn_ent || winn_plr)
            ambientMediaPlayer.stop();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        ambientMediaPlayer.stop();
        Log.d("ActivityPlayingField", "Stop music");
    }
}
package com.example.eggertron.hw3_mysixlocations;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by eggertron on 10/11/16.
 */
public class MusicService extends Service {
    MusicPlayer musicPlayer;
    public static final String COMPLETE_INTENT = "complete intent";
    public static final String MUSICNAME = "music name";

    @Override
    public void onCreate() {
        super.onCreate();
        musicPlayer = new MusicPlayer();
        musicPlayer.setMusicService(this);
    }

    public void startMusic(String name) {
        musicPlayer.killSong();
        musicPlayer.playMusic(name);
    }

    public void pauseMusic() {
        musicPlayer.pauseMusic();
    }

    public void resumeMusic() {
        musicPlayer.resumeMusic();
    }

    public int getPlayingStatus() {
        return musicPlayer.getPlayingStatus();
    }

    public void stopMusic() {
        musicPlayer.stopMusic();
    }

    public void prevSong() {
        musicPlayer.prevSong();
    }

    public void nextSong() {
        musicPlayer.nextSong();
    }

    public void onUpdateMusicName(String musicname) {
        Intent intent = new Intent(COMPLETE_INTENT);
        intent.putExtra(MUSICNAME, musicname);
        sendBroadcast(intent);
    }

    private final IBinder iBinder=new MyBinder();

    public class MyBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
}
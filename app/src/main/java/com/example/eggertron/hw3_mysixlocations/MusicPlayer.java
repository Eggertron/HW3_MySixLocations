package com.example.eggertron.hw3_mysixlocations;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by eggertron on 10/11/16.
 */
public class MusicPlayer implements MediaPlayer.OnCompletionListener {
    static final String[] MUSICPATH = new String[]{
            "mario Here we go.mp3",
            "tetris.mp3"
    };
    static final String[] MUSICNAME = new String[]{
            "Super Mario",
            "Tetris"
    };
    MediaPlayer player;
    int currentPosition = 0;
    int musicIndex = 0;
    private int musicStatus = 0;//0: wait 1:playing 2:paused

    private MusicService musicService;

    public void setMusicService(MusicService service) {
        this.musicService = service;
    }

    public int getPlayingStatus() {
        return musicStatus;
    }

    public String getMusicName() {
        return MUSICNAME[musicIndex];
    }

    public void stopMusic() {
        if (player != null) {
            musicStatus = 0;
            player.stop();
            currentPosition = 0;
        }
    }

    public void playMusic(String name) {
        try {
            musicService.onUpdateMusicName(getMusicName());
            player = new MediaPlayer();
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            //File file = new File(path, MUSICPATH[musicIndex]);
            File file = new File(path, name);
            //File file = new File("/storage/emulated/0/Music/", MUSICPATH[musicIndex]);
            player.setDataSource(file.getAbsolutePath());
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.prepare();
            player.start();
            player.setOnCompletionListener(this);
            musicStatus = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {
        if (player != null && player.isPlaying()) {
            player.pause();
            currentPosition = player.getCurrentPosition();
            musicStatus = 2;
        }
    }

    public void resumeMusic() {
        if (player != null) {
            player.seekTo(currentPosition);
            player.start();
            musicStatus = 1;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        musicIndex = (musicIndex + 1) % MUSICNAME.length;
        player.release();
        player = null;
        musicStatus = 0;
        //playMusic();
    }

    public void nextSong() {
        musicIndex = (musicIndex + 1) % MUSICNAME.length;
        player.release();
        player = null;
        //playMusic();
    }

    public void killSong() {
        if (player != null)
            player.release();
        player = null;
        musicStatus = 0;
    }

    public void prevSong() {
        musicIndex--;
        if (musicIndex < 0) {
            musicIndex = MUSICNAME.length - 1;
        }
        player.release();
        player = null;
        //playMusic();
    }
}
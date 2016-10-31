package com.example.eggertron.hw3_mysixlocations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by eggertron on 10/11/16.
 */
public class MusicCompletionReceiver extends BroadcastReceiver {
    MainActivity mainActivity;
    //    public MusicCompletionReceiver(MainActivity mainActivity){
//        this.mainActivity=mainActivity;
//    }

    public MusicCompletionReceiver(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void SetMainActivity(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String musicName=intent.getStringExtra(MusicService.MUSICNAME);
        mainActivity.updateName(musicName);
    }
}
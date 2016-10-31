package com.example.eggertron.hw3_mysixlocations;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageHolder;
    TextView textHolder;
    GPSManager gpsManager;
    DirectionManager directionManager;
    MyCompass myCompass;
    boolean isLaying;
    int lastImage;
    boolean isBound = false;
    boolean isInitialized = false;
    MusicCompletionReceiver musicBroadcastReceiver;
    Intent startMusicServiceIntent;
    MusicService musicService;

    public static final String INITIALIZE_STATUS = "init";
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageHolder = (ImageView)findViewById(R.id.imageBlock);
        textHolder = (TextView)findViewById(R.id.textBlock);

        //music service stuff
        startMusicServiceIntent = new Intent(this, MusicService.class);
        //if(savedInstanceState!=null){
        //    isInitialized=savedInstanceState.getBoolean(INITIALIZE_STATUS);
            //music.setText(savedInstanceState.getString(MUSIC_PLAYING));
        //}
        if (!isInitialized) {
            verifyStoragePermissions(this);
            startService(startMusicServiceIntent);
            isInitialized = true;
        }
        musicBroadcastReceiver = new MusicCompletionReceiver(this);
        //musicBroadcastReceiver.SetMainActivity(this);
        //musicService = new MusicService();
        gpsManager = new GPSManager(this);
        directionManager = new DirectionManager(this);
        myCompass = new MyCompass();
        isLaying = true;
        lastImage = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Here, thisActivity is the current activity
        gpsManager.register();
        directionManager.register();
        if(isInitialized&&!isBound){
            bindService(startMusicServiceIntent,musicServiceConnection, Context.BIND_AUTO_CREATE);
        }
        registerReceiver(musicBroadcastReceiver,new IntentFilter(MusicService.COMPLETE_INTENT));
    }

    @Override
    protected void onPause() {
        super.onPause();
        gpsManager.unregister();
        directionManager.unregister();
        if(isBound){
            unbindService(musicServiceConnection);
            isBound=false;
        }
        unregisterReceiver(musicBroadcastReceiver);
    }

    public void setLaying(boolean l) {
        if (l != isLaying) {
            isLaying = l;
            updateUI();
        }
    }

    public void updateUI () {
        //arrow.setRotation(degree);
        //distance.setText("Distance to Drillfield: " + dist + " meters");
        if (isLaying) {
            // display satellite map and distance
            textHolder.setText(myCompass.getDistance());
            //((BitmapDrawable)imageHolder.getDrawable()).getBitmap().recycle();
            imageHolder.setImageResource(myCompass.getMap());
            lastImage = myCompass.getMap();
        }
        else {
            // display picture and description
            textHolder.setText(myCompass.getDescription());
            //((BitmapDrawable)imageHolder.getDrawable()).getBitmap().recycle();
            imageHolder.setImageResource(myCompass.getImg());
            lastImage = myCompass.getMap();
        }
        //play mp3
        String songName = myCompass.getMp3();
        if (songName != null && isBound) {
            musicService.startMusic(songName);
        }
    }

    public void updateSensor(double angle) {
        myCompass.setAngle(angle);
        if (lastImage != myCompass.getMap())
            updateUI();
    }

    public void updateGPSLocation(Location lastKnownLocation) {
        myCompass.setCurrentLocation(lastKnownLocation);
    }

    public void updateName(String musicName) {
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private ServiceConnection musicServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MyBinder binder = (MusicService.MyBinder) service;
            musicService = binder.getService();
            isBound = true;
            switch (musicService.getPlayingStatus()) {
                case 0:
                    //play.setText("Start");
                    break;
                case 1:
                    //play.setText("Pause");
                    break;
                case 2:
                    //play.setText("Resume");
                    break;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
            isBound = false;
        }
    };
}

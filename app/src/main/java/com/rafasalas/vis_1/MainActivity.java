package com.rafasalas.vis_1;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by salas on 26/04/2017.
 */

public class MainActivity extends Activity {

    private MediaPlayer mPlayer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){

    }

}

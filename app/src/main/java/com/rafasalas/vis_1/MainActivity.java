package com.rafasalas.vis_1;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by salas on 26/04/2017.
 */

public class MainActivity extends Activity {

    private MediaPlayer mPlayer;
    private byte[] mBytes;
    private byte[] mFFTBytes;

    private Visualizer mVisualizer;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        mBytes = null;
        mFFTBytes = null;





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        mPlayer = MediaPlayer.create(this, R.raw.dummy_01);
        mPlayer.setLooping(true);
        mPlayer.start();
        link(mPlayer);

    }
    public void link(MediaPlayer player)
    {
        if(player == null)
        {
            throw new NullPointerException("Cannot link to null MediaPlayer");
        }


        mVisualizer = new Visualizer(0);
       // mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        mVisualizer.setCaptureSize(128);

        Visualizer.OnDataCaptureListener captureListener = new Visualizer.OnDataCaptureListener()
        {
            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes,
                                              int samplingRate)
            { float sum=0;
                Log.v("wave_form", " " +bytes.length+" "+bytes[0]+" "+bytes[60]+" "+bytes[125]);
                for (int i = 0; i < bytes.length; i++) {
                    sum=sum+(float)bytes[i];
                }
                Log.v("sumatorio"," "+sum);
            }

            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] bytes,
                                         int samplingRate)
            {
                Log.v("fft", " " + bytes.length+bytes[0] + " "+bytes[60] + " " + bytes[125]);
            }
        };

        mVisualizer.setDataCaptureListener(captureListener,
                Visualizer.getMaxCaptureRate() / 2, true, true);

        // Enabled Visualizer and disable when we're done with the stream

        mVisualizer.setEnabled(true);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                mVisualizer.setEnabled(false);
                mVisualizer.release();
            }
        });
    }


    public void release()
    {
        mVisualizer.release();
    }


}

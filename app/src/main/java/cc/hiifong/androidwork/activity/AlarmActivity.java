package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;


import cc.hiifong.androidwork.R;

public class AlarmActivity extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Log.d("Alarm", "onCreate: Hello");
        try {
            mp = MediaPlayer.create(AlarmActivity.this, R.raw.getup);
            mp.setLooping(true);
            mp.start();
            new AlertDialog.Builder(AlarmActivity.this)
                    .setTitle("闹钟")
                    .setMessage("起床啦!")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mp.release();
                            AlarmActivity.this.finish();
                        }
                    }).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.ImageView;

import cc.hiifong.androidwork.R;

public class Images extends AppCompatActivity {
    Sensor s;
    SensorManager sm;
    ImageView iv;
    int rate;
    Vibrator vb;
    SensorEventListener listener;
    int[] imgId = { R.drawable.bg_img_1, R.drawable.bg_img_2, R.drawable.bg_img_3 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        iv = (ImageView) findViewById(R.id.iv);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        vb = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        rate = SensorManager.SENSOR_DELAY_NORMAL;
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];
                float f = 15;
                int i = (int) (Math.random() * 100) % imgId.length;
                if(Math.abs(x) > f || Math.abs(y) > f || Math.abs(z) > f) {
                    vb.vibrate(1);
                    iv.setImageResource(imgId[i]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sm.registerListener(listener, s, rate);
    }
    @Override
    protected void onDestroy() {
        vb.cancel();
        sm.unregisterListener(listener, s);
        super.onDestroy();
    }
}
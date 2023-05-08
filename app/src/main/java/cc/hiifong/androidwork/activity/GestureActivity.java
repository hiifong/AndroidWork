package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cc.hiifong.androidwork.R;

public class GestureActivity extends AppCompatActivity {
    private TextView textView;
    private GestureOverlayView gestureOverlayView;
    private GestureLibrary gestureLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gestureLibrary.load()) finish();
        textView = findViewById(R.id.g_tv);
        gestureOverlayView = findViewById(R.id.g_gov);
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
                ArrayList<Prediction> predictionArray = gestureLibrary.recognize(gesture);
                if (predictionArray.size() > 0 && predictionArray.get(0).score > 5.0){
                    String result = "手势名称:" + predictionArray.get(0).name + String.valueOf(predictionArray.get(0).score);
                    textView.setText(result);
                }
            }
        });
    }
}
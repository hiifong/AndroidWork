package cc.hiifong.androidwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.gesture.Gesture;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cc.hiifong.androidwork.R;

public class SimpleTouchActivity extends AppCompatActivity {
    private TextView tv;
    private GestureDetector gd;

    GestureDetector.OnGestureListener listener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(@NonNull MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(@NonNull MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
            Toast.makeText(SimpleTouchActivity.this, "单击抬起",Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(@NonNull MotionEvent motionEvent) {
            Toast.makeText(SimpleTouchActivity.this, "长按",Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onFling(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
            Toast.makeText(SimpleTouchActivity.this, "滑屏",Toast.LENGTH_SHORT).show();
            return false;
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_touch);
        tv = findViewById(R.id.st_tv);
        ImageView iv = findViewById(R.id.st_iv);
        iv.setFocusable(true);
        iv.setClickable(true);
        iv.setLongClickable(true);
        gd = new GestureDetector(this,listener);
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tv.setText("X=" + motionEvent.getX() + " Y=" + motionEvent.getY());
                return gd.onTouchEvent(motionEvent);
            }
        });
    }
}
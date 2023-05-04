package cc.hiifong.androidwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.FrameLayout;

import cc.hiifong.androidwork.R;

public class FrameLayoutActivity extends AppCompatActivity {
    private FrameLayout fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        fl = findViewById(R.id.bird);
        final BirdHandler birdHandler = new BirdHandler();
        birdHandler.sendMessageDelayed(birdHandler.obtainMessage(0), 200);
    }
    class BirdHandler extends Handler{
        int i = 0;

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (i < 100000) i++;
            else i = 0;
            Drawable a = getResources().getDrawable(R.drawable.bird1,getTheme());
            Drawable b = getResources().getDrawable(R.drawable.bird2,getTheme());
            Drawable c = getResources().getDrawable(R.drawable.bird3,getTheme());
            switch ( i % 3){
                case 0:
                    fl.setForeground(a);
                    break;
                case 1:
                    fl.setForeground(b);
                    break;
                case 2:
                    fl.setForeground(c);
                    break;
            }
            this.sendMessageDelayed(this.obtainMessage(0),200);
        }
    }
}
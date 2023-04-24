package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import cc.hiifong.androidwork.R;

public class Color extends AppCompatActivity {
    private ConstraintLayout cl;
    private SeekBar skb_r,skb_g,skb_b;
    private int r,g,b;
    private SeekBar.OnSeekBarChangeListener skb = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getTooltipText().toString()){
                case "r":
                    r = progress;
                    break;
                case "g":
                    g = progress;
                    break;
                case "b":
                    b = progress;
                    break;
            }
            cl.setBackgroundColor(android.graphics.Color.rgb(r,g,b));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Toast.makeText(Color.this,"r:" + r + ",g:" + g + ",b:" + b, Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        cl = (ConstraintLayout) findViewById(R.id.cl1);
        skb_r = (SeekBar) findViewById(R.id.skb_r);
        skb_g = (SeekBar) findViewById(R.id.skb_g);
        skb_b = (SeekBar) findViewById(R.id.skb_b);
        skb_r.setOnSeekBarChangeListener(skb);
        skb_g.setOnSeekBarChangeListener(skb);
        skb_b.setOnSeekBarChangeListener(skb);
    }
}
package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import cc.hiifong.androidwork.R;

public class LinearLayoutActivity extends AppCompatActivity {
    private LinearLayout ly;
    private Button btn;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
        ly = (LinearLayout) findViewById(R.id.ly_1);
        btn = (Button) findViewById(R.id.ly_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn.isClickable()){
                    flag = !flag;
                }
                if (flag){
                    ly.setOrientation(LinearLayout.HORIZONTAL);
                }else {
                    ly.setOrientation(LinearLayout.VERTICAL);
                }
            }
        });
    }
}
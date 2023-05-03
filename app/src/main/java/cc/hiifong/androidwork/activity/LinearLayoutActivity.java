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
    private TextView tv_1,tv_2,tv_3;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
        tv_1 = (TextView) findViewById(R.id.ly_tv_1);
        tv_2 = (TextView) findViewById(R.id.ly_tv_2);
        tv_3 = (TextView) findViewById(R.id.ly_tv_3);
        btn = (Button) findViewById(R.id.ly_btn);
        boolean flag = false;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn.isClickable()){
                    System.out.println("btn 被点击了");
                }
            }
        });
    }
}
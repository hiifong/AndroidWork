package cc.hiifong.androidwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import cc.hiifong.androidwork.activity.Images;
import cc.hiifong.androidwork.activity.Color;

public class MainActivity extends AppCompatActivity {
    private RadioButton b1,b2;
    private Button btn;
    private String checked;

    CompoundButton.OnCheckedChangeListener rb = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){
                checked = buttonView.getText().toString();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (RadioButton) findViewById(R.id.btn1);
        b2 = (RadioButton) findViewById(R.id.btn2);
        b1.setOnCheckedChangeListener(rb);
        b2.setOnCheckedChangeListener(rb);
        btn = (Button) findViewById(R.id.bnt_submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent();
                switch (checked){
                    case "调节背景色":
                        intent.setClass(MainActivity.this, Color.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    case "查看图集":
                        intent.setClass(MainActivity.this, Images.class);
                        MainActivity.this.startActivity(intent);
                        break;
                }
            }
        });
    }
}
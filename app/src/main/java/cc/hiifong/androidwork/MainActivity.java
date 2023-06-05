package cc.hiifong.androidwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import cc.hiifong.androidwork.activity.InsertActivity;
import cc.hiifong.androidwork.activity.ManagerActivity;
import cc.hiifong.androidwork.activity.QueryActivity;
import cc.hiifong.androidwork.dao.SiteDBHelp;

public class MainActivity extends AppCompatActivity {
    private SiteDBHelp dbHelp;
    private int checked;
    CompoundButton.OnCheckedChangeListener rb = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b){
                checked = compoundButton.getId();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (dbHelp == null){
            dbHelp = new SiteDBHelp(this);
        }
        dbHelp.fillDB();
        RadioButton btn_1 = findViewById(R.id.btn1);
        RadioButton btn_2 = findViewById(R.id.btn2);
        RadioButton btn_3 = findViewById(R.id.btn3);
        btn_1.setOnCheckedChangeListener(rb);
        btn_2.setOnCheckedChangeListener(rb);
        btn_3.setOnCheckedChangeListener(rb);
        Button btn = findViewById(R.id.bnt_submit);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent();
            switch (checked){
                case R.id.btn1:
                    intent.setClass(MainActivity.this, InsertActivity.class);
                    MainActivity.this.startActivity(intent);
                    break;
                case R.id.btn2:
                    intent.setClass(MainActivity.this, QueryActivity.class);
                    MainActivity.this.startActivity(intent);
                    break;
                case R.id.btn3:
                    intent.setClass(MainActivity.this, ManagerActivity.class);
                    MainActivity.this.startActivity(intent);
                    break;
            }
        });
    }
}
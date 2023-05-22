package cc.hiifong.androidwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import cc.hiifong.androidwork.activity.CustomSpinnerActivity;
import cc.hiifong.androidwork.activity.SpinnerActivity;

public class MainActivity extends AppCompatActivity {
    private Spinner spm;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spm = findViewById(R.id.spm);
        btn = findViewById(R.id.bnt_submit);

        btn.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent();
            @Override
            public void onClick(View view) {
                if (spm.getSelectedItemPosition() == 0){
                    intent.setClass(MainActivity.this, SpinnerActivity.class);
                    MainActivity.this.startActivity(intent);
                } else if (spm.getSelectedItemPosition() == 1) {
                    intent.setClass(MainActivity.this, CustomSpinnerActivity.class);
                    MainActivity.this.startActivity(intent);
                }
            }
        });
    }
}
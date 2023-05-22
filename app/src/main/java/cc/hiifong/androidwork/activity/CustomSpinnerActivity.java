package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cc.hiifong.androidwork.R;
import cc.hiifong.androidwork.adapter.MemberAdapter;
import cc.hiifong.androidwork.module.Member;

public class CustomSpinnerActivity extends AppCompatActivity {
    TextView textView;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_spinner);

        textView = findViewById(R.id.tvc);
        spinner = findViewById(R.id.spc);

        spinner.setAdapter(new MemberAdapter(this));
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Member member = (Member) adapterView.getItemAtPosition(i);
                textView.setText("你选择了" + member.getName());
                Toast.makeText(CustomSpinnerActivity.this, member.getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
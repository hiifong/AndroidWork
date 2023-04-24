package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import cc.hiifong.androidwork.R;

public class UserInfo extends AppCompatActivity {
    private RadioButton r1,r2;
    private CheckBox c1,c2,c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        TextView tv_username = (TextView) findViewById(R.id.userinfo);
        tv_username.setText(username);
        r1 = findViewById(R.id.male);
        r2 = findViewById(R.id.female);
        c1 = findViewById(R.id.h1);
        c2 = findViewById(R.id.h2);
        c3 = findViewById(R.id.h3);
        Button btn = (Button) findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sex;
                String hobby = "";
                String result;
                if (r1.isChecked()) sex = r1.getText().toString();
                else sex = r2.getText().toString();
                if (c1.isChecked()) hobby += c1.getText().toString();
                if (c2.isChecked()) hobby += c2.getText().toString();
                if (c3.isChecked()) hobby += c3.getText().toString();
                result = "username:" + username + ",sex:" + sex + ",hobby:" + hobby;
                Toast.makeText(UserInfo.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
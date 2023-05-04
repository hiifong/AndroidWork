package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cc.hiifong.androidwork.R;

public class TableLayoutActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button btn;

    private final String[][] users = {
            { "hiifong", "123456" },
            { "test", "654321" },
            { "teacher", "teacher" }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        username = (EditText) findViewById(R.id.tl_username);
        password = (EditText) findViewById(R.id.tl_password);
        Button login = (Button) findViewById(R.id.tl_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().length() > 0){
                    if (username.getText().toString().length() >= 6 && username.getText().toString().length() <= 12){
                        if (password.getText().toString().length() >= 6 && password.getText().toString().length() <= 12){
                            for (int i = 0; i < users.length - 1; i++){
                                if (users[i][0].equals(username.getText().toString())){
                                    if (users[i][1].equals(password.getText().toString())){
                                        String result = "username:" + username.getText().toString() + ",password:" + password.getText().toString();
                                        Toast.makeText(TableLayoutActivity.this, result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }else {
                            Toast.makeText(TableLayoutActivity.this, "密码不正确,密码长度为6-12位！", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(TableLayoutActivity.this, "用户名不正确,用户名长度为6-12位！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(TableLayoutActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
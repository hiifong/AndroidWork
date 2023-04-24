package cc.hiifong.androidwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import cc.hiifong.androidwork.activity.UserInfo;

public class MainActivity extends AppCompatActivity {
    private EditText username,password;
    private RadioButton isAgree;

    private final String[][] users = {
            { "hiifong", "123456" },
            { "test", "654321" },
            { "teacher", "teacher" }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        isAgree = (RadioButton) findViewById(R.id.isAgree);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().length() > 0){
                    if (username.getText().toString().length() >= 6 && username.getText().toString().length() <= 12){
                        if (password.getText().toString().length() >= 6 && password.getText().toString().length() <= 12){
                            if (isAgree.isChecked()){
                                for (int i = 0; i < users.length - 1; i++){
                                    if (users[i][0].equals(username.getText().toString())){
                                        if (users[i][1].equals(password.getText().toString())){
                                            Intent intent = new Intent();
                                            String result = "username:" + username.getText().toString() + ",password:" + password.getText().toString();
                                            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                                            intent.putExtra("username", username.getText().toString());
                                            intent.setClass(MainActivity.this, UserInfo.class);
                                            MainActivity.this.startActivity(intent);
                                        }
                                    }
                                }
                            }else {
                                Toast.makeText(MainActivity.this, "请同意协议！", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "密码不正确,密码长度为6-12位！", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "用户名不正确,用户名长度为6-12位！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
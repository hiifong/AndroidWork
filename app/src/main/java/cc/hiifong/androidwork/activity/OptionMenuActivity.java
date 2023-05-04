package cc.hiifong.androidwork.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import cc.hiifong.androidwork.R;

public class OptionMenuActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);
        tv = findViewById(R.id.tv_op_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        System.out.println("id:"+ id);
        switch (id){
            case R.id.om1:
                tv.setText("你选择了计算机系");
                return true;
            case R.id.om2:
                tv.setText("你选择了软件工程");
                return true;
            case R.id.om3:
                tv.setText("你选择了网络工程");
                return true;
            case R.id.om4:
                tv.setText("你选择了网络安全技术");
                return true;
            case R.id.g_om1:
                tv.setText("你选择了子菜单1");
                return true;
            case R.id.g_om2:
                tv.setText("你选择了子菜单2");
                return true;
            case R.id.g_om3:
                tv.setText("你选择了子菜单3");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
package cc.hiifong.androidwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cc.hiifong.androidwork.R;

public class ContextMenuActivity extends AppCompatActivity {
    private TextView tv;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);
        tv = findViewById(R.id.ct_tv);
        btn = findViewById(R.id.ct_btn);
        registerForContextMenu(btn);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.ctmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.m1:
                tv.setText("你选择了计算机系");
                return true;
            case R.id.m2:
                tv.setText("你选择了软件工程");
            case R.id.m3:
                tv.setText("你选择了软件工程");
            case R.id.m4:
                tv.setText("你选择了软件工程");
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
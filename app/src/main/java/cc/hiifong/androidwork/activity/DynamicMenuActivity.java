package cc.hiifong.androidwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import cc.hiifong.androidwork.R;

public class DynamicMenuActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_menu);
        tv = findViewById(R.id.dn_tv);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String ctx = tv.getText().toString();
        if ("软件技术".equals(ctx)){
            menu.clear();
            menu.add(0, 501, 1, "软件1班");
            menu.add(0, 502, 2, "软件2班");
            menu.add(0, 503, 3, "软件3班");
            menu.add(0, 504, 4, "转到计算机应用菜单");
        }
        if ("计算机应用".equals(ctx)){
            menu.clear();
            menu.add(0, 601, 1, "计应1班");
            menu.add(0, 602, 2, "计应2班");
            menu.add(0, 603, 3, "转到软件技术菜单");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dnmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case 504:
                tv.setText("计算机应用");
                return true;
            case 603:
                tv.setText("软件技术");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
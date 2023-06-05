package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import cc.hiifong.androidwork.R;
import cc.hiifong.androidwork.dao.SiteDBHelp;

public class QueryActivity extends AppCompatActivity {
    private SiteDBHelp dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        EditText name = findViewById(R.id.q_name);
        Button search = findViewById(R.id.q_query);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder address = new StringBuilder("");
                String n = name.getText().toString().trim();
                if (name.length() > 0){
                    ArrayList<String> addresses = dbHelp.getInfo(n);
                    if (addresses.size() > 0){
                        for (String addr:addresses){
                            address.append(addr + "\n");
                        }
                    }else {
                        address.append("找不到相关记录!");
                    }
                }else {
                    address.append("请输入关键字!");
                }
                Toast.makeText(QueryActivity.this, address, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHelp == null){
            dbHelp = new SiteDBHelp(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dbHelp != null){
            dbHelp.close();
            dbHelp = null;
        }
    }
}
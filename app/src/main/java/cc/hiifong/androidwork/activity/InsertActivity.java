package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cc.hiifong.androidwork.R;
import cc.hiifong.androidwork.dao.SiteDBHelp;
import cc.hiifong.androidwork.model.Site;

public class InsertActivity extends AppCompatActivity {

    private SiteDBHelp dbHelp;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        EditText add_id = findViewById(R.id.add_id);
        EditText add_name = findViewById(R.id.add_name);
        EditText add_phone = findViewById(R.id.add_phone);
        EditText add_address = findViewById(R.id.add_address);

        Button btn_add = findViewById(R.id.add_btn_insert);
        Button btn_clear = findViewById(R.id.add_btn_clear);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = add_id.getText().toString().trim();
                String name = add_name.getText().toString().trim();
                String phone = add_phone.getText().toString().trim();
                String address = add_address.getText().toString().trim();
                if (id.length() <= 0 || name.length() <= 0){
                    Toast.makeText(InsertActivity.this, "请输入ID:", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                Site site = new Site(id, name, phone, address);
                long rowId = dbHelp.insertBD(site);
                if (rowId != -1){
                    stringBuilder.append("插入成功!");
                }else {
                    stringBuilder.append("插入失败!");
                }
                Toast.makeText(InsertActivity.this, stringBuilder, Toast.LENGTH_SHORT).show();
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_id.setText("");
                add_name.setText("");
                add_phone.setText("");
                add_address.setText("");
            }
        });
    }
}
package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cc.hiifong.androidwork.R;
import cc.hiifong.androidwork.dao.SiteDBHelp;
import cc.hiifong.androidwork.model.Site;

public class ManagerActivity extends AppCompatActivity {
    private TextView row,id;

    private EditText name,phone,address;

    private Button next,back,update,delete;

    private SiteDBHelp dbHelp;

    private ArrayList<Site> sites;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        row = findViewById(R.id.m_row);
        id = findViewById(R.id.m_id);

        name = findViewById(R.id.m_name);
        phone = findViewById(R.id.m_phone);
        address = findViewById(R.id.m_address);

        next = findViewById(R.id.m_next);
        back = findViewById(R.id.m_back);
        update = findViewById(R.id.m_update);
        delete = findViewById(R.id.m_delete);

        if (dbHelp == null){
            dbHelp = new SiteDBHelp(this);
        }
        sites = dbHelp.getAllSites();

        showSites(index);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if (index >= sites.size())
                    index = 0;
                showSites(index);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                if (index < 0){
                    index = sites.size() - 1;
                }
                showSites(index);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b_id = id.getText().toString().trim();
                String b_name = name.getText().toString().trim();
                String b_phone = phone.getText().toString().trim();
                String b_address = address.getText().toString().trim();
                if (b_id.length() <= 0 || b_name.length() <= 0){
                    Toast.makeText(ManagerActivity.this, "信息不能为空!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Site site = new Site(b_id, b_name, b_phone, b_address);
                int count = dbHelp.updateDB(site);
                Toast.makeText(ManagerActivity.this, count + "条记录修改成功!", Toast.LENGTH_SHORT).show();
                sites = dbHelp.getAllSites();
                index = 0;
                showSites(index);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d_id = id.getText().toString().trim();
                int count = dbHelp.delete(d_id);
                Toast.makeText(ManagerActivity.this, count + "条记录删除成功!", Toast.LENGTH_SHORT).show();
                sites = dbHelp.getAllSites();
                index = 0;
                showSites(index);
            }
        });
    }
    private void showSites(int i){
        if (sites.size() > 0){
            row.setText((i + 1) + "/" + sites.size() + "(第几个/总个数)");
            id.setText(sites.get(i).getName());
            name.setText(sites.get(i).getName());
            phone.setText(sites.get(i).getPhoneNo());
            address.setText(sites.get(i).getAddress());
        }else {
            row.setText("0/0" + "记录为空!");
            id.setText("");
            name.setText("");
            phone.setText("");
            address.setText("");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHelp == null){
            dbHelp = new SiteDBHelp(this);
        }
        sites = dbHelp.getAllSites();
        showSites(index);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dbHelp != null){
            dbHelp.close();
            dbHelp = null;
        }
        sites.clear();
    }
}
package cc.hiifong.androidwork.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import cc.hiifong.androidwork.R;

public class CalendarActivity extends AppCompatActivity {
    private TextView c_tv;
    private CalendarView c_cv;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        c_tv = findViewById(R.id.c_tv);
        c_cv = findViewById(R.id.c_cv);
        c_cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                year = i;
                month = i1 + 1;
                day = i2;
                AlertDialog.Builder builder = new AlertDialog.Builder(CalendarActivity.this);
                builder.setTitle("确定选中?");
                builder.setMessage("您确定选中这一天去表白吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        c_tv.setText("你选了" + year + "-" + month + "-" + day + "这一天!祝你表白成功!");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        c_tv.setText("表白失败!");
                    }
                });
                builder.create().show();
            }
        });
    }
}
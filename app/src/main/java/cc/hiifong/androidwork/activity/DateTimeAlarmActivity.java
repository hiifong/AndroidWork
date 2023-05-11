package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import cc.hiifong.androidwork.R;

public class DateTimeAlarmActivity extends AppCompatActivity {
    private TextView ac_tv_1, ac_tv_2, ac_s;
    private SwitchCompat sw;
    private int year, monthOfYear, dayOfMonth, hour, minute;
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            DateTimeAlarmActivity.this.year = i;
            DateTimeAlarmActivity.this.monthOfYear = i1;
            DateTimeAlarmActivity.this.dayOfMonth = i2;
            String result = year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
            ac_tv_1.setText(result);
        }
    };
    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            DateTimeAlarmActivity.this.hour = i;
            DateTimeAlarmActivity.this.minute = i1;
            String result = hour + "时" + minute + "分";
            ac_tv_2.setText(result);
        }
    };

    protected void setAlarm() {
        calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth, hour, minute, 0);

        Intent intent = new Intent(DateTimeAlarmActivity.this, AlarmActivity.class);
        PendingIntent pi = PendingIntent.getActivity(DateTimeAlarmActivity.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pi);

        Log.d("Set", "setAlarm: Hello");

        Toast.makeText(DateTimeAlarmActivity.this, "闹钟已设置", Toast.LENGTH_SHORT).show();
        String result = "闹钟时间:" + year + "年" + (monthOfYear + 1 ) + "月" + dayOfMonth + "日" + hour + "时" + minute + "分";
        ac_s.setText(result);
    }

    protected void cancelAlarm(){
        Intent intent = new Intent(DateTimeAlarmActivity.this, AlarmActivity.class);
        PendingIntent pi = PendingIntent.getActivity(DateTimeAlarmActivity.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(pi);
        Toast.makeText(DateTimeAlarmActivity.this, "闹钟已取消!", Toast.LENGTH_SHORT).show();
        ac_s.setText(R.string.not_set);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_alarm);
        Button ac_btn_1 = findViewById(R.id.ac_btn_1);
        Button ac_btn_2 = findViewById(R.id.ac_btn_2);
        ac_tv_1 = findViewById(R.id.ac_tv_1);
        ac_tv_2 =findViewById(R.id.ac_tv_2);
        sw = findViewById(R.id.sw_set);
        ac_s = findViewById(R.id.ac_s);
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        hour =  calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        ac_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(DateTimeAlarmActivity.this, onDateSetListener, year, monthOfYear, dayOfMonth).show();
            }
        });
        ac_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(DateTimeAlarmActivity.this, onTimeSetListener, hour, minute, true).show();
            }
        });
        sw.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b){
                setAlarm();
            }else {
                cancelAlarm();
            }
        });
    }

    @Override
    protected void onRestart() {
        ac_tv_1.setText(R.string.not_set);
        ac_tv_2.setText(R.string.not_set);
        ac_s.setText(R.string.not_set);
        sw.setChecked(false);
        super.onRestart();
    }
}
package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cc.hiifong.androidwork.R;

public class BroadcastReceiverActivity extends AppCompatActivity {
    private String result;
    private TextView textView;
    private EditText et_num,et_msg;
    private Button bt_send;

    class SmsReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            SmsMessage msg = null;
            textView.setText("");
            result = "";
            if (null != bundle){
                Object[] smsObj = (Object[]) bundle.get("pdus");
                for (Object object : smsObj) {
                    msg =SmsMessage.createFromPdu((byte[]) object);
                    Date date = new Date(msg.getTimestampMillis()); // 时间
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String receiverTime = format.format(date);
                    result += "\n number:" + msg.getOriginatingAddress()
                            + " \n body:" + msg.getDisplayMessageBody()
                            + " \n time:" + receiverTime;
                }
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                textView.setText(result);
            }
        }
    }
    static final String BROADCAST_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    SmsReceiver smsReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        textView = findViewById(R.id.msg);
        et_num = findViewById(R.id.et_num);
        et_msg = findViewById(R.id.et_msg);
        bt_send = findViewById(R.id.btn_send);

        intentFilter = new IntentFilter(BROADCAST_ACTION);
        smsReceiver = new SmsReceiver();
        registerReceiver(smsReceiver, intentFilter);


        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone_num = et_num.getText().toString().trim();
                String sms_content = et_msg.getText().toString().trim();
                if (phone_num.equals("")){
                    Toast.makeText(BroadcastReceiverActivity.this, "请输入来信手机号!",Toast.LENGTH_SHORT).show();
                }else {
                    SmsManager smsManager = SmsManager.getDefault();
                    if (sms_content.length() > 70){
                        List<String> contents = smsManager.divideMessage(sms_content);
                        for (String sms: contents){
                            smsManager.sendTextMessage(phone_num, null,
                                    sms, null, null);
                        }
                    }else {
                        smsManager.sendTextMessage(phone_num, null,
                                sms_content,null,null);
                    }
                    Toast.makeText(BroadcastReceiverActivity.this, "短信已发送!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(smsReceiver);
        super.onDestroy();
    }
}
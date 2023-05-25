package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cc.hiifong.androidwork.R;

public class NotificationActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button btn;

    NotificationManager manager;
    NotificationChannelGroup channelGroup;
    NotificationChannel notificationChannel;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        textView = findViewById(R.id.please_input_ms);
        editText = findViewById(R.id.ms);
        btn = findViewById(R.id.send);

        //管理通知类
        manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        //通知渠道组用于管理通知渠道
        channelGroup = new NotificationChannelGroup("GroupId1", "GroupName1");
        //创建通知渠道组
        manager.createNotificationChannelGroup(channelGroup);
        //创建通知渠道
        notificationChannel = new NotificationChannel("ChannelId1", "ChannelName1", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);//允许使用LED消息提示灯
        notificationChannel.enableVibration(true);//允许振动
        notificationChannel.setLightColor(Color.YELLOW);//设置LED提示灯为黄色
        notificationChannel.setVibrationPattern(new long[]{ 0, 100, 100, 100 });//设置振动的频率
        notificationChannel.setGroup("GroupId1");//设置通知渠道组
        manager.createNotificationChannel(notificationChannel);

        // 通知跳转动作
        Intent intent = new Intent(this, BroadcastReceiverActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        //构造通知
        notification = new NotificationCompat.Builder(this, "ChannelId1")
                .setContentTitle("上课了上课了！")
                .setContentText("你老师喊你回直播间上网课啦！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        int second = Integer.parseInt(editText.getText().toString());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.notify(1, notification);//发通知
            }
        });

    }
}
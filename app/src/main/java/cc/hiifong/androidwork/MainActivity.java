package cc.hiifong.androidwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import cc.hiifong.androidwork.service.MusicService;

public class MainActivity extends AppCompatActivity {
    // MusicBox接收器所能响应的Action
    public static final String MUSICBOX_ACTION = "cc.hiifong.androidwork.MUSICBOX_ACTION";
    // MusicService接收器所能响应的Action
    public static final String MUSICSERVICE_ACTION = "cc.hiifong.androidwork.MUSICSERVICE_ACTION";
    // 初始化flag
    public static final int STATE_NON = 0x100;
    // 播放的flag
    public static final int STATE_PLAY = 0x101;
    // 暂停的flag
    public static final int STATE_PAUSE = 0x102;
    // 停止放的flag
    public static final int STATE_STOP = 0x103;
    // 播放上一首的flag
    public static final int STATE_PREVIOUS = 0x104;
    // 播放下一首的flag
    public static final int STATE_NEXT = 0x105;
    // 播放状态
    public int state;

    Button btn_pre,btn_next,btn_state;
    public static SeekBar skbMusic;
    // 获取界面中显示的歌曲标题,作者文本框
    TextView song_name, singer_name;
    String[] song_list = new String[] {"手心的蔷薇", "这,就是爱", "一路生花"};
    String[] singer_list = new String[] {"林俊杰&邓紫棋", "张杰", "周深"};
    // 是否正在播放
    boolean isPlaying = false;
    MusicBoxReceiver receiver;
    IntentFilter filter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_next: // 下一首
                        btn_state.setText("暂停");
                        state = STATE_NEXT;
                        sendBroadcastToService(state);
                        isPlaying = true;
                        break;
                    case R.id.btn: // 播放或暂停
                        if(!isPlaying){
                            btn_state.setText("暂停");
                            state = STATE_PLAY;
                            sendBroadcastToService(state);
                            isPlaying = true;
                        }else {
                            btn_state.setText("播放");
                            state = STATE_PAUSE;
                            sendBroadcastToService(state);
                            isPlaying = false;
                        }
                        break;
                    case R.id.btn_pre: // 上一首
                        btn_state.setText("暂停");
                        state = STATE_PREVIOUS;
                        sendBroadcastToService(state);
                        isPlaying = true;
                        break;
                    default:
                        break;
                }
            }
        };

        SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                MusicService.isChanging = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 当拖动停止后.控制mediaPlayer播放指定位置的音乐
                if (state != STATE_NON){
                    MusicService.mediaPlayer.seekTo(seekBar.getProgress());
                }else {
                    seekBar.setProgress(0);
                }
                MusicService.isChanging = false;
            }
        };


        skbMusic  = findViewById(R.id.sk);
        skbMusic.setOnSeekBarChangeListener(changeListener);

        btn_next = findViewById(R.id.btn_next);
        btn_state = findViewById(R.id.btn);
        btn_pre = findViewById(R.id.btn_pre);

        btn_next.setOnClickListener(listener);
        btn_state.setOnClickListener(listener);
        btn_pre.setOnClickListener(listener);

        song_name = findViewById(R.id.song_name);
        singer_name = findViewById(R.id.singer_name);

        // 注册接收器
        receiver = new MusicBoxReceiver();
        filter = new IntentFilter();
        filter.addAction(MUSICBOX_ACTION);
        registerReceiver(receiver, filter);
        intent = new Intent(this, MusicService.class); // 指定要开启的服务

        // 情况一: 判断后台服务是否已开启,如开启则恢复当前播放进度和状态
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo: manager.getRunningServices(Integer.MAX_VALUE)){
            if ("cc.hiifong.administrator.androidwork.MusicService"
                    .equals(serviceInfo.service.getClassName()) && serviceInfo.started
            ){
                skbMusic.setMax(MusicService.mediaPlayer.getDuration());
                skbMusic.setProgress(MusicService.mediaPlayer.getCurrentPosition());
                song_name.setText(song_list[MusicService.current]); // 更新音乐标题
                singer_name.setText(singer_list[MusicService.current]); // 更新音乐作者
                state = STATE_PLAY;
            }
            if (MusicService.mediaPlayer.isPlaying()){
                btn_state.setText("暂停");
                isPlaying = true;
            }
            return;
        }
        // 情况二: 未开启过服务则开启服务
        song_name.setText(song_list[0]);
        singer_name.setText(singer_list[0]);
        state = STATE_NON;
        startService(intent);
    }

    class MusicBoxReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 获取Intent中current消息,current代表当前正在播放的歌曲
            int current = intent.getIntExtra("current", -1);
            song_name.setText(song_list[current]);
            singer_name.setText(singer_list[current]);
        }
    }

    protected void sendBroadcastToService(int state){
        Intent intent1 = new Intent();
        intent1.setAction(MUSICSERVICE_ACTION);
        intent1.putExtra("control", state);
        // 向后台Service发送播放控制的广播
        sendBroadcast(intent1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 关联和添加菜单项
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0, 1, 1, "停止");
        menu.add(0, 2, 2, "退出");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == 1) {
            btn_state.setText("播放");
            sendBroadcastToService(STATE_STOP);
            isPlaying = false;
        } else if (id == 2) {
            stopService(intent);
            this.finish();;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
package cc.hiifong.androidwork.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import cc.hiifong.androidwork.MainActivity;

public class MusicService extends Service {
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

    private TimerTask timerTask;
    private Timer mTimer;

    public static boolean isChanging = false; // 互斥变量,防止定时器与SeekBar拖动时进度冲突
    // 创建一个媒体播放器的对象
    public static MediaPlayer mediaPlayer;
    // 创建一个Asset管理器的对象
    AssetManager assetManager;
    // 存放音乐名的数组
    String[] musics = new String[] {"shouxindeqiangwei.mp3", "thisislove.mp3", "yilushenghua.mp3"};
    // 当前播放的音乐
    public static int current;
    // 当前播放状态
    int state = STATE_NON;
    // 记录Timer运行状态
    boolean isTimerRunning = false;
    MusicServiceReceiver receiver;
    IntentFilter filter;

    public void onCreate() {
        super.onCreate();
        // 注册接收器
        receiver = new MusicServiceReceiver();
        filter = new IntentFilter();
        filter.addAction(MUSICSERVICE_ACTION);
        registerReceiver(receiver, filter);
        mediaPlayer = new MediaPlayer();
        assetManager = getAssets();
        current = 0;
        // 为mediaPlayer的完成事件创建监听器
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                current++;
                prepareAndPlay(current);
            }
        });
    }

    /**
     * 装载和播放音乐
     * @param index int index 播放第几音乐的索引
     * @return
     */
    protected void prepareAndPlay(int index) {
        if (isTimerRunning) { // 如果Timer正在运行
            timerTask.cancel(); // 取消定时器
            isTimerRunning = false;
        }
        if (index > musics.length - 1) {
            current = index = 0;
        }
        if (index < 0) {
            current = index = musics.length -1;
        }
        // 发送广播停止前台Activity更新界面
        Intent intent = new Intent();
        intent.putExtra("current", index);
        intent.setAction(MUSICBOX_ACTION);
        sendBroadcast(intent);
        try {
            // 获取assets目录下指定文件的AssetFileDescriptor对象
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(musics[current]);
            mediaPlayer.reset(); // 初始化mediaPlayer对象
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());
            // 准备播放音乐
            mediaPlayer.prepare();
            // 播放音乐
            mediaPlayer.start();
            // getDuration() 方法要在prepare() 方法之后, 否则会出现异常
            MainActivity.skbMusic.setMax(mediaPlayer.getDuration()); // 设置SeekBar的长度
        }catch (IOException e){
            e.printStackTrace();
        }
        // -------- 定时器记录播放进度------------
        mTimer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                isTimerRunning = true;
                if (isChanging == true) // 当用户正在拖动进度进条时不处理进度条的进度
                    return;
                MainActivity.skbMusic.setProgress(mediaPlayer.getCurrentPosition());
            }
        };
        // 每隔10毫秒检测一下播放进度
        mTimer.schedule(timerTask, 0, 10);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MusicServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int control = intent.getIntExtra("control", -1);
            switch (control) {
                case STATE_PLAY: // 播放音乐
                    if (state == STATE_PAUSE) { // 如果原来状态是暂停
                        mediaPlayer.start();
                    } else if (state != STATE_PLAY) {
                        prepareAndPlay(current);
                    }
                    state = STATE_PLAY;
                    break;
                case STATE_PAUSE: // 暂停播放
                    if (state == STATE_PLAY) {
                        mediaPlayer.pause();
                        state = STATE_PAUSE;
                    }
                    break;
                case STATE_STOP: // 停止播放
                    if (state == STATE_PLAY || state == STATE_PAUSE) {
                        state = STATE_STOP;
                        mediaPlayer.seekTo(0);
                        mediaPlayer.stop();
                    }
                    break;
                case STATE_PREVIOUS: // 上一首
                    prepareAndPlay(--current);
                    state = STATE_PLAY;
                    break;
                case STATE_NEXT: // 下一首
                    prepareAndPlay(++current);
                    state = STATE_PLAY;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        timerTask.cancel();
        mediaPlayer.release();
        super.onDestroy();
    }
}

package cn.u313.plugin.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;

import java.io.File;


import androidx.annotation.Nullable;
import cn.u313.plugin.activity.MainActivity;
import cn.u313.plugin.comm.Setting.Setting;
import cn.u313.plugin.receiver.SingleMediaScanner;

import static android.content.ContentValues.TAG;
import static cn.u313.plugin.activity.DownloadActivity.is;
import static cn.u313.plugin.activity.DownloadActivity.progressBar;
import static cn.u313.plugin.activity.DownloadActivity.t1;
import static cn.u313.plugin.activity.DownloadActivity.t2;

public class DownloadService extends Service {
    private static final String DOWNLOAD_URL =
            "http://qzapp.qlogo.cn/qzapp/101858587/A1CB17BE3A6B025F44A6598C4DBFEE77/100";

    //    private DownloadNotification mNotify;
//client 可以通过Binder获取Service实例
    public class MyBinder extends Binder {
        public DownloadService getService() {
            return DownloadService.this;
        }
    }

    //通过binder实现调用者client与Service之间的通信
    private MyBinder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        mNotify = new DownloadNotification(getApplicationContext());
        Aria.download(this).register();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Aria.download(this).unRegister();
    }

    @Download.onNoSupportBreakPoint
    public void onNoSupportBreakPoint(DownloadTask task) {
        showShort(getApplicationContext(), "该下载链接不支持断点");
    }

    void showShort(Context context, String a) {
        Log.e("TEST", "showShort: " + a);
//        Toast.makeText(context, a, Toast.LENGTH_SHORT).show();
    }

    @Download.onTaskStart
    public void onTaskStart(DownloadTask task) {
        showShort(getApplicationContext(), task.getDownloadEntity().getFileName() + "，开始下载");
        if (is && Setting.downloadAc != null) {
            Setting.downloadAc.lista(false);
        }
    }

    @Download.onTaskStop
    public void onTaskStop(DownloadTask task) {
        showShort(getApplicationContext(), task.getDownloadEntity().getFileName() + "，停止下载");
        if (is && Setting.downloadAc != null) {
            Setting.downloadAc.lista(false);
        }
    }

    @Download.onTaskCancel
    public void onTaskCancel(DownloadTask task) {
        showShort(getApplicationContext(), task.getDownloadEntity().getFileName() + "，取消下载");
        if (is && Setting.downloadAc != null) {
            Setting.downloadAc.lista(false);
        }
    }

    @Download.onTaskFail
    public void onTaskFail(DownloadTask task) {
        showShort(getApplicationContext(), task.getDownloadEntity().getFileName() + "，下载失败");
        if (is && Setting.downloadAc != null) {
            Setting.downloadAc.lista(false);
        }
    }

    /**
     * 下载完成后设置歌曲属性
     *
     * @param task
     */
    @Download.onTaskComplete
    public void onTaskComplete(DownloadTask task) {
        showShort(getApplicationContext(), task.getDownloadEntity().getFileName() + "，下载完成");
        //下载器是否err
        if (is && Setting.downloadAc != null) {
            Setting.downloadAc.lista(false);
        }
        String j = task.getExtendField();
        //注册媒体库
        String musicPath = task.getFilePath();
        if (!TextUtils.isEmpty(musicPath)) {
            // 设置专辑封面
            File mMusicFile = new File(task.getFilePath());
            Log.e(TAG, "onTaskComplete: " + mMusicFile.length());

            new SingleMediaScanner(this, mMusicFile);
            String path = "file://".concat(task.getFilePath());
            Log.e(TAG, "onTaskComplete: " + path);
            Intent intent =
                    new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(path));
            MainActivity.mainActivity.sendBroadcast(intent);
            //  ToastUtils.show("保存成功");
        }
    }
//        mNotify.upload(100);


    @Download.onTaskRunning public void onTaskRunning(DownloadTask task) {
        int p = task.getPercent();  //任务进度百分比
        Log.e(TAG, task.getTaskName()+"进度: "+p );
        if(is&& Setting.downloadAc!=null&&progressBar!=null){
            progressBar.setProgress(p);
            t1.setText(task.getSpeed()/1000+" kb/s");
            //获取单位转换后的文件大小
            String fileSize1 = task.getConvertFileSize();
            fileSize1= fileSize1.toUpperCase();
            //当前进度百分比
            int percent = task.getPercent();
            t2.setText("已下载 "+percent+"%\t 总："+fileSize1);
        }
    }

}
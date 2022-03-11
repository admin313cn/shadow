package cn.u313.plugin.application;//package cn.u313.music_test.application;
//
//
//import android.annotation.TargetApi;
//import android.app.Application;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.support.multidex.MultiDex;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.tencent.bugly.Bugly;
//import com.tencent.bugly.beta.Beta;
//import com.tencent.bugly.beta.interfaces.BetaPatchListener;
////import com.tencent.tinker.entry.DefaultApplicationLike;
//
//import java.util.Locale;
//
///**
// * Created by Haegyeong on 2016/12/22.
// * email：1328750511@qq.com
// * <p>
// * 描述：以前的Applicaton配置为继承TinkerApplication的类：
// */
//
//public class SampleApplicationLike extends DefaultApplicationLike {
//
//    public static final String TAG = "Tinker.SampleApplicationLike";
//
//    public SampleApplicationLike(Application application, int tinkerFlags,
//                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
//                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
//        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
//    }
//
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
//        // 调试时，将第三个参数改为true
//        Bugly.init(getApplication(), "eed0e0c443", true);
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    @Override
//    public void onBaseContextAttached(Context base) {
//        super.onBaseContextAttached(base);
//        // you must install multiDex whatever tinker is installed!
//        MultiDex.install(base);
//
//        // 安装tinker
//        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
//        Beta.installTinker(this);
//        Beta.canNotifyUserRestart =true;
//        Beta.betaPatchListener = new BetaPatchListener() {
//            @Override
//            public void onPatchReceived(String patchFile) {
//                //Toast.makeText(getApplication(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDownloadReceived(long savedLength, long totalLength) {
//                Log.e("URL",
//                        String.format(Locale.getDefault(), "%s %d%%",
//                                Beta.strNotificationDownloading,
//                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)));
//            }
//
//            @Override
//            public void onDownloadSuccess(String msg) {
//                //Toast.makeText(getApplication(), "补丁下载成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onDownloadFailure(String msg) {
//                //Toast.makeText(getApplication(), "补丁下载失败", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onApplySuccess(String msg) {
//                Toast.makeText(getApplication(), "补丁应用成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onApplyFailure(String msg) {
//                //Toast.makeText(getApplication(), "补丁应用失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPatchRollback() {
//                //Toast.makeText(getApplication(), "补丁回滚", Toast.LENGTH_SHORT).show();
//            }
//        };
////        Beta.canAutoDownloadPatch = false;
//    }
//
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
//        getApplication().registerActivityLifecycleCallbacks(callbacks);
//    }
//
//}

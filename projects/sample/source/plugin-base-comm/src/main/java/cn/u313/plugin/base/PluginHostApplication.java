package cn.u313.plugin.base;


import static android.os.Process.myPid;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

import com.tencent.shadow.core.common.LoggerFactory;
import com.tencent.shadow.dynamic.host.DynamicRuntime;
import com.tencent.shadow.dynamic.host.PluginManager;

import java.io.File;

import cn.u313.plugin.base.manager.Shadow;
import cn.u313.plugin.base.utils.DBKeyValue;
import com.tencent.shadow.sample.host.lib.HostUiLayerProvider;

public class PluginHostApplication extends Application {
    private static PluginHostApplication sApp;
    private static PluginContext pluginContext;
    public  static PluginHostApplication getsApp(){
        return sApp;
    }
    private PluginManager mPluginManager;
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
//
//        CrashHandler.getInstance().init();
        detectNonSdkApiUsageOnAndroidP();
        DBKeyValue.init(getApplicationContext());
        setWebViewDataDirectorySuffix();
        pluginContext = PluginContext.init(getApplicationContext());
//        LoggerFactory.setILoggerFactory(new AndroidLogLoggerFactory());

        LoggerFactory.setILoggerFactory(new AndroidLogLoggerFactory());
        if (isProcess(this, ":plugin")) {
            //在全动态架构中，Activity组件没有打包在宿主而是位于被动态加载的runtime，
            //为了防止插件crash后，系统自动恢复crash前的Activity组件，此时由于没有加载runtime而发生classNotFound异常，导致二次crash
            //因此这里恢复加载上一次的runtime
            DynamicRuntime.recoveryRuntime(this);
        }
//        initPlugin();

        HostUiLayerProvider.init(this);
    }

    private static void setWebViewDataDirectorySuffix() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            return;
        }
        WebView.setDataDirectorySuffix(Application.getProcessName());
    }




    private static void detectNonSdkApiUsageOnAndroidP() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            return;
        }
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        builder.detectNonSdkApiUsage();
        StrictMode.setVmPolicy(builder.build());
    }

    public static PluginHostApplication getApp() {
        return sApp;
    }

    public void loadPluginManager(File apk) {
        if (mPluginManager == null) {
            mPluginManager = Shadow.getPluginManager(apk);
        }
    }

    public PluginManager getPluginManager() {
        return mPluginManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private static boolean isProcess(Context context, String processName) {
        String currentProcName = "";
        ActivityManager manager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == myPid()) {
                currentProcName = processInfo.processName;
                break;
            }
        }

        return currentProcName.endsWith(processName);
    }

    public static PluginContext getPluginContext() {
        return pluginContext;
    }
}

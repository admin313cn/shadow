/*
 * Tencent is pleased to support the open source community by making Tencent Shadow available.
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package cn.u313.plugin;

import android.Manifest;
import android.app.ActivityManager;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.WebView;

import cn.u313.plugin.base.PluginHostApplication;
import cn.u313.plugin.comm.CrashHandler;

import java.io.File;

import static android.os.Process.myPid;

public class HostApplication extends PluginHostApplication {
    private static HostApplication application;
    public  static HostApplication getsApp(){
        return application;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        CrashHandler.getInstance().init();
        detectNonSdkApiUsageOnAndroidP();
//        DBKeyValue.init(getApplicationContext());
        setWebViewDataDirectorySuffix();
//        LoggerFactory.setILoggerFactory(new AndroidLogLoggerFactory());


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

    public static HostApplication getApp() {
        return application;
    }



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
}

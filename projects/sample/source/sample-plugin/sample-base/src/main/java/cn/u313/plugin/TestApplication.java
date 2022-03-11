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

import android.app.Application;
import android.widget.Toast;

import com.tencent.bugly.Bugly;

import cn.u313.plugin.application.CrashHandler;
import cn.u313.plugin.application.ForegroundObserver;
import cn.u313.plugin.comm.utils.YLog;
import cn.u313.plugin.dbmanager.DatabaseUtils;
import cn.u313.plugin.storage.preference.Preferences;

public class TestApplication extends Application {

    private static TestApplication sInstence;

    public boolean isOnCreate;

    @Override
    public void onCreate() {
        sInstence = this;
        isOnCreate = true;
        super.onCreate();
        CrashHandler.getInstance().init();
        Preferences.init(getApplicationContext());
        YLog.e("SampleApplication");
        Bugly.init(getApplicationContext(), "eb8e5128e8", false);
//        DexFile[] as = (DexFile[])a.toArray();
//        List<Class<?>> list = ClassesReader.reader(getApplicationContext().getPackageName(),getApplicationContext().getPackageCodePath());
//        for (Class<?> aClass : list) {
//
//            YLog.e("SampleApplication",aClass.getName());
//        }
     
//        Bugly.init(getApplicationContext(), "eed0e0c443", false);
//        AppCache.get().init(this);
        ForegroundObserver.init(this);
//        DBManager.get().init(this);
        DatabaseUtils.initHelper(this,"videodbmode");
    }

    public static TestApplication getInstance() {
        return sInstence;
    }
}

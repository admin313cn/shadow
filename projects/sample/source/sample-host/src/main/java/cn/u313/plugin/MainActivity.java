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
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.tencent.shadow.sample.host.R;

import java.io.File;
import java.util.Arrays;

import cn.u313.plugin.base.HostEnterCallback;
import cn.u313.plugin.base.PluginHostApplication;
import cn.u313.plugin.base.UpdateCallback;
import cn.u313.plugin.comm.nav.StatusBarUtil;

public class MainActivity extends BaseActivity {
    ViewGroup mViewGroup ;

    private Handler mHandler = new Handler();
    private static final int PERMISSON_REQUESTCODE = 1;
    TextView loadTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        loadTextView = findViewById(R.id.loadText);
        mViewGroup = findViewById(R.id.mainView);
        loadTextView.setText("正在检查更新....");
//        if (true) {
//            return;
//        }

        initPermission();



    }

    /**
     * 初始化权限（申请权限）
     */
    private void initPermission() {
        //权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            checkSkip();
            return;
        }
        //权限申请
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // 申请 相机 麦克风权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,

                    Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSON_REQUESTCODE);
        }else {
            checkSkip();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        log("onRequestPermissionsResult:\n requestCode: "+requestCode+"\npermissions:"+ Arrays.toString(permissions)+"\ngrantResults:"+Arrays.toString(grantResults) );
        if(permissions.length==0){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            return;
        }
        if(grantResults.length==0||grantResults[0]==-1){
            Toast.makeText(getApplicationContext(), "无权限！请先获取文件读写权限！", Toast.LENGTH_SHORT).show();
            //finish();
            return;
        }
        checkSkip();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void checkSkip() {
        //
        PluginHostApplication.getPluginContext().loadA(PluginLoadActivity.class, new UpdateCallback() {
            @Override
            public void loadSessce() {
                /**
                 * 插件更新成功
                 */
                PluginHostApplication
                        .getPluginContext()
                        .startActivity("cn.u313.plugin.activity.MainActivity", new HostEnterCallback() {
                            /**
                             * 启动后关闭当前页面
                             */
                            @Override
                            public void onCloseLoadingView() {
                                finish();
                            }
                        });
            }

            @Override
            public void ukwn() {
                /**
                 * 插件 无更新
                 */
                log("ukwn");
                PluginHostApplication
                        .getPluginContext()
                        .startActivity("cn.u313.plugin.activity.MainActivity",new HostEnterCallback() {
                            @Override
                            public void onCloseLoadingView() {
                                finish();
                            }
                        });
            }

            @Override
            public void loadError() {

                /**
                 * 插件 更新异常
                 */
                log("loadError");

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PluginHostApplication.getPluginContext().onDestroy();
    }

}

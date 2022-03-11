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

package cn.u313.plugin.base;

import android.content.Context;
import android.os.Environment;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.u313.plugin.base.utils.YLog;


public class PluginHelper {
    public static boolean isTest = false;

    /**
     * 动态加载的插件管理apk
     */
    public final static String sPluginManagerName = "pluginmanager.apk";

    /**
     * 动态加载的插件包，里面包含以下几个部分，插件apk，插件框架apk（loader apk和runtime apk）, apk信息配置关系json文件
     */
    public final static String sPluginZip = BuildConfig.DEBUG ? "plugin-debug.zip" : "plugin-release.zip";

    public File pluginManagerFile;

    public File pluginZipFile;

    public ExecutorService singlePool = Executors.newSingleThreadExecutor();

    private Context mContext;

    private static PluginHelper sInstance = new PluginHelper();

    public static PluginHelper getInstance() {
        return sInstance;
    }

    private PluginHelper() {
    }

    public void init(Context context) {

        mContext = context.getApplicationContext();



//        File root = new File(mContext.getFilesDir()+ "/ManagerImplLoader");
        //当前已安装的插件名称（文件夹名）
        pluginManagerFile = new File(context.getFilesDir(), sPluginManagerName);//
        pluginZipFile = new File(context.getFilesDir(), sPluginZip);//
//        String name = null;
//        if (name == null) {
//            //未安装
//
//            if (pluginManagerFile == null) {
//                YLog.e("pluginManagerFile=null");
//                return;
//            }
//            //解压后插件的文件夹
//            String n="JJSSHFS";
//            DBKeyValue.saveData(Consts.plugin_mian,n);
//            YLog.e("解压完成"+n);
//        }else {
////            File odexDir = new File(root, name);
//            YLog.e("已经解压过了"+name);
//        }


//        if (odexDir!=null&&odexDir.list()!=null&&odexDir.list().length>0){
//
//        }else





        YLog.e("线程池门口");
        singlePool.execute(new Runnable() {
            @Override
            public void run() {

                YLog.e("已进线程池");
                preparePlugin();
            }
        });

    }

    private void preparePlugin() {
        try {
            YLog.e("开始复制");
            if(isTest){

                InputStream zip =mContext.getAssets().open(sPluginZip);
                FileUtils.copyInputStreamToFile(zip, pluginZipFile);


//                pluginManagerFile = new File(mContext.getFilesDir(), sPluginManagerName);//
                InputStream is = mContext.getAssets().open(sPluginManagerName);
                FileUtils.copyInputStreamToFile(is, pluginManagerFile);
            }else{
                InputStream zip = new FileInputStream(
                        Environment.getExternalStorageDirectory()+"/plugin.zip"
                );
                FileUtils.copyInputStreamToFile(zip, pluginZipFile);


//                pluginManagerFile = new File(mContext.getFilesDir(), sPluginManagerName);//
                InputStream is = new FileInputStream(
                        Environment.getExternalStorageDirectory()+"/"+sPluginManagerName
                );// mContext.getAssets().open(sPluginManagerName);
                FileUtils.copyInputStreamToFile(is, pluginManagerFile);


            }

            YLog.e("复制完成");
//            String name = (String) DBKeyValue.getData(Consts.plugin_mian,null);
//
//            if (name == null) {
                //未安装

//            }else {
////            File odexDir = new File(root, name);
//                YLog.e("已经解压过了");
//            }


//            InputStream zip = new BufferedInputStream(
////                    mContext.openFileInput("plugin.zip")
//                    new FileInputStream(Environment.getExternalStorageDirectory()+"/plugin.zip")
//            );
//            FileUtils.copyInputStreamToFile(zip, pluginZipFile);
//            InputStream is = mContext.getAssets().open(sPluginManagerName);
//            FileUtils.copyInputStreamToFile(is, pluginManagerFile);
//
//            InputStream zip = mContext.getAssets().open(sPluginZip);
//            FileUtils.copyInputStreamToFile(zip, pluginZipFile);


        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(mContext, "未找到可用插件！", Toast.LENGTH_SHORT).show();
           // throw new RuntimeException("从assets中复制apk出错", e);
        }
    }


}

package cn.u313.plugin.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.tencent.shadow.dynamic.host.EnterCallback;

import cn.u313.plugin.base.utils.Constant;
import cn.u313.plugin.base.utils.DBKeyValue;
import cn.u313.plugin.base.model.PluginVersionModel;
import cn.u313.plugin.base.utils.DownloadUtil;
import cn.u313.plugin.base.utils.FilesUtils;
import cn.u313.plugin.base.utils.YLog;
import cn.u313.plugin.base.utils.http.HttpCallback;
import cn.u313.plugin.base.utils.http.HttpClient;

public class PluginContext {
    /**
     *
     */
    PluginVersionModel pluginVersionModel;
    UpdateCallback updateCallback;//下载回调
    public  void log(Object ...arg){

        YLog.e(arg);
    }
    private static PluginContext pluginContext ;
    ApkLoadingImp apkLoadingImp;
    Context context;

    public PluginContext(Context context) {
        DBKeyValue.init(context);
        this.context = context;
    }
    public void loadA(Class<?> pluginDef,UpdateCallback updateCallback){
        aClass = pluginDef;
        this.updateCallback = updateCallback;
        checkSkip();
    }
    public PluginVersionModel initVersion(){
        return null;
    }
    //入口
    private void checkSkip() {
        log("插件更新入口");
        HttpClient.loadPluginGx(new HttpCallback<PluginVersionModel>() {
            @Override
            public void onSuccess(PluginVersionModel pluginModel) {
                if (pluginModel == null) {
                    return;
                }
                pluginVersionModel = pluginModel;
                String json = DBKeyValue.getData("pluginModel",null);
                //第一次
                if (json == null) {
                    log("json = null");
                    downloadPlugin(pluginModel.getManagerurl(), pluginModel.getPluginurl());
                    return;
                }

                Gson gson = new Gson();
                PluginVersionModel localhostPluginModel = gson.fromJson(json,PluginVersionModel.class);
                if (localhostPluginModel == null) {
                    log("localhostPluginModel = null","解析异常！");
//                    downloadPlugin(pluginModel,true);
                    return;
                }
                String managerUrl=null, pluginUrl = null;

                //判断版本
                if(pluginModel.getManagerid() !=localhostPluginModel.getManagerid()){
                    //插件管理需要更新
                    managerUrl = pluginModel.getManagerurl();

                }
                if(pluginModel.getPluginid() !=localhostPluginModel.getPluginid()){
                    //插件需要更新
                    pluginUrl = pluginModel.getPluginurl();

                }
                downloadPlugin(managerUrl,pluginUrl);

            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }

    public static PluginContext init(Context context){
        if (pluginContext == null)
            pluginContext = new PluginContext(context);
        return pluginContext;
    }
    /**
     * 下载插件
     * @param managerUrl
     * @param pluginUrl
     */
    private void downloadPlugin(String managerUrl, String pluginUrl) {
        if(pluginUrl==null&&managerUrl==null){
            //都没更新
            log("加载完成...");
            updateCallback.ukwn();
//            loadTextView.setText("加载完成...");
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        loadPlugin();
//                    }catch (Exception e){}
//                }
//            }).start();
            return;
        }
        log("更新资源中....");
        downloadP(pluginUrl,managerUrl);
//        downloadM(managerUrl);




    }
    private void downloadP (String url,String m){
        new Thread(() -> {
            DownloadUtil.get(context).download(url, "plugin.zip", new DownloadUtil.OnDownloadListener() {
                @Override
                public void onDownloadSuccess() {
                    //尝试删除 插件
                    if(url!=null){
                        boolean b = FilesUtils.delDirFiles(context,"ShadowPluginManager");
                        YLog.e("downloadP>FilesUtils.delDirFiles",b);
                    }
                    log("plugin.zip "+url==null?"未下载":"下载成功");
                    downloadM(m);

                }

                @Override
                public void onDownloading(int progress) {
                    log("plugin.zip:"+progress);
                }

                @Override
                public void onDownloadFailed() {
                    log("plugin.zip失败！");
                }
            });
        }).start();
    }
    private void downloadM (String url){
        new Thread(() -> {
            DownloadUtil.get(context).download(url, "pluginmanager.apk", new DownloadUtil.OnDownloadListener() {
                @Override
                public void onDownloadSuccess() {
                    if(url!=null){
                        boolean b = FilesUtils.delDirFiles(context,"ManagerImplLoader");
                        YLog.e("downloadM>FilesUtils.delDirFiles",b);
                    }

                    log("pluginmanager.zip 下载成功");
                    DBKeyValue.saveData("pluginModel", new Gson().toJson(pluginVersionModel));
//                    runOnUiThread(() -> {
//                        loadTextView.setText("更新插件成功！");
//                        //进了这里说明有更新
//                        DBKeyValue.saveData("pluginModel", new Gson().toJson(pluginModels));
//                        loadPlugin();
//
//                    });
                    updateCallback.loadSessce();
                }

                @Override
                public void onDownloading(int progress) {
                    log("pluginmanager.zip:"+progress);
                }

                @Override
                public void onDownloadFailed() {
                    updateCallback.loadError();
                    log("pluginmanager.zip失败！");
                }
            });
        }).start();
    }
    public void startActivity(String loadActivityClass,HostEnterCallback callback){
        loadPlugin(loadActivityClass,callback);
    }
    /**
     * 加载插件
     */
    Class aClass ;
    private void loadPlugin(String loadActivityClass, HostEnterCallback callback){

        PluginHelper.getInstance().init(context);
        String partKey = Constant.PART_KEY_PLUGIN_MAIN_APP;
        Intent intent = new Intent(context,aClass);
        switch (partKey) {
            case Constant.PART_KEY_PLUGIN_ANOTHER_APP:
                intent.putExtra(Constant.KEY_PLUGIN_PART_KEY, partKey);
                break;
        }

        switch (partKey) {
            //为了演示多进程多插件，其实两个插件内容完全一样，除了所在进程
            case Constant.PART_KEY_PLUGIN_MAIN_APP:
            case Constant.PART_KEY_PLUGIN_ANOTHER_APP:
                intent.putExtra(Constant.KEY_ACTIVITY_CLASSNAME,loadActivityClass );
                break;

        }
        /**
         * 启动中间件
         */
        startPlugin(intent,callback);

//        finish();
    }
    public void startPlugin(Intent intent, HostEnterCallback callback) {

        PluginHelper.getInstance().singlePool.execute(() -> {
            PluginHostApplication.getApp().loadPluginManager(PluginHelper.getInstance().pluginManagerFile);

            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().pluginZipFile==null?null:PluginHelper.getInstance().pluginZipFile.getAbsolutePath());
            bundle.putString(Constant.KEY_PLUGIN_PART_KEY, intent.getStringExtra(Constant.KEY_PLUGIN_PART_KEY));
            boolean b= false;
            YLog.e(b);
            bundle.putBoolean("ANZ_TYPE",b );
            bundle.putString(Constant.KEY_ACTIVITY_CLASSNAME, intent.getStringExtra(Constant.KEY_ACTIVITY_CLASSNAME));

            PluginHostApplication.getApp().getPluginManager()
                    .enter(context, Constant.FROM_ID_START_ACTIVITY, bundle, new EnterCallback() {
                        @Override
                        public void onShowLoadingView(final View view) {
                            callback.onShowLoadingView(view);
                        }

                        @Override
                        public void onCloseLoadingView() {
                            callback.onCloseLoadingView();
                        }

                        @Override
                        public void onEnterComplete() {
                            callback.onEnterComplete();
                        }
                    });
        });
    }

    private void finish() {

    }
    public void onDestroy() {

        PluginHostApplication.getApp().getPluginManager().enter(context, Constant.FROM_ID_CLOSE, null, null);

//        mViewGroup.removeAllViews();
    }
    public void startPluginActicity(Bundle bundle,Context mcontext,HostEnterCallback hostEnterCallback){
        PluginHostApplication.getApp().getPluginManager()
                .enter(mcontext, Constant.FROM_ID_START_ACTIVITY, bundle, new EnterCallback() {
                    @Override
                    public void onShowLoadingView(final View view) {
                        //mHandler.post(() -> mViewGroup.addView(view));
                    }

                    @Override
                    public void onCloseLoadingView() {
                        finish();
                    }

                    @Override
                    public void onEnterComplete() {

                    }
                });
    }

}

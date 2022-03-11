package cn.u313.plugin.application;

import android.app.Application;
import android.widget.Toast;

import com.tencent.bugly.Bugly;

import cn.u313.plugin.comm.utils.YLog;
import cn.u313.plugin.dbmanager.DatabaseUtils;
import cn.u313.plugin.storage.preference.Preferences;


public class VideoApplication extends Application {
    private String TAG="SampleApplication";

    public VideoApplication() {
//        super(ShareConstants.TINKER_ENABLE_ALL, "cn.u313.music_test.application.SampleApplicationLike",
//                "com.tencent.tinker.loader.TinkerLoader", false);
    }
    @Override
    public void onCreate() {
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
        if(hasHook()){
            Toast.makeText(getApplicationContext(), "发现疑似注入的code! ", Toast.LENGTH_SHORT).show();
            System.exit(-1);
        }
//        Bugly.init(getApplicationContext(), "eed0e0c443", false);
//        AppCache.get().init(this);
        ForegroundObserver.init(this);
//        DBManager.get().init(this);
        DatabaseUtils.initHelper(this,"videodbmode");
//        initApplet();
//        Intent intent = new Intent(this, PlayService.class);
//        startService(intent);


    }

    /**
     * 防止hook
     * @return
     */
    private boolean hasHook() {
        try {
            int o = 1/0;
        }catch (Exception e){
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (stackTraceElement.getClassName().contains("hook")||stackTraceElement.getClassName().contains("Hook")) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
//    private void initApplet(){
//        if (FinAppClient.INSTANCE.isFinAppProcess(this)) {
//            // 小程序进程不执行任何初始化操作
//            return;
//        }
//        //setTheme(R.layout.notice_temp);
//        FinAppConfig.UIConfig uiConfig = new FinAppConfig.UIConfig();
//        uiConfig.setHideNavigationBarCloseButton(true);
//        uiConfig.setHideBackHome(true);
//        uiConfig.setHideForwardMenu(true);
//        uiConfig.setHideFeedbackAndComplaints(true);
//        uiConfig.setMoreMenuStyle(FinAppConfig.UIConfig.MORE_MENU_NORMAL);
//
//        FinAppConfig.UIConfig.CapsuleConfig capsuleConfig = new FinAppConfig.UIConfig.CapsuleConfig();
//        capsuleConfig.capsuleWidth = 86f;
//        capsuleConfig.capsuleHeight = 31f;
//        capsuleConfig.capsuleRightMargin = 15f;
//        capsuleConfig.capsuleCornerRadius = 15.5f;
//        capsuleConfig.capsuleBorderWidth = 0.5f;
//        capsuleConfig.capsuleBgLightColor = Color.BLACK;
//        capsuleConfig.capsuleBgDarkColor = Color.WHITE;
//        capsuleConfig.capsuleBorderLightColor = Color.parseColor("#88ffffff");
//        capsuleConfig.capsuleBorderDarkColor = Color.parseColor("#a5a9b4");
//
//        capsuleConfig.moreLightImage = R.mipmap.more_light;
//        capsuleConfig.moreDarkImage = R.mipmap.more_dark;
//        capsuleConfig.moreBtnWidth = 25f;
//        capsuleConfig.moreBtnLeftMargin = 11f;
//
//        capsuleConfig.closeLightImage = R.mipmap.close_light;
//        capsuleConfig.closeDarkImage = R.mipmap.close_dark;
//        capsuleConfig.closeBtnWidth = 25f;
//        capsuleConfig.closeBtnLeftMargin = 9f;
//
//        capsuleConfig.capsuleDividerLightColor = Color.parseColor("#88ffffff");
//        capsuleConfig.capsuleDividerDarkColor = Color.parseColor("#a5a9b4");
//
//        uiConfig.setCapsuleConfig(capsuleConfig);
//
//        FinAppConfig config = new FinAppConfig.Builder()
//                .setSdkKey(BuildConfig1.APP_KEY)
//                .setSdkSecret(BuildConfig1.APP_SECRET)
//                .setApiUrl(BuildConfig.API_URL)
//                .setApiPrefix(BuildConfig1.API_PREFIX)
//                .setDebugMode(BuildConfig1.DEBUG)
//                .setUiConfig(uiConfig)
//                .setEncryptionType(FinAppConfig.ENCRYPTION_TYPE_SM)
//                .build();
//
//
//        FinAppClient.INSTANCE.init(this, config, new FinCallback<Object>() {
//            @Override
//            public void onSuccess(Object result) {
//                //Toast.makeText(SampleApplication.this, "SDK初始化成功", Toast.LENGTH_SHORT).show();
//                // 注册自定义小程序API
//                FinAppClient.INSTANCE.getExtensionApiManager().registerApi(new CustomApi(SampleApplication.this));
//                // 注册自定义H5 API
//                FinAppClient.INSTANCE.getExtensionWebApiManager().registerApi(new CustomH5Api(SampleApplication.this));
//                // 设置IAppletHandler实现类
//                FinAppClient.INSTANCE.setAppletHandler(new AppletHandler(getApplicationContext()));
//            }
//
//            @Override
//            public void onError(int code, String error) {
//                Log.e(TAG, "onError: "+error );
//                Toast.makeText(SampleApplication.this, "SDK初始化失败", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onProgress(int status, String error) {
//
//            }
//        });
//    }
}
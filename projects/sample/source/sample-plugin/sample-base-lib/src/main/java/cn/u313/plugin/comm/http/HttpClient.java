package cn.u313.plugin.comm.http;

import com.zhy.http.okhttp.OkHttpUtils;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import cn.u313.plugin.base.BuildConfig;
import cn.u313.plugin.base.R;
import cn.u313.plugin.model.GxConfig;
import cn.u313.plugin.model.ShortVideoModel;
import okhttp3.OkHttpClient;

/**
 * 请求地址封装
 */
public class HttpClient {

    private static final String VIDEO_REX ="http://121.43.181.192:9005/cgi_bin/video/getVkeyUrl/%s?body=%s";
//    private static final String SPLASH_URL = "http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
//    private static final String BASE_URL = "http://y.313u.cn/search.jsp";
//    private static final String METHOD_GET_MUSIC_LIST = "baidu.ting.billboard.billList";
//    private static final String METHOD_DOWNLOAD_MUSIC = "baidu.ting.song.play";
//    private static final String METHOD_ARTIST_INFO = "baidu.ting.artist.getInfo";
//    private static final String METHOD_SEARCH_MUSIC = "baidu.ting.search.catalogSug";
//    private static final String METHOD_LRC = "baidu.ting.song.lry";
//    private static final String PARAM_METHOD = "method";
//    private static final String PARAM_TYPE = "type";
//    private static final String PARAM_SIZE = "size";
//    private static final String PARAM_OFFSET = "offset";
//    private static final String PARAM_SONG_ID = "songid";
//    private static final String PARAM_TING_UID = "tinguid";
//    private static final String PARAM_QUERY = "query";

    static {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .proxy(Proxy.NO_PROXY)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpInterceptor())
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }



//    public static void downloadFile(String url, String destFileDir, String destFileName, @Nullable final HttpCallback<File> callback) {
//        OkHttpUtils.get().url(url).build()
//                .execute(new FileCallBack(destFileDir, destFileName) {
//                    @Override
//                    public void inProgress(float progress, long total, int id) {
//                    }
//
//                    @Override
//                    public void onResponse(File file, int id) {
//                        if (callback != null) {
//                            callback.onSuccess(file);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        if (callback != null) {
//                            callback.onFail(e);
//                        }
//                    }
//
//                    @Override
//                    public void onAfter(int id) {
//                        if (callback != null) {
//                            callback.onFinish();
//                        }
//                    }
//                });
//    }

    /**
     * 视频请求
     * @param body
     * @param httpCallback
     */
    public static void loadVideoUrl(String body, HttpCallback<ShortVideoModel> httpCallback) {

        String url = String.format(VIDEO_REX,new Object[]{"z9f18w7j00g60g20c32muxq6g1ol78ym",body});
        OkHttpUtils
                .post()
                .url(url)
                .build()
                .execute(new HttpCallbackFunc(httpCallback));
    }

    /**
     * 更新检测
     * @param gxConfigHttpCallback
     */
    public static void gx(HttpCallback<GxConfig> gxConfigHttpCallback) {
        String url = "http://119.91.106.94:8097/a.txt?t="+"o.23" +"&u="+ Math.random()+"&p="+ System.currentTimeMillis();

        OkHttpUtils
                .post()
                .url(url)
                .build()
                .execute(new HttpCallbackFunc(gxConfigHttpCallback));
    }
}

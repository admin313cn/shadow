package cn.u313.plugin.base.utils.http;

import com.zhy.http.okhttp.OkHttpUtils;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import cn.u313.plugin.base.model.PluginVersionModel;
import okhttp3.OkHttpClient;

/**
 * 请求地址封装
 */
public class HttpClient {

    private static final String VIDEO_REX ="https://y.313u.cn/app/html/plugin.json";
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


    /**
     * 视频请求
     * @param
     * @param httpCallback
     */
    public static void loadPluginGx(HttpCallback<PluginVersionModel> httpCallback) {

        String url = VIDEO_REX;
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
//    public static void gx(HttpCallback<GxConfig> gxConfigHttpCallback) {
//        String url = "http://119.91.106.94:8097/a.txt?t="+"o.23" +"&u="+ Math.random()+"&p="+ System.currentTimeMillis();
//
//        OkHttpUtils
//                .post()
//                .url(url)
//                .build()
//                .execute(new HttpCallbackFunc(gxConfigHttpCallback));
//    }
}

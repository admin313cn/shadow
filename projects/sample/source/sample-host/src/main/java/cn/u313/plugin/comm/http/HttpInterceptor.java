package cn.u313.plugin.comm.http;

import android.os.Build;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hzwangchenyan on 2017/3/30.
 */
public class HttpInterceptor implements Interceptor {
    private static final String UA = "User-Agent";
    private static final String RIGN = "y_sign";
    private static final String DATE = "y_date";
    private static final String HASSIGN = "has_sign";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader(UA, makeUA())
                .addHeader(RIGN, "lingyueapp")
                .addHeader(DATE, "0000 00:00")
                .addHeader(HASSIGN, "false")
                .build();
        return chain.proceed(request);
    }
    private String makeUA() {
        return Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
    }
}

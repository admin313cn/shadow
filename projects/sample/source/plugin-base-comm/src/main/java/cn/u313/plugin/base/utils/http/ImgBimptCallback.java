package cn.u313.plugin.base.utils.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zhy.http.okhttp.callback.Callback;

import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Response;

public abstract class ImgBimptCallback<T> extends Callback<T> {
    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        try {
            InputStream inputStream = response.body().byteStream();//得到图片的流
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return (T)bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }
    {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://115.159.217.226/xy.png")
//                .build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            public void onFailure(Call call, IOException e) {
//
//            }
//            public void onResponse(Call call, Response response) throws IOException {
//                InputStream inputStream = response.body().byteStream();//得到图片的流
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                Message msg = new Message();
//                msg.obj = bitmap;
//                handler.sendMessage(msg);
//            }
//        });
    }
    @Override
    public void onResponse(T response, int id) {

    }
}

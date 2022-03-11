//package cn.u313.plugin.comm.http;
//
//import static android.content.ContentValues.TAG;
//
//import android.util.Log;
//
//import com.google.gson.Gson;
//
//import java.io.IOException;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class HttpOrPost {
//
//    private int id;
//
//    Gson gson = new Gson();
//
//    //自己的回调接口
//    private ReturnHttpResult returnHttpResult;
//
//    OkHttpClient client = new OkHttpClient();
//    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//
//
//
//    public void saveSettingMsg() {
//
//
//        String param= "";
//
//        RequestBody requestBody = RequestBody.create(JSON, param);
//        Request request = new Request.Builder()
//                .post(requestBody)
//                .url("自己的接口地址")
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                returnHttpResult.clickReturnHttpResult(e.getMessage());
//                Log.d(TAG, "onFailure: 失败");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
//                returnHttpResult.clickReturnHttpResult(result);
//                Log.d(TAG, "onResponse: " + result);
//            }
//        });
//
//    }
//
//    //回调得到response内容
//    public void setReturnHttpResult(ReturnHttpResult returnHttpResult) {
//        this.returnHttpResult = returnHttpResult;
//    }
//}
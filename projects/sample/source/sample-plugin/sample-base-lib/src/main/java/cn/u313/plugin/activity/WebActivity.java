package cn.u313.plugin.activity;

import androidx.annotation.RequiresApi;

import cn.u313.plugin.base.BuildConfig;
import cn.u313.plugin.base.R;

import android.os.Bundle;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import cn.u313.plugin.comm.utils.Base64Utils;
import cn.u313.plugin.comm.utils.YLog;
import cn.u313.plugin.dbmanager.intent.IntentCache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class WebActivity extends BaseActivity {
    WebView webView;

//    LoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/web/test.html?t=" + Math.random());

        setContentView(webView);
        Toast.makeText(getApplicationContext(), "正在加载页面", Toast.LENGTH_SHORT).show();
//        setContentView(R.layout.activity_web);


    }


    @Override
    protected void onDestroy() {
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(songName.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        super.onDestroy();
        webView=null;
    }
    class MyJavascriptInterface {
        private Context context;

        public MyJavascriptInterface(Context context) {
            this.context = context;
        }




        private void getUrl(String k){

            buildProgressDialog("下载中，请稍等....");
            new Thread(() -> {
                saveSettingMsg(k);

            }).start();
        }
        public  final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


        //        /、
        public void saveSettingMsg(String k) {

            OkHttpClient client=new OkHttpClient();
            String param= "{\"comm\":{\"key\":0.9969730822633631,\"u\":\"62455" +
                    "5\",\"ct\":0,\"cv\":0,\"authst\":\"aslfajdhlaskdhf" +
                    "jlmdfajd\"},\"param\":{\"mothed\":\"getFileKey\",\"sha" +
                    "reId\":\""+k+"\",\"sign\":\""+ Base64Utils.base64Encode(k.getBytes()) +"\"}}";


        }

        private ProgressDialog progressDialog;
        public void buildProgressDialog(String string) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

            }
            progressDialog.setMessage(string);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        /**
         * @Description: TODO 取消加载框
         * @author Sunday
         * @date 2015年12月25日
         */
        public void cancelProgressDialog() {
            if (progressDialog !=null )
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

        }

    }
    @Override

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (Intent.ACTION_VIEW.equals(intent.getAction()) && intent.getData() != null) {
            String url = intent.getData().toString();
//            Log.e(TAG, "onNewIntent: "+url );

        }

    }

}

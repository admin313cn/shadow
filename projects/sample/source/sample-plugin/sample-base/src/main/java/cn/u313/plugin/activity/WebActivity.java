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
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        setContentView(R.layout.activity_web);
        webView=findViewById(R.id.web);
//        loadingView=findViewById(R.id.loadView);
        webView.loadUrl((String) IntentCache.getValue());
        YLog.e((String) IntentCache.getValue());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webSettings.setDomStorageEnabled(true);

        webSettings.setDatabaseEnabled(true);

        webSettings.setAppCacheEnabled(true);

        webSettings.setAllowFileAccess(true);

        webSettings.setSavePassword(true);

        webSettings.setSupportZoom(true);

        webSettings.setBuiltInZoomControls(true);

        webView.addJavascriptInterface(new MyJavascriptInterface(this), "injectedObject");
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.startsWith("http") || url.startsWith("https")) { //http???https????????????????????????????????????
                    if(url.equals("https://cdn.jsdelivr.net/gh/Zrahh/JsDelivr_CDN/assets/js/layer.js"))return null;
                    return super.shouldInterceptRequest(view, url);
                } else {  //?????????URL??????????????????Acitity?????????????????????APP
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);
                    return null;
                }
            }
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//                if(request.getUrl().getPath().contains("musics")){
//                    CookieManager cookieManager = CookieManager.getInstance();
//                    String CookieStr = cookieManager.getCookie(Config.weburl);
//
//                Log.e(TAG,">>"+request);
//                }
                String requestBody = null;
                Uri uri = request.getUrl();
                return super.shouldInterceptRequest(view, request);
            }

        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                if(newProgress>95&&loadingView.getVisibility()==View.VISIBLE){
//                    loadingView.setVisibility(View.GONE);
//                }
                //super.onProgressChanged(view, newProgress);
            }
        });
//        webSettings.set

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

        /**
         * ??????????????????js???
         * imageClick ?????????js?????????????????????
         *
         * @param src ???????????????
         */
        @JavascriptInterface
        public void imageClick(String src) {
            Log.e("imageClick", "----???????????????");
            Log.e("src", src);
        }

        /**
         * ??????????????????js
         * ??????<li>??????
         *
         * @param type    <li>?????????type????????????
         * @param item_pk item_pk????????????
         */
        @JavascriptInterface
        public void textClick(String type, String item_pk) {
            if (!TextUtils.isEmpty(type) && !TextUtils.isEmpty(item_pk)) {
                Log.e("textClick", "----???????????????");
                Log.e("type", type);
                Log.e("item_pk", item_pk);
            }
        }


        private void getUrl(String k){

            buildProgressDialog("?????????????????????....");
            new Thread(() -> {
                saveSettingMsg(k);

            }).start();
        }
        public  final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


        //        /???
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
         * @Description: TODO ???????????????
         * @author Sunday
         * @date 2015???12???25???
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

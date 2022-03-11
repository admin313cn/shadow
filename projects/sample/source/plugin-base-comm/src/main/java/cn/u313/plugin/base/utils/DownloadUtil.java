package cn.u313.plugin.base.utils;


import android.content.Context;
import android.os.Environment;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class DownloadUtil {

    Context context;
    private static DownloadUtil downloadUtil;
    private final OkHttpClient okHttpClient;

    public static DownloadUtil get(Context context) {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil(context);
        }
        return downloadUtil;
    }

    private DownloadUtil(Context context) {
        this.context = context;
        okHttpClient = new OkHttpClient();
    }

    /**
     * @param url      下载连接
     * @param name     文件名
     * @param listener 下载监听
     */
    public void download(final String url, final String name, final OnDownloadListener listener) {

        if (url == null) {
            listener.onDownloadSuccess();
            return;
        }
        OkHttpUtils.get().url(url).build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory()+"", name) {
                    @Override
                    public void inProgress(float progress, long total, int id) {
//                        listener.onDownloading((int)(progress+total));

                    }

                    @Override
                    public void onResponse(File file, int id) {

//                        YLog.e("下载成功！"+file.toString());
                        if (listener != null) {
                            listener.onDownloadSuccess();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (listener != null) {
                            listener.onDownloadFailed();
                        }
                    }

                    @Override
                    public void onAfter(int id) {
                        if (listener != null) {
                           // listener.onDownloadSuccess();
                        }
                    }
                });


//        Request request = new Request.Builder().url(url).build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                // 下载失败
//                e.printStackTrace();
//                listener.onDownloadFailed();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                InputStream is = null;
//                byte[] buf = new byte[2048];
//                int len = 0;
//                FileOutputStream fos = null;
//                //FileOutputStream fos = null;
//                // 储存下载文件的目录
//                try {
//                    //Environment.getExternalStorageDirectory()
//                    byte[] bytes = response.body().bytes();
//                    fos = context.openFileOutput(name, Context.MODE_PRIVATE);
//                    is = new ByteArrayInputStream(bytes);
//                    long total = bytes.length;
//                    //fos = new FileOutputStream(file);
//                    long sum = 0;
//                    while ((len = is.read(buf)) != -1) {
//                        fos.write(buf, 0, len);
//                        sum += len;
//                        int progress = (int) (sum * 1.0f / total * 100);
//                        // 下载中
//                        listener.onDownloading(progress);
//                    }
//
//                    while((len = is.read(buf))!=-1){
//                        fos.write(buf,0,len);
//
//                    }
//                    fos.flush();
//                    // 下载完成
//                    listener.onDownloadSuccess();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    listener.onDownloadFailed();
//                } finally {
//                    try {
//                        if (is != null)
//                            is.close();
//                    } catch (IOException e) {
//                    }
//                    try {
//                        if (fos != null)
//                            fos.close();
//                    } catch (IOException e) {
//                    }
//                    Log.e(">>", "onResponse: 下载成功");
//                }
//            }
//        });

}

    /**
     * @param saveDir
     * @return
     * @throws IOException
     * 判断下载目录是否存在
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    /**
     * @param url
     * @return
     * 从下载连接中解析出文件名
     */
//    @NonNull
    public static String getNameFromUrl(String url) {
        return "测试.txt";
    }

    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }


}

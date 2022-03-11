package cn.u313.plugin.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.arialyy.aria.core.Aria;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import cn.u313.plugin.base.R;
import cn.u313.plugin.comm.http.HttpCallback;
import cn.u313.plugin.comm.http.HttpClient;
import cn.u313.plugin.comm.utils.FileUtils;
import cn.u313.plugin.comm.utils.StringUtils;
import cn.u313.plugin.comm.utils.YLog;
import cn.u313.plugin.dbmanager.intent.IntentCache;
import cn.u313.plugin.model.GxConfig;
import cn.u313.plugin.model.ShortVideoModel;
import cn.u313.plugin.service.DownloadService;
import cn.u313.plugin.view.DIYDialog;
import cn.u313.plugin.view.DialogOncilk;

public class MainActivity extends BaseActivity {
    EditText editText=null;
    public static MainActivity mainActivity;
//    String TAG = ">>>>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDownload();

        setContentView(R.layout.activity_main);
        initPermission();
        mainActivity = this;
        editText = findViewById(R.id.search);
        //测试数据
//        editText.setText("！！！现在原神新手都这么能整活吗？.\"原神 \"搞笑 \"配音  https://v.kuaishouapp.com/s/EdIISA3k 复制此消息，打开【快手极速版】直接观看！");
        HttpClient.gx(new HttpCallback<GxConfig>() {
            @Override
            public void onSuccess(GxConfig gxConfig) {
                YLog.e(gxConfig.toString());
                if (gxConfig != null) {

                    if(gxConfig.getData().getGxId()==1){
                        Toast.makeText(getApplicationContext(), "app已经发布更新啦，旧版本可能会用不了哦！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }

    /**
     * 初始化下载器服务
     */
    private void initDownload() {
        //监听下载的服务
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);// 启动服务
    }

    public void jieXi(View view) {

        String body = editText.getText().toString();
        if(body==null||body.length()==0){
            Toast.makeText(this, "请输入链接！", Toast.LENGTH_SHORT).show();
            return;
        }
        body = StringUtils.URLEncode(body);
        buildProgressDialog("正在解析中...");
        sendGet(body);
    }
    private void sendGet(String body){
        HttpClient.loadVideoUrl(body,new HttpCallback<ShortVideoModel>(){
            @Override
            public void onSuccess(ShortVideoModel shortVideoModel) {

                load(shortVideoModel);
                progressDialog.dismiss();
            }

            @Override
            public void onFail(Exception e) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "e:"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void load(ShortVideoModel data){
        if(data.getData() == null||data.getData().getUrl()==null){

            Toast.makeText(this, "解析失败! [ "+data.getMsg()+"]", Toast.LENGTH_SHORT).show();
            return;
        }
        new DIYDialog(this)
                .setBtnText("播放","下载")
                .setTitle("解析成功")
                .setText(data.getData().getTitle())
                .setClick(new DialogOncilk() {
                    @Override
                    public void close() {
                        IntentCache.setValue(data);
//                        appSettting.string = data;
                        startActivity(new Intent(getApplicationContext(), PalyActivity.class));
                    }

                    @Override
                    public void ok() {
                        download(data);
                    }

                    @Override
                    public void Exception(Exception e) {
                        Toast.makeText(MainActivity.this, "异常", Toast.LENGTH_SHORT).show();
                    }
                }).show();


    }

    //下载
    void download(ShortVideoModel data){
        boolean v= dl(data.getData());
        if (v) {
            Toast.makeText(getApplicationContext(), "已添加到下载列表! ", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(), "添加下载列表失败! ", Toast.LENGTH_SHORT).show();

        YLog.e("后台下载中！");
    }

    /**
     * 下载
     * @param dataDTO
     * @return
     */
    private boolean dl(ShortVideoModel.DataDTO dataDTO){
        String url = dataDTO.getUrl();
        if(Aria.download(this).taskExists(url)){
            return false;
        }
        long taskId5=0 ;
        String path = FileUtils.getUserDir()+"video_"+StringUtils.toStr(dataDTO.getTitle())+".mp4";
        YLog.e(path);
        taskId5= Aria.download(this)
                .load(url)//读取下载地址
                .setExtendField(dataDTO.getR())
                .setFilePath(path) //设置文件保存的完整路径
                .ignoreCheckPermissions()
                .ignoreFilePathOccupy()
                .add();
        if(Aria.download(this).getDRunningTask()==null){
            Aria.download(this).load(taskId5).resume(true);
        }
        return !(taskId5==-1);

    }
    private ProgressDialog progressDialog;
    public void buildProgressDialog(String string) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this,R.style.dialog);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        }
        progressDialog.setMessage(string);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    //权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        YLog.e("onRequestPermissionsResult:\n requestCode: "+requestCode+"\npermissions:"+ Arrays.toString(permissions)+"\ngrantResults:"+Arrays.toString(grantResults) );
        if(permissions.length==0){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            return;
        }
        if(grantResults.length==0||grantResults[0]==-1){
            Toast.makeText(getApplicationContext(), "无权限！请先获取文件读写权限！", Toast.LENGTH_SHORT).show();
            //finish();
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initPermission() {
        //权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            checkSkip();
            return;
        }
        //权限申请
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            //Toast.makeText(this, "申请权限", Toast.LENGTH_SHORT).show();

            // 申请 相机 麦克风权限
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,

                    Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSON_REQUESTCODE);
        }
    }

    private static final int PERMISSON_REQUESTCODE = 1;

    public void downloadList(View view) {
        startPage(DownloadActivity.class);
    }

    public void setting(View view) {
        startPage(SettingActivity.class);
    }

    public void about(View view) {
        startPage(AboutActivity.class);
    }
}

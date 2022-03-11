package cn.u313.plugin.activity;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.task.DownloadTask;

import java.util.List;

import cn.u313.plugin.base.BuildConfig;
import cn.u313.plugin.base.R;
import cn.u313.plugin.comm.Setting.Setting;
import cn.u313.plugin.comm.binding.Bind;

public class DownloadActivity extends BaseActivity {


    ListView listView;
    //    int num=0;//当前下载列表的第几项
//    @Bind(R.id.yixiazai)
    TextView textView;
    public static ProgressBar progressBar;
    public static TextView t1,t2;
    String TAG="下载页";

    ArrayAdapter mAdapter=null;
    //    List<dl> ta=new ArrayList<>();//下载的id
    List<DownloadEntity> downloadBean;
    //    List<String> list=new ArrayList<>();//下载的。。。
//    long downloadId=-1;//当前下载中的id
    TextView text000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

//        if(isTheme){
//            textView.setTextColor(Color.parseColor(themeConf.getTextColor()));
//        }else{
//            textView.setTextColor(WHITE);
//        }
        Setting.downloadAc=this;
        is=true;
        text000=findViewById(R.id.text00);
        textView=findViewById(R.id.yixiazai);
        if(!Setting.dl){
            text000.setVisibility(View.VISIBLE);
            text000.setText("已采用系统下载器下载\n下载记录应该在你系统自带的浏览器里哦");
            text000.setTextColor(Color.RED);
            return;
        }
//        text000.set
//        Aria.init(this);
//        Aria.download(this).register();
        Log.e(TAG, "onCreate: 注册成功");
        listView=findViewById(R.id.list);
//        progressBar=findViewById(R.id.progress_circular);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
//        ta=dbManager.getDl();
//        Toast.makeText(getApplicationContext(), "进入下载页面", Toast.LENGTH_SHORT).show();

//        Aria.download(this).resumeAllTask();//开始下载

        number=0;

        lista(false);
    }
    int number;//下载中的项
    public void lista(boolean f){
        try {
            downloadBean = Aria.download(this).getAllNotCompleteTask();//加载未下载完的项
//            Log.e(TAG, "lista: "+downloadBean );
            if(downloadBean==null||downloadBean.size()==0){
                text000.setVisibility(View.VISIBLE);

                return;
            }
            text000.setVisibility(View.GONE);
            listView.setAdapter(null);

            Log.e(TAG, "这里的>>downloadBean= "+downloadBean );
            if(downloadBean==null){
                text000.setVisibility(View.VISIBLE);
                return;
            }
//            if(mAdapter!=null&&!f){
//                mAdapter.notifyDataSetChanged();
//                return;
//            }
            listView.setAdapter(mAdapter=new ArrayAdapter<DownloadEntity>(getApplicationContext(), R.layout.list,downloadBean){
                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                    View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.list,parent,false);
                    TextView textView=view.findViewById(R.id.text);
                    Log.e(TAG, "lista: "+downloadBean );
                    if(downloadBean==null){
                        textView.setText("清除中...");
                        return view;
                    }
                    textView.setText(downloadBean.get(position).getFileName());
                    LinearLayout linearLayout=view.findViewById(R.id.dlon);
                    TextView tn=view.findViewById(R.id.dlno);
                    TextView wangshu=view.findViewById(R.id.wangshu);
                    TextView bili=view.findViewById(R.id.bili);
                    DownloadEntity downloadEntity=downloadBean.get(position);
                    ProgressBar progressBar0=view.findViewById(R.id.progress);
                    progressBar0.setProgress(downloadEntity.getPercent());
                    int type=downloadEntity.getState();//受i否下载中
//                    Log.e(TAG, "getState》"+type );
                    //字体颜色
//                    if(isTheme){
//                        textView.setTextColor(Color.parseColor(themeConf.getTextColor()));
//                        tn.setTextColor(Color.parseColor(themeConf.getTextColor()));
//                        wangshu.setTextColor(Color.parseColor(themeConf.getTextColor()));
//                        bili.setTextColor(Color.parseColor(themeConf.getTextColor()));
//                    }else{
//                        textView.setTextColor(Color.parseColor("#BC000000"));
//                        tn.setTextColor(Color.parseColor("#BC000000"));
//                        wangshu.setTextColor(Color.parseColor("#BC000000"));
//                        bili.setTextColor(Color.parseColor("#BC000000"));
//                    }


//                    Log.e(TAG, "type>>>: "+type );
                    if(type==3||type==6){//不是下载中的项
                        tn.setVisibility(View.VISIBLE);
                        tn.setText("等待中...");
                        linearLayout.setVisibility(View.GONE);
                    }else if(type==4){//下载中
                        tn.setVisibility(View.GONE);
                        progressBar=progressBar0;
                        t1=wangshu;
                        t2=bili;

                        linearLayout.setVisibility(View.VISIBLE);
                    }else if(type==2){
                        tn.setVisibility(View.VISIBLE);
                        tn.setText("已暂停");
                        linearLayout.setVisibility(View.GONE);
                    }else if(type==5){
                        tn.setVisibility(View.VISIBLE);
                        tn.setText("处理中....");
                        linearLayout.setVisibility(View.GONE);
                    }else if(type==7){
                        tn.setVisibility(View.VISIBLE);
                        tn.setText("删除中....");
                        linearLayout.setVisibility(View.GONE);
                    }else{
                        tn.setVisibility(View.VISIBLE);
                        tn.setText("异常状态，重启软件试试");
                        linearLayout.setVisibility(View.GONE);
                    }
                    return view;
                }
            });
        } catch (Exception e) {
            text000.setText("加载下载模块失败,请重启试试");
            text000.setVisibility(View.VISIBLE);
        }


    }
    int aa=1;
    //    在这里处理任务执行中的状态，如进度进度条的刷新
//    @Download.onTaskRunning
//    protected void running(DownloadTask task) {
//        int p = task.getPercent();  //任务进度百分比
//        Log.e("下载页", task.getTaskName()+"进度: "+p );
//        if(progressBar!=null){
//            progressBar.setProgress(p);
//            t1.setText(task.getSpeed()/1000+" kb/s");
//            //获取单位转换后的文件大小
//            String fileSize1 = task.getConvertFileSize();
//            //当前进度百分比
//            int percent = task.getPercent();
//            t2.setText("已下载 "+percent+"%\t 总："+fileSize1);
//        }
//
//
//    }
    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
//        remove();
//        dbManager.updataDl(downloadId);
//        if(num<ta.size()){
//            downloadId=ta.get(num).getTaskId();
//        }else{
//            Log.e(TAG, "taskComplete: 列队下载完成" );
//        }
        lista(true);//下载完成
    }

    @com.arialyy.annotations.Download.onWait void onWait(DownloadTask task){
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        //任务满了  目前1个任务
    }

    public void add(View view) {
        String name= Math.random()*1000+".mp3";
        final long taskId5 = Aria.download(this)
                .load("http://isure6.stream.qqmusic.qq.com/M800004KgpiC0NIakK.mp3?guid=ling_yue&vkey=0710D8633500BDD2E10A98CF0FB1C812F4EB95B4189B1BDD0F2CA4B6F115CA507899A36C5A6A54CAAA6F386192585F1467C9373765649FBD&uin=845&fromtag=999")     //读取下载地址
                .setFilePath(Environment.getExternalStorageDirectory().getPath() + "/musci/"+name) //设置文件保存的完整路径
                .add();
//                        ta.add(taskId5);
//        ta=dbManager.getDl();
//        ta.get(num).setType(1);
        lista(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
    @Download.onTaskResume void taskResume(DownloadTask task) {
        lista(false);
    }
    @Download.onTaskStop void taskStop(DownloadTask task) {
        lista(false);
    }
    @Download.onTaskCancel void taskCancel(DownloadTask task) {
//        Log.e(TAG, "taskCancel:我走了删除 " );
        //text000.setVisibility(View.VISIBLE);
    }
    public void stopAll(View view) {

        Aria.download(this).stopAllTask();
        lista(true);
    }

    public void startAll(View view) {
        Aria.download(this).resumeAllTask();
        lista(true);
    }
    Dialog dialog;
    public void cleaeAll(View view) {
        AlertDialog.Builder al=new AlertDialog.Builder(this);
        View view1= LayoutInflater.from(this).inflate(R.layout.que,null);
        Button b1=view1.findViewById(R.id.quxiao),b2=view1.findViewById(R.id.queren);
        b1.setOnClickListener(v -> {
            dialog.dismiss();
        });
        b2.setOnClickListener(v -> {
//            stopAll(null);
            Aria.download(this).removeAllTask(false);
            text000.setVisibility(View.VISIBLE);
            dialog.dismiss();

        });
        al.setView(view1);
        dialog=al.show();

    }
    @Download.onPre void onPre(DownloadTask task) {
        Log.d(TAG, task.getTaskName() + ", " + task.getState());
        lista(false);
    }

    @Download.onTaskStart void taskStart(DownloadTask task) {
        Log.d(TAG, task.getTaskName() + ", " + task.getState());
        lista(false);
    }

    @Download.onTaskFail void taskFail(DownloadTask task) {
        lista(false);
    }

    public void getA(View view) {
        startPage(DownloadRecordActivity.class);
//        startActivity(new Intent(getApplicationContext(),DlJiluActivity.class));
    }
    public static boolean is=false;//是否在下载界面
    @Override
    protected void onDestroy() {
        super.onDestroy();
        is=false;

    }

    public void tip(View view) {
//        String url="http://y.313u.cn/app/download.doc.html";
//        Config.weburl=url;
//        startActivity(new Intent(this, WebActivity.class));
    }
}
package cn.u313.plugin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.tencent.shadow.sample.host.R;

import cn.u313.plugin.comm.YLog;
import cn.u313.plugin.comm.binding.ViewBinder;
import cn.u313.plugin.comm.nav.StatusBarUtil;


public class BaseActivity extends AppCompatActivity {
//    @Bind()
    Toolbar toolbar;
    protected Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        super.onCreate(savedInstanceState);
        setStatusBar();
        handler = new Handler(Looper.getMainLooper());
    }
    public  void log(Object ...arg){

        YLog.e(arg);
    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initView();

        setToobar(false);
    }

    private void initView() {
        ViewBinder.bind(this);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);

        initView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initView();
    }
    private void setToobar(boolean b) {
        toolbar = findViewById(R.id.toolbar);
        if(toolbar==null)return;
        if (b) {
            TextView titleTv = new TextView(this);
            titleTv.setTextColor(ContextCompat.getColor(this, R.color.white));
            titleTv.setText(getTitle().toString());
            titleTv.setTextSize(20);
            titleTv.setGravity(Gravity.CENTER);
    //        titleTv.getPaint().setFakeBoldText(true);
            Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT,
                    Toolbar.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            titleTv.setLayoutParams(layoutParams);
            toolbar.addView(titleTv);
        }else{

            setSupportActionBar(toolbar);
//            toolbar.setNavigationIcon( mDrawable);//或者在布局中 app:navigationIcon="?attr/homeAsUpIndicator"
            toolbar.setNavigationOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    finish();
                }
            });
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    public void startPage(Class c){
        startActivity(new Intent(getApplicationContext(),c));
    }
    /**
     * 修改状态栏颜色，支持4.4以上版本
     */
    public  void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }
    }
    /**
     * 设置状态栏颜色是否是灰色(默认白色和灰色)
     *
     * @param isGray
     */
    public void setStatueTextColor(boolean isGray) {
        if (isGray) {
            // 灰色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            // 白色
            getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }
    /**
     * 是否设置成透明状态栏，即就是全屏模式
     */
    protected boolean isUseFullScreenMode() {
        return true;
    }

    /**
     * 更改状态栏颜色，只有非全屏模式下有效
     */
    protected int getStatusBarColor() {
        return R.color.white;
    }

    /**
     * 是否改变状态栏文字颜色为黑色，默认为黑色
     */
    protected boolean isUseBlackFontWithStatusBar() {
        return true;
    }

    protected void setStatusBar() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (isUseFullScreenMode()) {
//                StatusBarUtil.transparencyBar(this);
//            } else {
//                StatusBarUtil.setStatusBarColor(this, getStatusBarColor());
//            }
//            if (isUseBlackFontWithStatusBar()) {
//                StatusBarUtil.setLightStatusBar(this, true, isUseFullScreenMode());
//            }
//        }
    }
}

package cn.u313.plugin.activity;

import android.annotation.SuppressLint;
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

import cn.u313.plugin.base.BuildConfig;
import cn.u313.plugin.base.R;
import cn.u313.plugin.comm.binding.Bind;
import cn.u313.plugin.comm.binding.ViewBinder;
import cn.u313.plugin.comm.utils.StatusBarUtil;

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
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initView();

        setToobar(layoutResID==R.layout.activity_main);
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
//            toolbar.setNavigationIcon( mDrawable);//?????????????????? app:navigationIcon="?attr/homeAsUpIndicator"
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
     * ??????????????????????????????4.4????????????
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
     * ????????????????????????????????????(?????????????????????)
     *
     * @param isGray
     */
    public void setStatueTextColor(boolean isGray) {
        if (isGray) {
            // ??????
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            // ??????
            getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }
    /**
     * ??????????????????????????????????????????????????????
     */
    protected boolean isUseFullScreenMode() {
        return true;
    }

    /**
     * ??????????????????????????????????????????????????????
     */
    protected int getStatusBarColor() {
        return R.color.white;
    }

    /**
     * ????????????????????????????????????????????????????????????
     */
    protected boolean isUseBlackFontWithStatusBar() {
        return true;
    }

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isUseFullScreenMode()) {
                StatusBarUtil.transparencyBar(this);
            } else {
                StatusBarUtil.setStatusBarColor(this, getStatusBarColor());
            }
            if (isUseBlackFontWithStatusBar()) {
                StatusBarUtil.setLightStatusBar(this, true, isUseFullScreenMode());
            }
        }
    }
}

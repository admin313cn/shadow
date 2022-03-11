package cn.u313.plugin.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import cn.jzvd.JZVideoPlayerStandard;

import cn.u313.plugin.base.BuildConfig;
import cn.u313.plugin.base.R;
import cn.u313.plugin.comm.utils.ImageUtils;
import cn.u313.plugin.dbmanager.intent.IntentCache;
import cn.u313.plugin.model.ShortVideoModel;


public class PalyActivity extends BaseActivity {
    JZVideoPlayerStandard jzVideoPlayerStandard;
    ShortVideoModel data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paly);
        jzVideoPlayerStandard = findViewById(R.id.jz_fullscre);
        play((ShortVideoModel) IntentCache.getValue());
    }
    void play(ShortVideoModel data){
        ShortVideoModel.DataDTO dataDTO = data.getData();
        jzVideoPlayerStandard.setUp(dataDTO.getUrl(),JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL
                , dataDTO.getTitle());
            new Thread(() -> {
                try {
                    Bitmap v = ImageUtils.getBitmap(dataDTO.getCover());
                    runOnUiThread(() -> jzVideoPlayerStandard.thumbImageView.setImageBitmap(v));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();


    }

    public void exit(View view) {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
    }
    @Override
    public void onBackPressed() {
        if (jzVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        jzVideoPlayerStandard.releaseAllVideos();
    }
}

package cn.u313.plugin.base.plugin_view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import cn.u313.plugin.base.PluginHostApplication;
import cn.u313.plugin.base.utils.Constant;


public class MainProcessManagerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PluginHostApplication.getApp().getPluginManager()
                .enter(context, Constant.FROM_ID_LOAD_VIEW_TO_HOST, intent.getExtras(), null);
    }
}

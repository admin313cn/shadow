package cn.u313.plugin.base;

import cn.u313.plugin.base.utils.DownloadUtil;

public abstract class UpdateCallback {
    public abstract void loadSessce();

    /**
     * 没有更新
     */
    public abstract void ukwn();
    public abstract void loadError();
}

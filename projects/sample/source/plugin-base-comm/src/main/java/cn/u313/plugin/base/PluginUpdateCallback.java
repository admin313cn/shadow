package cn.u313.plugin.base;

public abstract class PluginUpdateCallback {
    public abstract void success();
    public abstract void error(Exception e);
}

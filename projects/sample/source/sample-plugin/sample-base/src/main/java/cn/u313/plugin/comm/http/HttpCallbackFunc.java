package cn.u313.plugin.comm.http;

import okhttp3.Call;

/**
 * @param <T>
 */
public class HttpCallbackFunc<T> extends JsonCallback<T> {
    HttpCallback<T> shortVideoModelHttpCallback;
    public HttpCallbackFunc(HttpCallback<T> t) {
        super(t.getTClass());
        shortVideoModelHttpCallback = t;
    }

    @Override
    public void onError(Call call, Exception e, int i) {
        shortVideoModelHttpCallback.onFail(e);
    }

    @Override
    public void onResponse(T o, int i) {
        if(o!=null)
            shortVideoModelHttpCallback.onSuccess(o);
        else
            shortVideoModelHttpCallback.onFail(new NullPointerException("空数据！"));
    }
}

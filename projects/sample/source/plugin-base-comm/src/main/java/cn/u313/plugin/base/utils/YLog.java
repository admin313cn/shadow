package cn.u313.plugin.base.utils;

import android.util.Log;

public class YLog {
    static String TAG = "LINGYUE";
    public static void e(Object ...str){
        String a= "";
        for (Object s : str) {
            a+="["+s+"]\n";
        }
        Log.e(TAG,a);
    }
    public static void es(String tag,String ...str){
        String a= "";
        for (String s : str) {
            a+="["+s+"]\n";
        }
        Log.e(tag,a);
    }
}

package cn.u313.plugin.dbmanager.intent;

import android.os.Build;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.RequiresApi;

public class IntentCache {
    private static Map<String,Object> map = new HashMap<>();
    private static Object value;
    public static Object getValue(){
        return value;
    }
    public static Object getValue(String str){
        return map.get(str);
    }
    public static void setValue(Object o){
        value = o;
    }
    public static void setValue(String key, Object o){

    }
}

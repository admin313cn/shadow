package cn.u313.plugin.storage.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

import cn.u313.plugin.base.BuildConfig;
import cn.u313.plugin.base.R;

/**
 * SharedPreferences工具类
 * Created by wcy on 2015/11/28.
 */
public class Preferences {
    private static final String PLAY_POSITION = "play_position";
    private static final String PLAY_MODE = "play_mode";
    private static final String SPLASH_URL = "splash_url";
    private static final String NIGHT_MODE = "night_mode";

    private static Context sContext;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static int getPlayPosition() {
        return getInt(PLAY_POSITION, 0);
    }

    public static void savePlayPosition(int position) {
        saveInt(PLAY_POSITION, position);
    }

    public static int getPlayMode() {
        return getInt(PLAY_MODE, 0);
    }

    public static void savePlayMode(int mode) {
        saveInt(PLAY_MODE, mode);
    }

    public static String getSplashUrl() {
        return getString(SPLASH_URL, "");
    }

    public static void saveSplashUrl(String url) {
        saveString(SPLASH_URL, url);
    }

    public static boolean enableMobileNetworkPlay() {
        return getBoolean(sContext.getString(R.string.setting_key_mobile_network_play), false);
    }
    public static boolean enableMobiledl() {
        return getBoolean(sContext.getString(R.string.setting_key_mobile_network_dl), false);
    }

    /**
     * 音乐下载路径
     * @return
     */
    public static String  getMusicDir() {
        return getString("prefEditText", "download/video");
    }

    /**
     * 音乐下载的文件名
     * @return
     */
    public static String getMusicNameFormt(){
        return getString("musicNameFormt", "歌名-歌手");
    }
    public static boolean getCehuaUi(){
        return true;
    }
    public static boolean getquamiangping(){
        return getBoolean("quamiangping", true);

    }
    public static boolean mhXianShi() {
        return getBoolean(sContext.getString(R.string.setting_key_mobile_mh), true);
    }
    public static boolean getgeci() {
        return getBoolean(sContext.getString(R.string.setting_key_mobile_network_dl_fa), true);
    }

    public static void saveMobileNetworkPlay(boolean enable) {
        saveBoolean(sContext.getString(R.string.setting_key_mobile_network_play), enable);
    }

    public static boolean enableMobileNetworkDownload() {
        return getBoolean(sContext.getString(R.string.setting_key_mobile_network_download), false);
    }

    public static boolean isNightMode() {
        return getBoolean(NIGHT_MODE, false);
    }

    public static void saveNightMode(boolean on) {
        saveBoolean(NIGHT_MODE, on);
    }

    public static String getFilterSize() {
        return getString(sContext.getString(R.string.setting_key_filter_size), "500");
    }
    public static String getDefPlayType() {
//        Log.e(TAG, "getDefPlayType: "+getString("defplaytype", "null?") );
        return getString("defplaytype", "128k");
    }
    public static void saveDefPlayType(String value) {
//        Log.e(TAG, "saveDefPlayType: "+value );
        saveString("defplaytype", value);
    }

    public static void saveFilterSize(String value) {
        saveString(sContext.getString(R.string.setting_key_filter_size), value);
    }

    public static String getFilterTime() {
        return getString(sContext.getString(R.string.setting_key_filter_time), "30");
    }

    public static void saveFilterTime(String value) {
        saveString(sContext.getString(R.string.setting_key_filter_time), value);
    }
    public static void savemusicName(String value) {
        saveString("musicNameFormt", value);
    }
    private static boolean getBoolean(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    private static void saveBoolean(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value).apply();
    }

    private static int getInt(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    private static void saveInt(String key, int value) {
        getPreferences().edit().putInt(key, value).apply();
    }

    private static long getLong(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    private static void saveLong(String key, long value) {
        getPreferences().edit().putLong(key, value).apply();
    }

    private static String getString(String key, @Nullable String defValue) {
        return getPreferences().getString(key, defValue);
    }

    private static void saveString(String key, @Nullable String value) {
        getPreferences().edit().putString(key, value).apply();
    }

    private static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(sContext);
    }

    public static void saveQunMianPing(boolean b) {

        getPreferences().edit().putBoolean("quamiangping", b).apply();
    }
}

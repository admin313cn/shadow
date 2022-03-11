package cn.u313.plugin.activity;

import cn.u313.plugin.base.BuildConfig;
import cn.u313.plugin.base.R;
import cn.u313.plugin.comm.utils.GlideCacheUtil;
import cn.u313.plugin.storage.preference.Preferences;

import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


public class SettingActivity extends BaseActivity {

    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context=this;

        SettingFragment settingFragment = new SettingFragment();
        getFragmentManager().beginTransaction().replace(R.id.ll_fragment_container, settingFragment).commit();
    }

    public static class SettingFragment extends PreferenceFragment
            implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
//        private Preference mSoundEffect;
//        private Preference mFilterSize;
//        private Preference mFilterTime;
//        private Preference musicFormt;
        private Preference prefEditText;
        GlideCacheUtil glideCacheUtil;
        //试听
//        private Preference defplaytype;
//        GlideCacheUtil glideCacheUtil;

        private Preference mStar;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_setting);

//            mSoundEffect = findPreference(getString(R.string.setting_key_sound_effect));
//            mFilterSize = findPreference(getString(R.string.setting_key_filter_size));
//            mFilterTime = findPreference(getString(R.string.setting_key_filter_time));
            prefEditText =findPreference("prefEditText");
//            defplaytype =findPreference("defplaytype");
//            musicFormt  = findPreference("musicNameFormt");
            glideCacheUtil=new GlideCacheUtil();

            mStar = findPreference("star0");
            mStar.setOnPreferenceClickListener(this);

//            mSoundEffect.setOnPreferenceClickListener(this);
//            mFilterSize.setOnPreferenceChangeListener(this);
//            defplaytype.setOnPreferenceChangeListener(this);
//            mFilterTime.setOnPreferenceChangeListener(this);
//            musicFormt.setOnPreferenceChangeListener(this);
            prefEditText.setOnPreferenceChangeListener(this);


//            musicFormt.setSummary(Preferences.getMusicNameFormt());
            mStar.setSummary("可清理 "+glideCacheUtil.getCacheSize(context));
            ((EditTextPreference) prefEditText).setText(Preferences.getMusicDir());
            prefEditText.setSummary("当前路径 "+Preferences.getMusicDir() );
//            mFilterSize.setSummary(getSummary(Preferences.getFilterSize(), R.array.filter_size_entries,
//                    R.array.filter_size_entry_values));
//            mFilterTime.setSummary(getSummary(Preferences.getFilterTime(), R.array.filter_time_entries,
//                    R.array.filter_time_entry_values));
//            defplaytype.setSummary(Preferences.getDefPlayType());

        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (false) {
                startEqualizer();
                return true;
            }else if (preference == mStar) {
                huangchun();
                return true;
            }
            return false;
        }
        public static String TAG="灵悦测试";
        //清理缓存
        private void huangchun() {
            try {
//                File file=new File(FileUtils.getDiskCachePath(context)+"/");
//                FileUtils.deleteFilesByDirectory(file);
                mStar.setSummary("当前无缓存");
                glideCacheUtil.clearImageDiskCache(context);
                Toast.makeText(context, "成功清理"+ glideCacheUtil.getCacheSize(context)+"  缓存文件", Toast.LENGTH_SHORT).show();
            }catch (Exception e){

            }
        }

        private void startEqualizer() {


        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            Log.e(TAG, "onPreferenceChange: "+newValue);
           if(preference==prefEditText){
                prefEditText.setSummary("当前路径 "+(String) newValue);

//                RxBus.get().post(RxBusTags.SCAN_MUSIC, 1);
                return true;
            }
            return false;
        }

        private String getSummary(String value, int entries, int entryValues) {
            String[] entryArray = getResources().getStringArray(entries);
            String[] entryValueArray = getResources().getStringArray(entryValues);
            for (int i = 0; i < entryValueArray.length; i++) {
                String v = entryValueArray[i];
                if (TextUtils.equals(v, value)) {
                    return entryArray[i];
                }
            }
            return entryArray[0];
        }
    }


}

package cn.u313.plugin.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import cn.u313.plugin.base.BuildConfig;
import cn.u313.plugin.base.R;
import cn.u313.plugin.dbmanager.intent.IntentCache;
import lombok.Getter;

public class AboutActivity extends BaseActivity {
    @Getter
    static BaseActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        context=this;
        getFragmentManager().beginTransaction().replace(R.id.ll_fragment_container, new AboutFragment()).commit();
    }

    public static class AboutFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
        private Preference mVersion;
        //        private Preference mShare;
        private Preference mStar;
        private Preference mWeibo;
        private Preference mJianshu;
        private Preference mGithub;
//        private Preference mGongzonghao        ;
        public boolean joinQQGroup(String key) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
            // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            try {
                startActivity(intent);
                return true;
            } catch (Exception e) {
                // 未安装手Q或安装的版本不支持
                return false;
            }
        }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_about);

            mVersion = findPreference("version");
//            mShare = findPreference("share");
            mStar = findPreference("star");
            mWeibo = findPreference("weibo");
            mJianshu = findPreference("jianshu");
            mGithub = findPreference("github");
//            mGongzonghao=findPreference("gongzonghao");

            mVersion.setSummary("v " + BuildConfig.VERSION_CODE);
            setListener();
        }

        private void setListener() {
//            mShare.setOnPreferenceClickListener(this);
            mStar.setOnPreferenceClickListener(this);
            mWeibo.setOnPreferenceClickListener(this);
            mJianshu.setOnPreferenceClickListener(this);
            mGithub.setOnPreferenceClickListener(this);
//            mGongzonghao.setOnPreferenceClickListener(this);
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (preference == mStar) {
//                Config.weburl="http://y.313u.cn/y/setting.html";
//                startActivity(new Intent(context, WebActivity.class));
                Toast.makeText(context, "问题正在收集中哦！", Toast.LENGTH_SHORT).show();
                return true;
            } else if (preference == mWeibo ) {
//                openUrl(preference.getSummary().toString());
                return true;
            }else if ( preference == mJianshu){
//                joinQQGroup("4Nj4GRT9ODJrRbYilgmoZkbm9Od6feYG");
                IntentCache.setValue("http://119.91.106.94:8097/app/html/group0.html");
                startActivity(new Intent(context, WebActivity.class));
                return true;
            }else if (preference == mGithub){
//                openUrl("http://y.313u.cn/y/p.html");
//                R.color.grassGreen
                IntentCache.setValue("http://119.91.106.94:8097/y/p.html");
                startActivity(new Intent(context, WebActivity.class));
                return true;
            }
//            else if(preference==mGongzonghao){
//                fuzhi("灵悦分享");
//
//            }
            return false;
        }

        private void share() {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "灵悦");
            startActivity(Intent.createChooser(intent, getString(R.string.share)));
        }

        private void openUrl(String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
        public  void fuzhi(String str){
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", str);
// 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            Toast.makeText(context, "复制成功，微信公众号更多关于灵悦资讯等你来", Toast.LENGTH_SHORT).show();
        }
    }

}

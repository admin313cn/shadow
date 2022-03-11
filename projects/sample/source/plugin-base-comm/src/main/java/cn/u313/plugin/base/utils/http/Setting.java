package cn.u313.plugin.base.utils.http;//package cn.u313.soltvideo.comm.http;
//
//import android.app.Activity;
//import android.content.Context;
//
//import com.google.gson.Gson;
//
//import cn.u313.music.activity.DownloadAc;
//import cn.u313.music.model.DBMusicModeModel;
//import cn.u313.music.model.InitConfig;
//import cn.u313.music.utils.Base64Utils;
//import cn.u313.music.utils.FileUtils;
//
//public class Setting {
//    public static DBMusicModeModel MusicModel = null ;
//    public static  InitConfig InitConfig ;
//    public static  boolean Theme = false;
//    public static String k="";//歌曲混淆密钥 远程
//    public static DownloadAc downloadAc;
//    public static boolean dl=true;//是否用arix下载
//    public static Activity activity;//group
//    public static boolean isSign=false;
//    public static int themeId;
////    public static Map<Integer,String> themeNavIcons = new HashMap<Integer,String>(){
////        HashMap add(){
////            themeNavIcons.put(0,"");
////            return this;
////        }
////    }.add();
////    public static boolean do=true;
//    /**
//     * 获取配置
//     * @return
//     */
//    private static InitConfig initConfig(Context context){
//        String  txt="";
//        String filePath = FileUtils.getConfigDir() + "x_user.ly";
//        try {
//            txt =FileUtils.readFile(context,filePath);
//
//        } catch (Exception e) {
//            txt=FileUtils.readStringFromAssets(context);
//            e.printStackTrace();
//        }
////        txt= AESUtil.decrypt(txt);
//        try {
//            txt= new String(Base64Utils.base64Decode(txt));
//            return new Gson().fromJson(txt,InitConfig.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Setting.InitConfig=new InitConfig().def();
//    }
//    public static InitConfig getInitConfig(Context context){
//        if(Setting.InitConfig==null){
//            return initConfig(context);
//        }
//        return Setting.InitConfig;
//
//
//    }
//}

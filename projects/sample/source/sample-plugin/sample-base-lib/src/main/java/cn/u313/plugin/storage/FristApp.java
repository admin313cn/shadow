package cn.u313.plugin.storage;//package cn.u313.soltvideo.storage;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.Toast;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.Locale;
//import java.util.Set;
//
//import cn.u313.music.utils.ClassesReader;
//import cn.u313.music.utils.t;
//import cn.u313.music.utils.tool.Tool;
//import cn.u313.music.widget.UserUsrXieYi;
//import dalvik.system.DexFile;
//
//import static cn.u313.music.activity.SearchMusicActivity.TAG;
//
//public class FristApp {
//    public static void app(Context context, Appt app){
//        if(Tool.getNotice(context)){
//            app.noFrist();
//        }else app.frist();
//    }
//
//    public static void app0(Context context, Appt app) {
////        app.frist();
////        if(true)return;
//        if(Tool.getNotice0(context)){
//            app.noFrist();
//        }else app.frist();
//    }
//
//    /**
//     * 调用c 进行apk校验 防篡改
//     * @param context
//     * @param context
//     */
////    public static String getSignature(Context context)
////    {
////        try {
////            /** 通过包管理器获得指定包名包含签名的包信息 **/
////            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
////            /******* 通过返回的包信息获得签名数组 *******/
////            Signature[] signatures = packageInfo.signatures;
////            /******* 循环遍历签名数组拼接应用签名 *******/
////            return signatures[0].toCharsString();
////            /************** 得到应用签名 **************/
////        } catch (PackageManager.NameNotFoundException e) {
////            e.printStackTrace();
////        }
////        return null;
////    }
//    static boolean flag=false;
//    static byte[] bytes=null;
//    /**
//     * app签名校验
//     * @param context
//     * @param userUsrXieYi
//     */
//    public static void sign(Context context,UserUsrXieYi userUsrXieYi) {
//        try {
//            if(!getSignature(t.c(context))){
//                Set<DexFile> a = ClassesReader.applicationDexFile(context);
//                DexFile dexFile = a.iterator().next();
//                Enumeration<String> enumeration = dexFile.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
//                List<String> classes = new ArrayList<>();
//                while (enumeration.hasMoreElements()) {//遍历
//                    String className = enumeration.nextElement();
//                    if(className.toLowerCase(Locale.ROOT).contains("hook")){
//                        Toast.makeText(context, "发现一个注入 code [ hook ]!", Toast.LENGTH_SHORT).show();
//                        userUsrXieYi.c();
//                        return;
//                    }
////                    System.out.println(className);
//                }
//            }
//
//            if(getSignature(t.c(context))){
//                if(!flag&&hash>0x00000101)
//                    userUsrXieYi.c(1,2,3,bytes,(byte) 0);
//                else
//                    userUsrXieYi.d(3,3,3,bytes,(byte) 0);
//                userUsrXieYi.a(new UserUsrXieYi() {
//                    @Override
//                    public void s() {
//                        super.s();
//                    }
//
//                    @Override
//                    public void t() {
//                        super.t();
//                    }
//
//                    @Override
//                    public void u() {
//                        e();
//                    }
//
//                    @Override
//                    public void v() {
//                        g();
//                    }
//
//                    @Override
//                    public void w() {
//                        super.w();
//                    }
//
//                    @Override
//                    public void x() {
//                        super.x();
//                    }
//
//                    @Override
//                    public void y() {
//                        super.y();
//                    }
//
//                    @Override
//                    public void z() {
//                        super.z();
//                    }
//
//                    @Override
//                    public void a(int i, int j, int s, byte[] bytes) {
//                        super.a(i, j, s, bytes);
//                    }
//
//                    @Override
//                    public void b(int i, int j, int s, byte[] bytes, byte b) {
//                        u();
//                    }
//
//                    @Override
//                    public void c(int i, int j, int s, byte[] bytes, byte b) {
//                        super.c(i, j, s, bytes, b);
//                    }
//
//                    @Override
//                    public void d(int i, int j, int s, byte[] bytes, byte b) {
//                        super.d(i, j, s, bytes, b);
//                    }
//
//                    @Override
//                    public void e(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.e(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void f(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.f(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void g(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.g(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void h(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.h(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void i(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.i(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void j(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.j(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void k(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.k(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void l(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.l(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void m(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.m(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void n(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.n(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void o(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.o(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void p(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.p(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void q(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.q(i, j, s, bytes, b, bytess);
//                    }
//
//                    @Override
//                    public void r(int i, int j, int s, byte[] bytes, byte b, byte[][] bytess) {
//                        super.r(i, j, s, bytes, b, bytess);
//                    }
//                });
//
//                return ;
//            }
//            String a = t.a(context);
//            String b = t.b(null);
//            Log.e(TAG, "sign: "+a+">>"+b );
//            UserUsrXieYi userUsrXieYi1 = userUsrXieYi.v(new UserUsrXieYi() {
//                @Override
//                public void no() {
//                    super.no();
//                }
//
//                @Override
//                public void yes() {
//                    super.yes();
//                }
//
//                @Override
//                public boolean a(int i) {
//                    return a.equals(b);
//                }
//
//                @Override
//                public void a(UserUsrXieYi userUsrXieYi) {
//                    super.a(userUsrXieYi);
//                }
//
//                @Override
//                public UserUsrXieYi v(String a, String b) {
//                    return this;
//                }
//            }.v(a,b));
//            if(userUsrXieYi1.a(hash)){
//                userUsrXieYi.u();
//            }else userUsrXieYi.c();
//        } catch (Exception e) {
//            userUsrXieYi.p();
//            e.printStackTrace();
//        }
//
//
//
//    }
//
//    private static boolean getSignature(String s) {
//        if(s.getBytes(StandardCharsets.UTF_8).length>hash)
//            return true;
//        return false;
//    }
//    private static int hash= 0x00001101;
//    private static int ud5=  0x00011101;
//    private static int ud4=  0x00111101;
//    private static int ud3=  0x01111101;
//}

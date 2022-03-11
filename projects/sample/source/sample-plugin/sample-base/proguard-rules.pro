# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/cubershi/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keep class org.slf4j.**{*;}
-dontwarn org.slf4j.impl.**

-keep class com.tencent.shadow.dynamic.host.**{*;}
-keep class com.tencent.shadow.core.common.**{*;}
-keep class com.tencent.shadow.core.runtime.container.**{*;}
-obfuscationdictionary dic.txt
-classobfuscationdictionary dic.txt
-packageobfuscationdictionary dic.txt
# okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.** { *; }

-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn org.blinkenlights.jid3.**

-keep class android.support.** { *; }
-keep public class **.*model*.** {*;}

#-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment


# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


# greenDAO
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties
# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use Rx:
-dontwarn rx.**
# greenDAO end

# RxBus
-keep class com.hwangjr.rxbus.** { *; }
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.hwangjr.rxbus.annotation.Subscribe public *;
    @com.hwangjr.rxbus.annotation.Produce public *;
}
# RxBus end


#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# tinker混淆规则
-dontwarn com.tencent.tinker.**
-keep class com.tencent.tinker.** { *; }


# 在prguard-rules.pro文件中写的，其实就是混淆规则，规定哪些东西不需要混淆。
#自己编写的代码中大致就是一些重要的类需要混淆，而混淆的本质就是精简类名，用简单的a,b,c等单词来代替之前写的如DataUtil等易懂的类名。
#所以，理解了这点，也就好理解这个混淆文件该怎么写了，大致思路就是：
#不混淆第三方库，不混淆系统组件，一般也可以不混淆Bean等模型类，因为这些对别人都是没用的，毕竟都是开源的。。。

#混淆配置设定
-optimizationpasses 4 #指定代码压缩级别
-dontusemixedcaseclassnames #混淆时不会产生形形色色的类名
-dontskipnonpubliclibraryclasses #指定不忽略非公共类库
-dontpreverify #不预校验，如果需要预校验，是-dontoptimize
-ignorewarnings #屏蔽警告
-verbose #混淆时记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/* #优化

#-不需要混淆第三方类库
-dontwarn android.support.v4.** #去掉警告
-keep class android.support.v4.** { *; } #过滤android.support.v4
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-keep class org.apache.**{*;} #过滤commons-httpclient-3.1.jar
-keep class com.fasterxml.jackson.**{*;} #过滤jackson-core-2.1.4.jar等
-dontwarn com.lidroid.xutils.** #去掉警告
-keep class com.lidroid.xutils.**{*;} #过滤xUtils-2.6.14.jar
-keep class * extends java.lang.annotation.Annotation{*;} #这是xUtils文档中提到的过滤掉注解
-dontwarn com.baidu.** #去掉警告
-dontwarn com.baidu.mapapi.**
-keep class com.baidu.** {*;} #过滤BaiduLBS_Android.jar
-keep class vi.com.gdi.bgl.android.**{*;}
-keep class com.baidu.platform.**{*;}
-keep class com.baidu.location.**{*;}
-keep class com.baidu.vi.**{*;}
# 去掉与 MPAndroidChart jar包相关的
-dontnote com.github.mikephil.charting.**
-keep class com.github.mikephil.charting.** {*;}
# 去掉与 leakcanary jar包相关的
-dontnote com.squareup.leakcanary.**
-keep class com.squareup.leakcanary.** {*;}
# 去掉与 Ksoap2 jar包相关的
-dontnote org.ksoap2.**
-dontnote org.kobjects.**
-dontnote org.kxml2.**
-dontnote org.xmlpull.v1.**
-keep class org.kobjects.** {*;}
-keep class org.ksoap2.** {*;}
-keep class org.kxml2.** {*;}
-keep class org.xmlpull.v1.** {*;}
# 去掉与 SlideAndDragListView jar包相关的
-dontnote com.yydcdut.sdlv.**
-keep class com.yydcdut.sdlv.** {*;}
# 去掉与 pulltorefresh jar包相关的
-dontnote com.handmark.pulltorefresh.**
-keep class com.handmark.pulltorefresh.** {*;}
# 去掉与 de.greenrobot.dao jar包相关的
-dontnote de.greenrobot.dao.**
-keep class de.greenrobot.dao.** {*;}
# 去掉与 de.greenrobot.daogenerator jar包相关的
-dontnote de.greenrobot.daogenerator.**
-keep class de.greenrobot.daogenerator.** {*;}

# 去掉与 gson jar包相关的
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}
#这句非常重要，主要是滤掉 com.demo.demo.bean包下的所有.class文件不进行混淆编译,com.demo.demo是你的包名
-keep class cn.u313.plugin.model.** {*;}
-keep class com.tencent.shadow.** {*;}

# 去掉与 butterknife jar包相关的
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keep class **$$ViewInjector {*;} #就是这里没有添加，导致我的整个程序出错（因为我在程序中使用的是InjectView而不是BindView）
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-dontwarn com.arialyy.aria.**
-keep class com.arialyy.aria.**{*;}
-keep class **$$DownloadListenerProxy{ *; }
-keep class **$$UploadListenerProxy{ *; }
-keep class **$$DownloadGroupListenerProxy{ *; }
-keepclasseswithmembernames class * {
    @Download.* <methods>;
    @Upload.* <methods>;
    @DownloadGroup.* <methods>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#不需要混淆系统组件等
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService


-keep class com.finogeeks.** {*;}

#保护指定的类和类的成员，但条件是所有指定的类和类成员是要存在
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.support.v7.app.AppCompatActivity {
public void *(android.view.View);
}
#
##-自己编写的类的操作
#-keep class cn.u313.music.entity.**{*;} #过滤掉自己编写的实体类                               #过滤掉自己编写的实体类
#
## 过滤R文件的混淆：
#-keep class **.R$* {*;}
-keep public class cn.jzvd.JZMediaSystem {*; }
-keep public class cn.jzvd.demo.CustomMedia.CustomMedia {*; }
-keep public class cn.jzvd.demo.CustomMedia.JZMediaIjk {*; }
-keep public class cn.jzvd.demo.CustomMedia.JZMediaSystemAssertFolder {*; }

-keep class tv.danmaku.ijk.media.player.** {*; }
-dontwarn tv.danmaku.ijk.media.player.*
-keep interface tv.danmaku.ijk.media.player.** { *; }
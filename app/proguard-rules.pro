# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

 #指定代码的压缩级别
-optimizationpasses 5
 #包明不混合大小写
-dontusemixedcaseclassnames
 #不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
 #优化  不优化输入的类文件
-dontoptimize
#预校验
-dontpreverify
 #混淆时记录日志
-verbose
 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
 #保护注解
-keepattributes *Annotation*
 #忽略所有警告
-ignorewarnings

-keepclassmembers class **.R$* {
    public static <fields>;
}

 # 保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep class androidx.** {*;}
-keep public class * implements androidx.versionedparcelable.VersionedParcelable
 #如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment
 #忽略警告
#-ignorewarning

 #保持自定义View的构造方法和set方法
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
 #保持自定义控件类构造方法不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
 #保持自定义控件类构造方法不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
 #保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
 #保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
 #保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable
 #保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
 # 保持枚举 enum 类不被混淆 如果混淆报错，建议直接使用上面的 -keepclassmembers class * implements java.io.Serializable即可
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}

 # xml中对应的onClick方法不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}
 #不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}

#####################记录生成的日志数据,gradle build时在本项目根目录输出################
#apk 包内所有 class 的内部结构
#-dump class_files.txt

#未混淆的类和成员
-printseeds seeds.txt

#列出从 apk 中删除的代码
-printusage unused.txt

#混淆前后的映射
-printmapping mapping.txt
#####################记录生成的日志数据，gradle build时 在本项目根目录输出-end################
#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
#okgo
-dontwarn com.lzy.okgo.**
-keep class com.lzy.okgo.**{*;}
#android-gif-drawable
#-dontwarn pl.droidsonroids.gif.**
#-keep pl.droidsonroids.gif.**{*;}
#Glide
-dontwarn com.bumptech.glide.module.GlideModule.**
-dontwarn com.bumptech.glide.load.resource.bitmap.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


#3D 地图 V5.0.0之后：
-dontwarn com.amap.api.maps.**
-dontwarn com.autonavi.**
-dontwarn com.amap.api.trace.**
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.**{*;}
-keep   class com.amap.api.trace.**{*;}
#定位
-dontwarn com.amap.api.location.**
-dontwarn com.amap.api.fence.**
-dontwarn com.autonavi.aps.amapapi.model.**
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

#搜索
-dontwarn com.amap.api.services.**
-keep   class com.amap.api.services.**{*;}
#导航
-dontwarn com.amap.api.navi.**
-dontwarn com.autonavi.**
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}
#金山推流
-dontwarn com.ksyun.**
-dontwarn com.ksy.statlibrary.**
-keep class com.ksyun.** {*;}
-keep class com.ksy.statlibrary.** {*;}

#图表混淆
-dontwarn com.github.mikephil.charting.**
-keep class com.github.mikephil.charting.** { *; }

# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}
-keep interface com.google.gson.** { *;}
-keep class com.xiaozhen.bean.** {*;}


# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
-keepclassmembers class * implements java.io.Serializable {
static final long serialVersionUID;
private static final java.io.ObjectStreamField[] serialPersistentFields;
private void writeObject(java.io.ObjectOutputStream);
private void readObject(java.io.ObjectInputStream);
java.lang.Object writeReplace();
java.lang.Object readResolve();
}
-keep public class * implements java.io.Serializable {*;}
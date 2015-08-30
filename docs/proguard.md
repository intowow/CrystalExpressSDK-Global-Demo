<h3 id='proguard' style='color:red'>Proguard</h2>

- if you use proguard, please modify your proguard setting file as follows
```
##################################
#	intowow sdk
#
-keep class com.intowow.sdk.* { *; }
##################################
#	android-support-v4
#
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }
##################################
```

﻿﻿<h3 id='androidmanifest' style='color:red'>AndroidManifest.xml</h3>

[XML Sample][TAG-AndroidManifest]

<h4 id='Permission' style='color:green'>Permission</h4>

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

---------------------------------------

<h4 id='Activity' style='color:green'>Activity</h4>

```xml
<activity
	android:name="com.intowow.sdk.SplashAdActivity"
	android:configChanges="orientation|screenSize"
	android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen"
	android:launchMode="singleTask"
	android:screenOrientation="portrait" >
</activity>

<activity
    android:name="com.intowow.sdk.WebViewActivity"
    android:configChanges="orientation|screenSize"
    android:theme="@android:style/Theme.NoTitleBar.Fullscreen" >
</activity>
```

<span style="color:red">Note:</span>
<br/>
<span style="color:red">if your App's build target is less than 13，then you should remove the `screenSize` property in the `android:configChanges`</span>

---------------------------------------

<h4 id='Receiver' style='color:green'>Receiver</h4>

```xml
<receiver android:name="com.intowow.sdk.ScheduleReceiver">
	<intent-filter >
	<action android:name="com.intowow.sdk.prefetch"/>
	</intent-filter>
</receiver>
```

---------------------------------------

<h4 id='meta-data' style='color:green'>Meta-Data</h4>

```xml
<meta-data android:name="CRYSTAL_ID" android:value="{the crystal id provided by Intowow}" />
```

---------------------------------------

<h4 id='meta-data' style='color:green'>Preview Mode</h4>
- [Preview Mode](../preview)

---------------------------------------

<br/>
<br/>

[TAG-AndroidManifest]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/AndroidManifest.xml "AndroidManifest.xml"

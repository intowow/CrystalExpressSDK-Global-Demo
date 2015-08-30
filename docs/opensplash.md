<h3 id='opensplash' style='color:red'>Open Splash</h3>

- Open splash ad means that one splash ad when you open the App
- trigger points are:
	- start the launch activity(`launch flow`)
	- click `HOME` button and reenter App (`enter foreground`)

<img style="display:block; margin:auto;" src="https://s3.cn-north-1.amazonaws.com.cn/intowow-common/preview/img/splash2-demo.png" alt="splash demo" width="250">

<a target="_blank" href="http://s3.cn-north-1.amazonaws.com.cn/intowow-common/preview/globe_slideup.html">Preview link</a>

---------------------------------------

<h4 id='opensplash-1' style='color:green'>Integration Steps</h4>

<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span style='font-weight: bold;color:red'>
if you can not see any ad after integration, you can reference to <a target="_blank" href="../faq">Trouble shooting</a>
</span>
<br/>


<h3 id='opensplash-launch' style='color:blue'>1. modify launch flow</h3>

<p/>
[Sample Code link][OpenSplash]
<p/>

- let the launch Activity extend the [BaseActivity](./activity_setting)<p/>
<p/>

- copy the [slide_in_from_bottom.xml][slide_in_from_bottom] and [no_animation.xml][no_animation] from the demo App into your project path `res/anim/`

<p/>

<p/>

- add `android:configChanges="orientation|screenSize"` property inside of the activity in the AndroidManifest.xml

<p/>

<span style='font-weight: bold;color:red'>Note:</span>
<br/>
<span style='font-weight: bold;color:red'>
if you don't add this property in the activity, then your activiy will be recreated when you getting a landscape splash ad
</span>

- declare variables([Sample Code link][OpenSplash-mAd])
<codetag tag="OpenSplash-mAd"/>
```java
private SplashAD mAd = null;
```
<p/>

- request a splash ad ([Sample Code link][OpenSplash-request])

<codetag tag="OpenSplash-request"/>
```java
//	we can request the splash ad 
//	after the LOGO shows for some time
//
mAd = I2WAPI.requesSingleOfferAD(CEOpenSplashActivity.this, "OPEN_SPLASH");
```
<p/>

<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span style='font-weight: bold;color:red'>
1.if you request ad in the launch flow, please add it after that user see the App's logo for one second for getting the better user experience
</span>

<br/><br/>
<a target="_blank" href="http://s3.cn-north-1.amazonaws.com.cn/intowow-common/preview/globe_slideup.html">Preview link</a>

- set the listener callback `onLoaded()`, `onLoadFailed()` and `onClosed()`
<br/>[Sample Code link][OpenSplash-setListener]

<br/>
<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span style='font-weight: bold;color:red'>
the listener's callback is `Blocking Calls` after using `I2WAPI.requesSingleOfferAD()`
</span>
 
<codetag tag="OpenSplash-setListener" id="OpenSplash-callback"/>
```java
if (mAd != null) {

	//	implement onLoaded, onLoadFailed and 
	//	onClosed callback
	//
	mAd.setListener(new SplashAdListener() {

		@Override
		public void onLoaded() {
			//	this callback is called 
			//	when the splash ad is ready to show
			//
			//	show splash ad here
			//
			mAd.show(R.anim.slide_in_from_bottom, 
					R.anim.no_animation);
		}

		@Override
		public void onLoadFailed() {
			//	this callback is called
			//	when this splash ad load fail
			//
			startNextActivity();
		}

		@Override
		public void onClosed() {
			//	this callback is called when:
			//	1.user click the close button
			//	2.user press the onBackpress button
			//	3.dismiss_time setting from the server
			//
			startNextActivity();
		}
	});
} else {
	//	the ad is not ready now
	//	start the next activity directly
	//
	startNextActivity();
}
```
<p/>

---------------------------------------

<h3 id='opensplash-enterforeground' style='color:blue'>2. click `HOME` button and reenter App (enter foreground)</h3>

<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span style='font-weight: bold;color:red'>
this function is only support Android 4.0 or above.
</span>
<br/>
<span style='font-weight: bold;color:red'>
if your App has its own Application class, you can copy BaseApplication's logic to your Application directly
</span>
<br/>

- add `BaseApplication.java`
	- [Download](https://s3.cn-north-1.amazonaws.com.cn/intowow-sdk/android/sample/BaseApplication.java)

- modify `application` tag in the `AndroidManifest.xml`

```xml
<application
    android:name="{your BaseApplication package}.BaseApplication"
    android:XXX
    android:XXX
    XXX >
```

- modify all of your `activity` tags in the `AndroidManifest.xml` to handle the showing of the landscape splash ad
	- add `android:configChanges="orientation|screenSize"` property

- modify [BaseActivity.java](./activity_setting), add `getApplicationContext()` in the `onCreate()` for starting the `BaseApplication`
<br/>[Sample Code][OpenSplash-startapplication]

<codetag tag="OpenSplash-startapplication" id="OpenSplash-startapplication"/>
```java
//	you can launch the BaseApplication.java
//	for requesting the enter foreground splash ad
//
getApplicationContext();
```
<p/>


- in the `BaseApplication.java`, modify the `FILTER_ACTIVITY_NAMES` string array, this array indicates which activity will not show the splash ad.
	- please add your launch activity here, since it has already requested splash ad itself, or add any other activity you do not want to show 

<br/>[Sample Code][OpenSplash-FILTER_ACTIVITY_NAMES]

<codetag tag="OpenSplash-FILTER_ACTIVITY_NAMES" id="OpenSplash-FILTER_ACTIVITY_NAMES"/>
```java
//	TODO
//	you can modify this String array.
//	these classes added here will not show 
//	the enter foreground splash ad
//
private final static String[] FILTER_ACTIVITY_NAMES = new String[] {

	//	================================================
	//	replace these classes to your launcher activity
	//	or any other class which you don't want to show the splash ad
	//
	MainActivity.class.getName(),
	CEOpenSplashActivity.class.getName(),
	OpenSplashActivity.class.getName(),
	MultipleDeferAdapterActivity.class.getName(),
	SingleDeferAdapterActivity.class.getName(),
	MultipleStreamHelperActivity.class.getName(),
	SingleStreamHelperActivity.class.getName(),
	ContentActivity.class.getName(),
	FlipActivity.class.getName(),

	//=====  do not remove SDK's activity
	//
	SplashAdActivity.class.getName(),// this is SDK's activity, don't remove it
	WebViewActivity.class.getName()// this is SDK's activity, don't remove it
}; 
```
<p/>

<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span style='font-weight: bold;color:red'>
do not remove SplashAdActivity and WebViewActivity in the FILTER_ACTIVITY_NAMES
</span>

---------------------------------------

- please go through the <a target="_blank" href="../checkpoint">Checkpoing</a> after finish integration

<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span style='font-weight: bold;color:red'>
1.the overridePendingTransition is only support portrait ad
</span>
<br/>
<span style='font-weight: bold;color:red'>
2.you can customize the overridePendingTransition effect by yourself
</span>
<br/>
<span style='font-weight: bold;color:red'>
3.the demo App supplies the bounce effect that you can reference too
</span>
<br/>
<span style='font-weight: bold;color:red'>
    res/anim/damping_in.xml
</span>
<br/>
<span style='font-weight: bold;color:red'>
    res/anim/damping_out.xml
</span>
<p/>

[OpenSplash-startapplication]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/BaseActivity.java#L30 "BaseActivity.java" 
[OpenSplash-FILTER_ACTIVITY_NAMES]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/BaseApplication.java#L51 "BaseApplication.java" 
[OpenSplash-mAd]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEOpenSplashActivity.java#L25 "CEOpenSplashActivity.java" 
[OpenSplash]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEOpenSplashActivity.java#L14 "CEOpenSplashActivity.java" 
[slide_in_from_bottom]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/blob/master/res/anim/slide_in_from_bottom.xml
[no_animation]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/blob/master/res/anim/no_animation.xml
[OpenSplash-onConfigurationChanged]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEOpenSplashActivity.java#L29 "CEOpenSplashActivity.java" 
[OpenSplash-request]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEOpenSplashActivity.java#L56 "CEOpenSplashActivity.java" 
[OpenSplash-setListener]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEOpenSplashActivity.java#L63 "CEOpenSplashActivity.java" 

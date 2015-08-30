<h3 id='interstitialsplash' style='color:red'>Interstitial Splash</h3>

- Interstitial splash ad means that one ad shows in the stream page while you cback from the article page

<p/>
[Sample Code link][Interstitial]
<p/>

<h4 id='interstitialsplash-1' style='color:green'>Integration Steps</h4>

<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span style='font-weight: bold;color:red'>
if you can not see any ad after integration, please reference to <a target="_blank" href="../faq">Trouble shooting</a>
</span>
<br/>

- let the Activity extend the [BaseActivity](./activity_setting)<p/>
<p/>

- copy the [slide_in_from_bottom.xml][slide_in_from_bottom] and [no_animation.xml][no_animation] from the demo App into your project path `res/anim/`

<p/>

- add `android:configChanges="orientation|screenSize"` property inside of the activity in the AndroidManifest.xml

<p/>

<span style='font-weight: bold;color:red'>Note:</span>
<br/>
<span style='font-weight: bold;color:red'>
if you don't add this property in the activity, then your activiy will be recreated when you getting a landscape splash ad
</span>

- declare variables ([Sample Code link][Interstitial-init])
<codetag tag="Interstitial-init"/>
```java
private final static String mInterstitialPlacement = Config.INTERSTITIAL_PLACEMENT;
private SplashAD mInterstitialSplashAd = null;
```
<p/>

<span style='font-weight: bold;color:red'>define the placement first, and submit it to Intowow<span/>

- request a splash ad([Sample Code link][Interstitial-request])
<codetag tag="Interstitial-request"/>
```java
mInterstitialSplashAd = I2WAPI.requesSplashAD(CEStreamActivity.this, mInterstitialPlacement);
```
<p/>

- set listener([Sample Code link][Interstitial-setListener])
- this callback is `Non-Blocking Calls` after you use the `I2WAPI.requesSplashAD()`

<codetag tag="Interstitial-setListener"/>
```java
if (mInterstitialSplashAd != null) {
	//	this is a Non-Blocking calls
	//
	mInterstitialSplashAd.setListener(new SplashAdListener() {

		@Override
		public void onLoaded() {
			if(mInterstitialSplashAd != null) {
				mInterstitialSplashAd.show(R.anim.slide_in_from_bottom, R.anim.no_animation);
			}
		}

		@Override
		public void onLoadFailed() {
			if(mInterstitialSplashAd != null) {
				mInterstitialSplashAd.release();
			}
		}

		@Override
		public void onClosed() {
			//	be sure to release the splash ad here
			//
			if(mInterstitialSplashAd != null) {
				mInterstitialSplashAd.release();
			}
		}
	});
}
```
<p/>

- release the ad in the `onDestroy()` ([Sample Code link][Interstitial-release])
<codetag tag="Interstitial-release"/>
```java
if (mInterstitialSplashAd != null) {
	mInterstitialSplashAd.release();
	mInterstitialSplashAd = null;
}
```
<p/>

- please go through the <a target="_blank" href="../checkpoint">Checkpoing</a> after finish integration

[Interstitial-release]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L367 "CEStreamActivity.java" 
[OpenSplash-request]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEOpenSplashActivity.java#L56 "CEOpenSplashActivity.java" 
[Interstitial]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L337 "CEStreamActivity.java" 
[Interstitial-init]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L84 "CEStreamActivity.java" 
[Interstitial-request]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L151 "CEStreamActivity.java" 
[Interstitial-setListener]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L155 "CEStreamActivity.java" 
[slide_in_from_bottom]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/blob/master/res/anim/slide_in_from_bottom.xml
[no_animation]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/blob/master/res/anim/no_animation.xml

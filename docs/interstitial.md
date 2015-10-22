<h3 id='interstitialsplash' style='color:red'>Interstitial Splash</h3>

- Interstitial splash ad means that one ad shows in the stream page while you cback from the article page

---------------------------------------

<h4 id='interstitialsplash-1' style='color:green'>Integration Steps</h4>

<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span style='font-weight: bold;color:red'>
before starting your integration, please remove the Demo App first.
</span>
<br/>
<span style='font-weight: bold;color:red'>
if you can not see any ad after integration, you can refer to <a target="_blank" href="../faq">Trouble shooting</a>
</span>
<br/>

<p/>
[Sample Code link][Interstitial]
<p/>

- let the Activity extend the [BaseActivity](./activity_setting), the BaseActivity includes logic that avoiding your APP request splash ad twice when screen are rotated in showing the landscape splash ad<p/>
<p/>

- copy the [slide_in_from_bottom.xml][slide_in_from_bottom] and [no_animation.xml][no_animation] from the demo App into your project path `res/anim/`

<p/>

- declare variables ([Sample Code link][Interstitial-init])
<codetag tag="Interstitial-init"/>
```java
private final static String mInterstitialPlacement = Config.INTERSTITIAL_PLACEMENT;
```
<p/>

<span style='font-weight: bold;color:red'>define the placement first, and submit it to Intowow<span/>

- request a splash ad([Sample Code link][Interstitial-request])
<codetag tag="Interstitial-request"/>
```java
//	check it for landscape ad case
//
if(hasRequestedSplashAd()) {
	return;
}
mSplashAd = I2WAPI.requesSingleOfferAD(CEStreamActivity.this, mInterstitialPlacement);
```
<p/>

- set listener([Sample Code link][Interstitial-setListener])
- this callback is `Blocking Calls` after you use the `I2WAPI.requesSingleOfferAD()`

<codetag tag="Interstitial-setListener"/>
```java
if (mSplashAd != null) {
	mSplashAd.setListener(new SplashAdListener() {

		@Override
		public void onLoaded() {
			mSplashAd.show(R.anim.slide_in_from_bottom, 
					R.anim.no_animation);
		}

		@Override
		public void onLoadFailed() {
			onSplashAdFinish();
		}

		@Override
		public void onClosed() {
			onSplashAdFinish();
		}
	});
}
```
<p/>

- release the ad in the `onDestroy()` ([Sample Code link][Interstitial-release])
<codetag tag="Interstitial-release"/>
```java
releaseSplashAd();
```
<p/>

- please go through the <a target="_blank" href="../checkpoint">Checkpoint</a> after finish integration

[Interstitial-release]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L360 "CEStreamActivity.java" 
[OpenSplash-request]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEOpenSplashActivity.java#L35 "CEOpenSplashActivity.java" 
[Interstitial]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L330 "CEStreamActivity.java" 
[Interstitial-init]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L82 "CEStreamActivity.java" 
[Interstitial-request]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L148 "CEStreamActivity.java" 
[Interstitial-setListener]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L157 "CEStreamActivity.java" 
[slide_in_from_bottom]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/blob/master/res/anim/slide_in_from_bottom.xml
[no_animation]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/blob/master/res/anim/no_animation.xml

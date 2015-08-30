<h3 id='content' style='color:red'>In-Read</h3>

- In-Read ad means that one ad shows in some article pages.
- if your article page is implemented by `ScrollView`, please use the [CrystalExpressScrollView][Content-scroll] class which is provided by the demo project to control the ad status  correctly.
- we can add the `ScrollViewListener` to the [CrystalExpressScrollView][Content-scroll] for listening the scroll event. when you scroll the page, it will trigger the `onScrollChanged` callback, so we can know whether the ad is shown in the screen or not
- if your article page is implemeted by `WebView`, then you can try to adjust your xml layout like this:

```xml
	<CrystalExpressScrollView>
		<ViewGroup>
			<WebView/>
			<ContentAd/>
		<ViewGroup/>
	<CrystalExpressScrollView/>
```

- you can refer to [activity_content.xml][activity_content.xml]

---------------------------------------

<h4 id='content-1' style='color:red'>Integration Steps</h4>

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

<p/>[Sample Code link][Content-activity]<p/>

- let the Activity extend the [BaseActivity](./activity_setting)<p/>
<p/>

- add [CrystalExpressScrollView][Content-scroll] and [ContentHelper.java][Content-helper] from the demo project

- declare variables in the `Activity` ([Sample Code link][Content-init])

<codetag tag="Content-init"/>
```java
private final static String mPlacement = Config.CONTENT_PLACEMENT;
private ContentHelper mContentHelper = null;

```
<p/>
	
<span style='font-weight: bold;color:red'>define the `placement` first, and submit it to Intowow<span/>

- initial the `mContentHelper`([Sample Code link][Content-inithelper])

<codetag tag="Content-inithelper"/>
```java
// content ad view
//
final RelativeLayout contentAdLayout = (RelativeLayout) findViewById(R.id.contentad);
final CrystalExpressScrollView sv = (CrystalExpressScrollView) findViewById(R.id.scrollview);

mContentHelper = new ContentHelper(this, mPlacement, sv,
		contentAdLayout);
mContentHelper.setActive();
mContentHelper.onPageSelected(0);
```
<p/>

- initial the `ScrollView`([Sample Code link][Content-initscroll])
<codetag tag="Content-initscroll"/>
```java
// callback
//
sv.setScrollViewListener(new ScrollViewListener() {
	public void onScrollChanged(CrystalExpressScrollView scrollView,
			int x, int y, int oldX, int oldY) {
	}

	public void onScrollViewIdle() {
		if (mContentHelper != null) {
			mContentHelper.checkContentAD();
		}
	}
});
```
<p/>

- if you want to load the ad in the onCreate(), you can add this code below
- if your ad is added below the `WebView`, then you can consider loading ad after `WebViewClient` 's `onPageFinished()`

<p/>[Sample Code link][Content-load]<p/>
<codetag tag="Content-load"/>
```java
if (mContentHelper != null) {
	mContentHelper.loadContentAd();
}
```
<p/>

- you can resize the ad's width by modifying the [ContentHelper.java][Content-helper]

<p/>[Sample Code link][Content-requestAD]<p/>
<codetag tag="Content-requestAD"/>
```java
public View requestAD(int position) {
	if (mHelper != null) {

		//	you can resize the ad width by
		//
		//	mHelper.requestAD(position, intWidthValue);

		return mHelper.requestAD(position);
	}

	return null;
}
```
<p/>

- or change the ad's background too
<p/>[Sample Code link][Content-background]<p/>
<codetag tag="Content-background"/>
```java
private void addAd(View contentAd) {
	if(contentAd!=null){

		//	you can set your background here
		//
		contentAd.setBackgroundColor(Color.WHITE);

		mContentAdLayout.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;
		mContentAdLayout.removeAllViews();
		mContentAdLayout.addView(contentAd);
	}
}
```
<p/>

- Activity lifecycle

<p/>[Sample Code link][Content-life]<p/>
<codetag tag="Content-life" id="content_life"/>
```java
@Override
public void onResume() {
	super.onResume();

	if (mContentHelper != null) {
		mContentHelper.start();
	}
}

@Override
public void onPause() {
	super.onPause();

	if (mContentHelper != null) {
		mContentHelper.stop();
	}
}

@Override
public void onDestroy() {
	super.onDestroy();

	if (mContentHelper != null) {
		mContentHelper.destroy();
		mContentHelper = null;
	}

}
```
<p/>

- please go through the <a target="_blank" href="../checkpoint">Checkpoing</a> after finish integration
 

[activity_content.xml]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/res/layout/activity_content.xml "activity_content.xml"
[Content-helper]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/content/ContentHelper.java#L10 "ContentHelper.java" 
[Content-init]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/content/ContentActivity.java#L27 "ContentActivity.java" 
[Content-scroll]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/content/CrystalExpressScrollView.java#L8 "CrystalExpressScrollView.java" 
[Content-inithelper]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/content/ContentActivity.java#L131 "ContentActivity.java" 
[Content-initscroll]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/content/ContentActivity.java#L143 "ContentActivity.java" 
[Content-load]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/content/ContentActivity.java#L159 "ContentActivity.java" 
[Content-activity]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/content/ContentActivity.java#L25 "ContentActivity.java" 
[Content-requestAD]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/content/ContentHelper.java#L66 "ContentHelper.java" 
[Content-background]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/content/ContentHelper.java#L124 "ContentHelper.java" 
[Content-life]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/content/ContentActivity.java#L166 "ContentActivity.java" 

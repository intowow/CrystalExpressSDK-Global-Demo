<h2 id='activity' style='color:red'>Activity</h2>

<h4 style='color:green'>Initialize SDK</h4>
- let all activities which you have defined in the AndroidManifest.xml extend the BaseActivity.java
- the [BaseActivity][BaseActivity] implemetnts all about the SDK initial functions

<br/>
<a target="_blank" href="https://s3.cn-north-1.amazonaws.com.cn/intowow-sdk/android/sample/BaseActivity.zip">Download Link</a>

```java
	change
	
	public class YourChildActivity extends Activity{

	to

	public class YourChildActivity extends BaseActivity{
	
```

<p/>

<div id="testmode"></div>
- if you want to use the ``Test Ad``, then you can modify the [BaseActivity][BaseActivity] directly

```java
I2WAPI.init(this);

to

I2WAPI.init(this, true);
```
 
<p/>

<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>

<span style='font-weight: bold;color:red'>
1. when you are integrating with the SDK, please use the Test Mode ad
</span>
<br/>

<span style='font-weight: bold;color:red'>
2.if you want to return to the original mode, please reinstall your App
</span>
<br/>

<p/>

<br/>

<p/>

[BaseActivity]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/BaseActivity.java#L12 "BaseActivity.java" 

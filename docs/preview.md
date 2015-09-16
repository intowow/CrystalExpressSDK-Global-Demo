<h3 id='preview' style='color:red'>Preview Mode</h3>

- the preview mode allows the sales to present the CrystalExpress ad for advertiser by your App at first hand
- it can be launched by scaning the QRCode

[Setup Steps](./preview/#preview-setting)
<br/>
[Test Steps](./preview/#preview-test)

---------------------------------------

<h4 id='preview-setting' style='color:green'>Setup Steps</h4>

- add code in your launcher activity of the [AndroidManifest.xml][TAG-AndroidManifest]

```xml
<intent-filter>
	<actionandroid:name="android.intent.action.VIEW"/>
	<category android:name="android.intent.category.DEFAULT"/>
	<category android:name="android.intent.category.BROWSABLE"/>
	<data android:scheme="{YOUR_APP_URL_SCHEME}"android:host="crystalexpress"/>
	<data android:scheme="{YOUR_APP_URL_SCHEME}"android:host="activate"android:pathPattern=".*"/>
</intent-filter>
```

- lets your launcher activity extend the[BaseActivity][BaseActivity]
- if you scan the QRCode and launch the activity, SDK will process the activity's bundle to enter the preview mode


<span style='font-weight: bold;color:red'>Note:<span/>
<br/>
<span style='font-weight: bold;color:red'>you should define your App's url scheme first, and submit it to Intowow<span/>

- QRCode format:
```
{YOUR_APP_URL_SCHEME}://crystalexpress?adid={AD ID}
```

---------------------------------------

<h4 id='preview-test' style='color:green'>Test Steps</h4>

1. verify your internet connection is working well
2. scan the QRCode
3. the QRCode App will launch the App if your configuration is currectlly
4. you will see the black screen first. after SDK finish loading, you will need to click the confirm button to close the App compulsive
5. open your App again. when SDK finish downloading the ad, you will see a toast
6. if you scan open splash, then press back button to leave the App, and reenter again
7. please do not kill the app process during the SDK downloading the ad
8. if you want to return to the original mode, swip you App directly (kill process)


[BaseActivity]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/BaseActivity.java#L13 "BaseActivity.java" 
[TAG-AndroidManifest]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/AndroidManifest.xml "AndroidManifest.xml"

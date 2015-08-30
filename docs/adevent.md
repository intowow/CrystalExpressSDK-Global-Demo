<h2 id='adcallback' style='color:red'>Ad Event Callback</h2>

- you can get the ad impression and click event by setting the callback listener
- if you use it, SDK will call callback event by background thread, so you should handle some `main-thread only `issues in the listener's callback when you handle it.

- for memory leak issue, SDK uses `WeakReference` for saving the listener, so you should mantain it's lifecycle by yourself, and to add it into SDK again if you have to

- API:

```java
I2WAPI.setADEventListener(final Context context, ADEventListener listener);
```

- Sample code

```java

// you should maintain this listener's lifecycle by yourself
//
private ADEventListener mADEventListener = null;

if(mADEventListener == null) {
	mADEventListener = new ADEventListener() {
		@Override
		public void onAdClick(final String adId, final String clickUrl) {
			//	get click event here.
			//	the clickUrl may be null
			//
		}
		
		@Override
		public void onAdImpression(final String adId) {
			//	get impression event here
			//
		}
	};
	
	I2WAPI.setADEventListener(Activity.this, mADEventListener);
}
```

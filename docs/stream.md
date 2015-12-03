<h3 id='stream_intro' style='color:red'>In-Stream</h3>

﻿<h4 id='stream_intro_1' style='color:green;margin-bottom:15px'>In-Stream ad introduction</h4>

- In-Stream ad means that one ad shows in the stream list (`ListView`)

<img style="display:block; margin:auto;" src="https://s3.cn-north-1.amazonaws.com.cn/intowow-common/preview/img/card.png" alt="stream demo" width="250" />

<a target="_blank" href="https://s3.cn-north-1.amazonaws.com.cn/intowow-common/preview/STREAM_VIDEO_CUSTOMCARD.html?video=http%3A%2F%2Fintowow-demo.oss-cn-beijing.aliyuncs.com%2Fpreview%2Fmaterial%2FStream-Video-CustomCard.mp4&customCard=http%3A%2F%2Fintowow-demo.oss-cn-beijing.aliyuncs.com%2Fpreview%2Fmaterial%2FStream-Video-CustomCard.jpg"  target="_blank">Preview link</a>

---------------------------------------

<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span style='font-weight: bold;color:red'>
before starting your integration, please remove the Demo App first.
</span>
<br/>
<span style='font-weight: bold;color:red'>
if you can not see any ad after integration, you can refere to <a target="_blank" href="../faq">Trouble shooting</a>
</span>
<br/>

﻿<h4 id='stream_import' style='color:green;margin-bottom:15px'>Integration Points</h4>

- let the Activity extend the [BaseActivity](./activity_setting)<p/>
<p/>

<font size=2 >Integration Steps:</font>

1. [Prepare Ad Placement](./stream/#stream_placement)
2. [Initial `StreamHelper` class](./stream/#stream_adapter)
3. [`ListView` add `onScrollListener()`](./stream/#stream_scroll_status)
4. [`ListView` process `onItemClickListener()`](./stream/#stream_itemclick)
5. [`StreamHelper` add `onADLoaded()` callback](./stream/#stream_adload)
6. [Process `Adapter.notifyDataSetChanged()`](./stream/#stream_notify)
7. [Process `Adapter.getItemViewType()`](./stream/#stream_itemview)
8. [Process `Adapter.getView()`](./stream/#stream_getview)
9. [`StreamHelper.setActive()`](./stream/#stream_active)
10. [Handle `Activity` lifecycle](./stream/#stream_activity)

---------------------------------------

﻿<h4 id='stream_placement' style='color:green;margin-bottom:15px'>Prepare Ad Placement</h4>

- you should define the [Ad Placement](./naming/#placement) first，every listview has it's own [Ad Placement](./naming/#placement)
- or you can configure them on the server for getting it dynamiclly

<p/>
[Sample Code link][Stream-init]
<p/>

<codetag tag="Stream-init"/>
```java
/**
 *	you can hardcode this placement value in the source code, 
 *	or replace it by calling your server API 	
**/
private final static String  mPlacement = Config.STREAM_PLACEMENT;
```
<p/>

---------------------------------------

﻿<h4 id='stream_adapter' style='color:green;margin-bottom:15px'>Initial StreamHelper class</h4>

- `StreamHelper` helps the App to control the ad status correctly, such as requesting , stopping, starting or releasing the ad etc.
- if your App has multiple `ListView` in one activity, then every listview should has its own StreamHelper for managing ads
- SDK supplies two choices for initializing the `StreamHelper`
	1. `DeferStreamAdapter` class (`we suggest you that to use it`)
		- it extends `BaseAdapter`
		- it initials the `StreamHelper` inside
		- it implements `OnScrollListener`
		- for integration easily
	2. `StreamHelper` class
		- if your App can not extend the `DeferStreamAdapter`, then use this one


<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span id='fix-init' style='font-weight: bold;color:red'>
if you want to let the stream ad be shown at the fixed position and you can refer to <a target="_blank" href="../stream#fix_stream">Stream ad at the fixed position</a>
</span>
<br/>


<p/>
[Initial Sample Code-StreamHelper][Stream-StreamHelper-init]
<p/>
[Initial Sample Code-DeferStreamHelper][Stream-defer-init]
<p/>

<codetag tag="Stream-defer-init"/>
```java
mAdapter = new ExtendDeferStreamAdapter(
		this, 
		mPlacement, 
		mItems);
```
<p/>

- Comparison Table
	
<table border="1" >
	<thead>
		<tr>
			<td align="center" style='color:green;'>Item</td>
			<td width='250px' align="center" style='color:green;word-break:break-all;'>DeferStreamAdapter</td>
			<td width='250px' align="center" style='color:green;word-break:break-all;'>StreamHelper</td>
		</tr>
	</thead>
	<tbody style='font-size:15px'>
		<tr>
			<td align="center">integration priority</td>
			<td width='250px' style='word-break:break-all;'>
				first
			</td>
			<td width='250px' style='word-break:break-all;'>
				only consider if your adapter can not extend the <span style='color:#E74C3C'>DeferStreamAdapter</span>
	  	</td>
		</tr>
		<tr>
			<td align="center">initialize</td>
			<td width='250px' style='word-break:break-all;'>
				just only let your adapter extend it
			</td>
			<td width='250px' style='word-break:break-all;'>
				you would need to declare a helper reference and pass it on to the adapter</span>
	  	</td>
		</tr>
		<tr>
			<td align="center">Ad Placement</td>
			<td width='250px' style='word-break:break-all;'>
				it is added in the adapter's constructor
			</td>
			<td width='250px' style='word-break:break-all;'>
				it is added in the helper's constructor
	  	</td>
		</tr>
		<tr>
			<td align="center">onScrollListener()</td>
			<td width='250px' style='word-break:break-all;'>
				<span style='color:#E74C3C'>listview.setOnScrollListener(DeferStreamAdapter)</span>
			</td>
			<td width='250px' style='word-break:break-all;'>
				<span style='color:#E74C3C'>listview.setOnScrollListener(StreamHelper)</span>
	  	</td>
		</tr>
		<tr>
			<td align="center">onADLoaded()</td>
			<td width='250px' style='word-break:break-all;'>
				directly implement it in your adapter which extends the <span style='color:#E74C3C'>DeferStreamAdapter</span>
	  	</td>
			<td width='250px' style='word-break:break-all;'>
				add it after you new an instance of the StreamHelper
	  	</td>
		</tr>
		
		<tr>
			<td align="center">getItemViewType()</td>
			<td width='250px' style='word-break:break-all;'>
				if your adapter dose not implement this method, skip the step. if it dose, then it needs to check whether the position is an ad or not, and return <span style='color:#E74C3C'>super.getItemViewType(position)</span>
	  	</td>
			<td width='250px' style='word-break:break-all;'>
				need to check whether the position is ad or not, if the position is ad, then return  <span style='color:#E74C3C'>mStreamHelper.getItemViewType(position)</span>
	  	</td>
		</tr>
		
		<tr>
			<td align="center">getView</td>
			<td width='250px' style='word-break:break-all;'>
				call the getAd() for requesting a stream ad in the getView()
			</td>
			<td width='250px' style='word-break:break-all;'>
				call helper.getAd() for requesting a stream ad in the getView()
	  		</td>
		</tr>

		<tr>
			<td align="center">setActive()</td>
			<td width='250px' style='word-break:break-all;'>
				call <span style='color:#E74C3C'>adapter.setActive()</span> when the listview is active now
	  		</td>
			<td width='250px' style='word-break:break-all;'>
				call <span style='color:#E74C3C'>streamHelper.setActive()</span> when the listview is active now
	  		</td>
		</tr>
		
	</tbody>
</table>

[Back to Top](./stream/#stream_import)

---------------------------------------

﻿<h4 id='stream_scroll_status' style='color:green;margin-bottom:15px'>onScrollListener()</h4>

- SDK needs to know that the scroll status of your `ListView`
- SDK will play the video ad only in the idle status (`OnScrollListener.SCROLL_STATE_IDLE`)
- just add the `DeferStreamAdapter` or `StreamHelper` to your `ListView`, they had already implemented the `OnScrollListener` interface

<p/>
[Sample-StreamHelper][Stream-onScroll-StreamHelper]
<p/>
[Sample-DeferStreamHelper][Stream-onScroll-defer]
<p/>

<codetag tag="Stream-onScroll-defer"/>
```java
//	let the SDK know the scroll status
//
mListView.setOnScrollListener(mAdapter);
```
<p/>

- if your `ListView` has already added the `onScrollListener()` :
<br/>
[Sample Code][Stream-onScroll]
<codetag tag="Stream-onScroll"/>
```java
mListView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view,
					int scrollState) {
				// ...
				// if you have already implemented this listener,
				// add your original code here
				// ...

				if (mAdapter != null) {
					mAdapter.onScrollStateChanged(view,
							scrollState);
				}
			}

			@Override
			public void onScroll(AbsListView view,
					int firstVisibleItem, int visibleItemCount,
					int totalItemCount) {
				// ...
				// if you have already implemented this listener,
				// add your original code here
				// ...

				if (mAdapter != null) {
					mAdapter.onScroll(
							view,
							firstVisibleItem,
							visibleItemCount,
							totalItemCount);
				}
			}
		});
```
<p/>

- if you use the pull to refresh list view (`PullToRefreshListView`), then be sure to that the position value will be shift 1 on the `onScroll()` callback, since the library adds one header view in the head of your dataset
<br/>
[Sample Code][Stream-Pull-OnScrollListener]
<codetag tag="Stream-Pull-OnScrollListener"/>
```java
pullToRefreshListView
		.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view,
					int scrollState) {
				// ...
				// if you have already implemented this listener,
				// add your original code here
				// ...

				if (adapter != null) {
					adapter.onScrollStateChanged(view,
							scrollState);
				}
			}

			@Override
			public void onScroll(AbsListView view,
					int firstVisibleItem, int visibleItemCount,
					int totalItemCount) {
				// ...
				// if you have already implemented this listener,
				// add your original code here
				// ...

				if (adapter != null) {
					final int FIRST_VISIBLE_ITEM_OFFSET = -1;
					// pass the right position on to the SDK
					//
					adapter.onScroll(
							view,
							firstVisibleItem
									+ FIRST_VISIBLE_ITEM_OFFSET,
							visibleItemCount,
							totalItemCount);
				}
			}
		});
```
<p/>

[Back to Top](./stream/#stream_import)

---------------------------------------

<h4 id='stream_itemclick' style='color:green;margin-bottom:15px'>Process onItemClickListener()</h4>

- if your `ListView` does not added `onItemClickListener()`, skip this step.
- check whether the position is an ad or not

[Sample Code][Stream-setOnItemClickListener]
<codetag tag="Stream-setOnItemClickListener"/>
```java
//	if you have not implemented setOnItemClickListener,
//	skip this code
//
mListView.setOnItemClickListener(new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		//	check is this position is a ad first
		//
		if(mAdapter != null && mAdapter.isAd(position)) {
			return;
		}

		//	...
		//	then add your original code here
		//	...

	}

});
```
<p/>

- if your `ListView` use (`PullToRefreshListView`) :

[Sample Code][Stream-OnItemClickListener]
<codetag tag="Stream-OnItemClickListener"/>
```java
//	if you use PullToRefresh library, 
//	then you should check the position offset 
//	in the scroll listener and item click listener
//
pullToRefreshListView
		.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {

				final int FIRST_VISIBLE_ITEM_OFFSET = -1;
				position = position + FIRST_VISIBLE_ITEM_OFFSET;

				//	you should check is this position is ad first
				//	then do your original logic later
				//
				if (adapter != null && adapter.isAd(position)) {
					return;
				}

				// ...
				// if you have already implemented this listener,
				// add your original code here
				// ...

				Intent intent = new Intent();
				intent.setClass(CEStreamActivity.this, CEContentActivity.class);
				startActivity(intent);
				finish();

			}
		});
```
<p/>

[Back to Top](./stream/#stream_import)

---------------------------------------

<h4 id='stream_adload' style='color:green;margin-bottom:15px'>StreamHelper add onADLoaded() callback</h4>

- SDK needs to know that which position can be inserted in your dataset when the ad finish loading
- this position will be saved in the StreamHelper, and you will get the ad by calling the getAd(position) in getView() next time
- when the stream ad loading finish, SDK will call `onADLoaded()` and expect one return position value by the App
- in `onADLoaded()` callback, you should insert one null object into your dataset first, then call `adapter.notifyDataSetChanged()`, and return this position index for SDK
- if you return -1, it means that App do not need any ad.
- the `StreamHelper` will preroll one ad after you instantiate directly

<span style='font-weight: bold;color:red'>
Note:
</span>
<br/>
<span style='font-weight: bold;color:red'>
in order to load the ad as faster as possible, the StreamHelper will preroll one ad after you instantiate it directly, so onADLoaded() will be called automatically once before you call getAd(0)
</span>
<br/>


[Sample Code-StreamHelper][Stream-StreamHelper-onADLoaded]
<p/>
[Sample Code-DeferStreamAdapter][Stream-onADLoaded]
<codetag tag="Stream-onADLoaded"/>
```java
@Override
public int onADLoaded(int position) {
	//
	// 	when one stream ad has been loaded,
	//	the SDK will need to know which position 
	//	can show this ad in your DataSet.
	//	so the SDK will call onADLoaded(position) for getting 
	//	one position that you have already allocate in your DataSet.
	//	then, if you call getAD(position) in the getView() later,
	//	the SDK will return one ad or null refer to onADLoaded's 
	//	return value.
	//
	//	if you return "-1", it means that the ad is not added in your 
	//	DataSet.

	//	for example:
	//	if you return "5" from the onADLoaded(position) first,
	//	and you request a ad by calling the getAD(5) in the getView() 
	//	later,
	//	the SDK will know that this position(5) can response a ad for 
	//	the App 
	//	

	position = getDefaultMinPosition(position);

	if (mList != null && mList.size() >  position) {	

		// just allocate one position for stream ad
		//
		mList.add(position, null);

		//	be sure to call the notifyDataSetChanged()
		//
		notifyDataSetChanged();

		return position;
	}
	else {				
		return -1;
	}
}
```
<p/>


[Back to Top](./stream/#stream_import)

---------------------------------------

<h4 id='stream_notify' style='color:green;margin-bottom:15px'>Process Adapter.notifyDataSetChanged()</h4>

- check all of your original ad positions before calling `Adapter.notifyDataSetChanged()`

[Sample Code-StreamHelper][Stream-StreamHelper-notifyDataSetChanged]
<p/>
[Sample Code-DeferStreamAdapter][Stream-notifyDataSetChanged]
<codetag tag="Stream-notifyDataSetChanged"/>
```java
@Override
public void notifyDataSetChanged() {
	//	if your DataSet has been changed
	//	the SDK will need to re-allocate the ad 
	//	which you have added in the DataSet before
	//
	for (Integer pos : getAddedPosition()) {
		if(mList == null || pos > mList.size()) {
			return;
		}

		//	check ad case
		//
		if(mList.get(pos) == null || mList.get(pos).equals("null")) {
			continue;
		}

		mList.add(pos , null);
	}
	super.notifyDataSetChanged();
}
```
<p/>

[Back to Top](./stream/#stream_import)

---------------------------------------

<h4 id='stream_itemview' style='color:green;margin-bottom:15px'>Process Adapter.getItemViewType()</h4>

-  check whether the position is an ad or not before return it

[Sample Code-StreamHelper][Stream-StreamHelper-getItemViewType]
<p/>
[Sample Code-DeferStreamAdapter][Stream-getItemViewType]
<codetag tag="Stream-getItemViewType"/>
```java
@Override
public int getItemViewType(int position) {
	//	if you have implemented getItemViewType(), 
	//	be sure to check if the item is an ad 
	//	in this position.
	if(isAd(position)) {
		return super.getItemViewType(position);
	}else{
		//	return your view type here
		//
		//
	}
}
```
<p/>

[Back to Top](./stream/#stream_import)

---------------------------------------

<h4 id='stream_getview' style='color:green;margin-bottom:15px'>Process Adapter.getView()</h4>

- request a stream ad in the head of the getView() logic
- you will get the ad according to the `onADLoaded()` return value

[Sample Code-StreamHelper][Stream-StreamHelper-getView]
<p/>
[Sample Code-DeferStreamAdapter][Stream-getView]
<codetag tag="Stream-getView"/>
```java
// Get ad view if possible
final View adView =  getAD(position);	

//	or you can resize the ad width by this way
//	final View adView =  getAD(position, someIntWidth);
//

//	or remove the background
//	final View adView =  getAD(position, false);
//

if(adView != null) {
	//	you can set the background
	//	such as
	//	adView.setBackgroundColor(Color.BLACK);
	//	adView.setBackgroundResource(your resid);
	//	adView.setBackgroundDrawable(your background drawable);
	return adView;
}
```
<p/>

```
if this position is not a ad

SDK will use background thread to load the ad further.

after finish loading, SDK will call onADLoaded()

and expect one return value that has inserted in the dataset

note:

onADLoaded() is called by main-thread

```

[Back to Top](./stream/#stream_import)

---------------------------------------

<h4 id='stream_active' style='color:green;margin-bottom:15px'>StreamHelper.setActive()</h4>

- App needs to call `setActive()` to let the SDK process this placement first
- only has the [onResume()](./stream/#stream_activity) and `setActive()` status stream helper can start the video ad
- so if you have multiple listview, SDK will process the last one called the `setActive()`
- if you use `ViewPager`, it could initial more than one listview at the same time, so you should know which page is selected, and set to active status correctly :
- there are two checkpoints about the `ViewPager` integration
	1. in the `PageAdapter`'s `instantiateItem()`, be sure to that this `position` is really seen by user now, and call `setActive()`
	<br/>
	[Sample Code][Stream-ViewPager-init]
	2. in the `onPageSelected()` and `onPageScrollStateChanged()`, 
	call `setActive()` after `OnScrollListener.SCROLL_STATE_IDLE`
	<br/>
	[Sample Code][Stream-ViewPager-setActive]
	
<br/>
[Sample Code-StreamHelper][Stream-StreamHelper-active]
<br/>
[Sample Code-DeferStreamAdapter][Stream-active]

[Back to Top](./stream/#stream_import)

---------------------------------------

<h4 id='stream_activity' style='color:green;margin-bottom:15px'>Handle Activity lifecycle</h4>

- the `StreamHelper` needs to know whether the listview is resume, pause or destroy
- remember to call `StreamHelper.release()` to avoid `memory leak` when your listview is onDestroy

```
if you use multiple listview in the activity

1. every StreamHelper has its own status (resume or pause)
   
2. only in the resume and active status StreamHelper can start the video ad
```

<br/>
[Sample Code-multiple-listview][Stream-CEStreamActivity]
<br/>
[Sample Code-StreamHelper][Stream-StreamHelper-life]
<br/>
[Sample Code-DeferStreamAdapter][Stream-life]
<codetag tag="Stream-life"/>
```java
@Override
public void onResume() {
	super.onResume();

	if(mAdapter != null) {
		mAdapter.onResume();
	}
}

@Override
public void onPause() {
	super.onPause();

	if(mAdapter != null) {
		mAdapter.onPause();
	}
}

@Override
public void onDestroy() {
	super.onDestroy();

	if(mAdapter != null) {
		mAdapter.release();
		mAdapter = null;
	}

}
```
<p/>

[Back to Top](./stream/#stream_import)

---------------------------------------

<h4 id='fix_stream' style='color:green;margin-bottom:15px'>Stream ad at the fixed position</h4>

- the integration of fixed position stream ad is the same as defer stream.
- the only difference is that initializing the helper as below and pass the `tag name` on to the helper.

<p/>
[Sample- Use FixPositionStreamHelper][Stream-StreamHelper-TagInit]
<p/>
<codetag tag="Stream-StreamHelper-TagInit"/>
```java
mStreamHelper = StreamHelper.getFixPositionStreamHelper(this, mTagName);
```
<p/>
or
<p/>
[Sample- Use FixPositionStreamAdapter][Stream-fix-init]
<p/>

<codetag tag="Stream-fix-init"/>
```java
mAdapter = new ExtendFixPositionStreamAdapter(
		this, 
		mTagName, 
		mItems);
```
<p/>

- for getting the stream ad, you will need to submit `tag name` to Intowow as the `placement`.


[Back to Top](./stream/#stream_import)

---------------------------------------

- please go through the <a target="_blank" href="../checkpoint">Checkpoint</a> after finish integration


<p/>

[Stream-init]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/SingleDeferAdapterActivity.java#L34 "SingleDeferAdapterActivity.java" 
[Stream-onScroll-defer]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/SingleDeferAdapterActivity.java#L79 "SingleDeferAdapterActivity.java" 
[Stream-onScroll]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/SingleDeferAdapterActivity.java#L116 "SingleDeferAdapterActivity.java" 
[Stream-notifyDataSetChanged]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/ExtendDeferStreamAdapter.java#L227 "ExtendDeferStreamAdapter.java" 
[Stream-StreamHelper-notifyDataSetChanged]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/streamhelper/StreamHelperAdapter.java#L70 "StreamHelperAdapter.java" 
[Stream-getItemViewType]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/ExtendDeferStreamAdapter.java#L72 "ExtendDeferStreamAdapter.java" 
[Stream-StreamHelper-getItemViewType]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/streamhelper/StreamHelperAdapter.java#L99 "StreamHelperAdapter.java" 
[Stream-getView]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/ExtendDeferStreamAdapter.java#L92 "ExtendDeferStreamAdapter.java" 
[Stream-StreamHelper-getView]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/streamhelper/StreamHelperAdapter.java#L118 "StreamHelperAdapter.java" 
[Stream-StreamHelper-active]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/streamhelper/MultipleStreamHelperActivity.java#L429 "MultipleStreamHelperActivity.java" 
[Stream-active]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/SingleDeferAdapterActivity.java#L71 "SingleDeferAdapterActivity.java" 
[Stream-ViewPager-setActive]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/MultipleDeferAdapterActivity.java#L141 "MultipleDeferAdapterActivity.java" 
[Stream-ViewPager-init]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/MultipleDeferAdapterActivity.java#L509 "MultipleDeferAdapterActivity.java" 
[Stream-defer-init]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/SingleDeferAdapterActivity.java#L64 "SingleDeferAdapterActivity.java" 
[Stream-StreamHelper-init]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/streamhelper/SingleStreamHelperActivity.java#L85 "SingleStreamHelperActivity.java" 
[Stream-onScroll-StreamHelper]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/streamhelper/SingleStreamHelperActivity.java#L144 "SingleStreamHelperActivity.java" 
[Stream-onADLoaded]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/ExtendDeferStreamAdapter.java#L183 "ExtendDeferStreamAdapter.java" 
[Stream-StreamHelper-onADLoaded]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/streamhelper/SingleStreamHelperActivity.java#L89 "SingleStreamHelperActivity.java" 
[Stream-life]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/SingleDeferAdapterActivity.java#L156 "SingleDeferAdapterActivity.java" 
[Stream-StreamHelper-life]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/streamhelper/SingleStreamHelperActivity.java#L164 "SingleStreamHelperActivity.java" 
[Stream-OnItemClickListener]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L665 "CEStreamActivity.java" 
[Stream-Pull-OnScrollListener]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L701 "CEStreamActivity.java" 
[Stream-setOnItemClickListener]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/defer/SingleDeferAdapterActivity.java#L87 "SingleDeferAdapterActivity.java" 
[Stream-CEStreamActivity]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/cedemo/CEStreamActivity.java#L52 "CEStreamActivity.java" 
[Stream-fix-init]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/fixposition/SingleFixPositionAdapterActivity.java#L61 "SingleFixPositionAdapterActivity.java" 
[Stream-StreamHelper-TagInit]:https://github.com/ddad-daniel/CrystalExpressSDK-CN-Demo/tree/master/src/com/intowow/crystalexpress/stream/streamhelper/SingleFixPositionStreamHelperActivity.java#L85 "SingleFixPositionStreamHelperActivity.java" 

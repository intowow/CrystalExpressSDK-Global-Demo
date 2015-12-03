<h3 id='api' style='color:red'>Change log</h3>

[Document Change Log](./changelog/#doc_modify)
<br/>
[SDK Change Log](./changelog/#sdk_modify)

---------------------------------------

<h4 id='doc_modify' style='color:green'>Document Change Log</h4>

<table border="1">
	<thead>
		<tr>
			<td style='color:green'>Version</td><td align="center" style='color:green'>Date</td><td align="center" style='color:green;' width=350px>Description</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">8.0</td><td align="center">2015/10/22</td>
			<td>
				<ul>
					<li>add integration of fixed position stream ad</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">7.0</td><td align="center">2015/09/15</td>
			<td>
				<ul>
					<li>add enter foreground splash ad debug method</li>
					<li>modify the integration of the splash ad</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">6.0</td><td align="center">2015/08/30</td>
			<td>
				<ul>
					<li>open splash add <a target="_blank" href="../opensplash#opensplash-enterforeground">enter foreground</a> case</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">5.0</td><td align="center">2015/08/14</td>
			<td>
				<ul>
					<li>modify <a target="_blank" href="../adevent">ad event callback </a>onAdClick interface</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">4.0</td><td align="center">2015/08/04</td>
			<td>
				<ul>
					<li>reduce the integration in <a target="_blank" href="../activity_setting">Activity</a> setting</li>
					<li>modify <a target="_blank" href="../opensplash">open splash</a></li>
					<li>modify <a target="_blank" href="../checkpoint">checkpoint</a></li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">3.0</td><td align="center">2015/07/27</td>
			<td>
				<ul>
					<li>AndroidManifest.xml add <a target="_blank" href="../androidmanifest/#Activity">WebViewActivity</a></li>
					<li><a target="_blank" href="../checkpoint">add checkpoint</a></li>
					<li><a target="_blank" href="../faq">add Trouble Shooting</a></li>
					<li>modify <a target="_blank" href="../preview/#preview-setting">preview mode</a></li>
					<li>modify <a target="_blank" href="../stream">In-Stream</a></li>
				</ul>
				
			</td>
		</tr>
		<tr>
			<td align="center">2.0</td><td align="center">2015/07/06</td>
			<td>
				add Adapter.setActive()
			</td>
		</tr>
		<tr>
			<td align="center">1.0</td><td align="center">2015/07/02</td><td>first version</td>
		</tr>
	</tbody>
</table>

[Back to Top](./changelog/#api)

---------------------------------------

<h4 id='sdk_modify' style='color:green'>SDK Change Log(<a target="_blank" href="../before#import">download SDK</a>)</h4>

<table border="1">
	<thead>
		<tr>
			<td align="center" style='color:green'>Version</td><td align="center" style='color:green'>Date</td><td align="center" style='color:green;' width=350px>Description</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">1.5</td><td align="center">2015/12/03</td>
			<td>
				<ul>
					<li>add new 'SPONSOR' price type for ad</li>
					<li>add 'time zone' feature for ad serving</li>
					<li>add reset last impression time for ad serving</li>
					<li>add required impression behavior for ad serving</li>
					<li>add 'ad_list_ref', 'device_level', 'total_file_size', 'impressions' properties in the tracking event</li>
					<li>fix incorrect remove tracking event bug</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.4</td><td align="center">2015/11/02</td>
			<td>
				<ul>
					<li>remove AWS SDK</li>
					<li>support audience targeting</li>
					<li>handle facebook link while opening the deep link</li>
					<li>check normal http link while opening the in-app browser</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.3.4</td><td align="center">2015/10/22</td>
			<td>
				<ul>
					<li>remove api setBackground() in the stream ad format</li>
					<li>improve the prefetch performance</li>
					<li>check if the SDK status is ready while use streamhelper.clearAddedAd()</li>
					<li>use full screen in the splash image format</li>
					<li>add fixed position stream ad</li>
					<li>fix trace active placement bug</li>
					<li>handle out of memory while loading assets</li>
					<li>adjust the retry interval for tracking</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.58</td><td align="center">2015/09/28</td>
			<td>
				<ul>
					<li>add try-catch in public API</li>
					<li>fix tracking bug</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.55</td><td align="center">2015/09/24</td>
			<td>
				<ul>
					<li>add dynamicly change ad mode</li>
					<li>add tracking retry interval in the CN enviroment</li>
					<li>add arrive button in the replay mode of the "SPLASH2_GENERAL_P" format</li>
					<li>add dynamicly change ad mode</li>
					<li>add request timeout function</li>
					<li>add public API for StreamHelper</li>
					<li>add dismiss time for the "SPLASH2_GENERAL_P" format</li>
					<li>remove time slot check when downloading creative</li>
					<li>improve fetch creative performance</li>
					<li>fix the bug that when downloading creative finish</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.48</td><td align="center">2015/09/10</td>
			<td>
				<ul>
					<li>fix "clearAddedAd" API bug</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.47</td><td align="center">2015/09/08</td>
			<td>
				<ul>
					<li>add background fetch tracking</li>
					<li>add ad request token</li>
					<li>add "clearAddedAd" API in the StreamHelper</li>
					<li>add "onRecreate" API in the StreamHelper</li>
					<li>improve preroll ad in the StreamHelper</li>
					<li>improve third-party tracking</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.46</td><td align="center">2015/08/30</td>
			<td>
				<ul>
					<li>add splash ad support display control function</li>
					<li>add splash ad auto close API</li>
					<li>add webview format ad</li>
					<li>add use non-defer request stream ad api function</li>
					<li>fix webview memory leak bug</li>
					<li>fix webview format ad engage error bug</li>
					<li>fix stream ad lose the first ad when preroll is successful bug</li>
					<li>fix null point exception when ad ready bug</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.43</td><td align="center">2015/08/21</td>
			<td>
				<ul>
					<li>update SDK internal function</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.41</td><td align="center">2015/08/14</td>
			<td>
				<ul>
					<li>update SDK internal function</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.40</td><td align="center">2015/08/09</td>
			<td>
				<ul>
					<li>add in-read ad remove the background function</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td  align="center">
				<ul>
					<li>1.1.39</li>
					<li>1.1.35</li>
				</ul>
			</td>
			<td align="center">
				<ul> 
					<li>2015/08/07</li>
					<li>2015/08/06</li>
				</ul>
			</td>
			<td>
				<ul>
					<li>update SDK internal function</li>
					<li>fix SDK internal bug</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.34</td><td align="center">2015/08/04</td>
			<td>
				<ul>
					<li>modify WIFI hint</li>
					<li>fix SDK internal bug</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.30</td><td align="center">2015/07/27</td>
			<td>
				<ul>
					<li>add in-app open web view function</li>
					<li>add third party tracking impression link function</li>
					<li>add <a target="_blank" href="../opensplash/#OpenSplash-callback">(<span style="color:red">overridePendingTransition</span>)</a> for splash ad</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.26</td><td align="center">2015/07/02</td>
			<td>
				<ul>
					<li>ad test mode</li>
					<li>add fetch ad policy for foreground status</li>
					<li>add geo-targeting</li>
					<li>add in-stream ad setBackground function</li>
					<li>add in-stream ad resize the width function</li>
					<li>add wifi only hint</li>
					<li>supports multiple ad placement group</li>
					<li>improve the video animation effect</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">1.1.10</td><td align="center">2015/05/18</td>
			<td>
				<ul>
					<li>increase the speed of updating the assets</li>
					<li>modify the preview mode</li>
				</ul>
			</td>
		</tr>
	</tbody>
</table>

[Back to Top](./changelog/#api)

<br/>
<br/>

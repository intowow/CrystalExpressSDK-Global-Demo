﻿<h3 id='before' style='color:red'>Checkpoint</h3>
<table border="1">
	<thead>
		<tr>
			<td align="center" style='color:green'>Item</td><td align="center" style='color:green'>Description</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">
				the Activity's life-cycle
			</td>
			<td>
				verify all of your activity extend the <a target="_blank" href="../activity_setting">BaseActivity</a>
			</td>
		</tr>
		<tr>
			<td align="center">
				Splash Ad
			</td>
			<td>
				it can show the ad normally when start the <a target="_blank" href="../opensplash/#OpenSplash-onConfigurationChanged">landscape ad</a>
			</td>
		</tr>
		<tr>
			<td align="center">
				In-Stream Ad
			</td>
			<td>
				<ul>
					<li><a target="_blank" href="../stream/#stream_itemclick">the previous or next item of the ad can be clicked correctly</a></li>
					<li><a target="_blank" href="../stream/#stream_scroll_status">the video ad can play correctly in the idle status after finish scrolling</a></li>
					<li><a target="_blank" href="../stream/#stream_active">if you use multiple list view (view pager), swipe your list view, and the video ad can play correctly in the idle status</a></li>
					<li><a target="_blank" href="../stream/#stream_notify">when you call adaprer.notifyDataSetChanged(), these ads which have been added in your list can be shown in the right position</a></li>
					<li><a target="_blank" href="../stream/#stream_activity">it can stop and start the video ad correctly when you press HOME button and reenter the App again</a></li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">
				In-Read Ad
			</td>
			<td>
				<a target="_blank" href="../content/#content_life">it can stop and start the video ad correctly when you press HOME button and reenter the App again</a>
			</td>
		</tr>
		<tr>
			<td align="center">Preview Mode</td>
			<td>
				<a target="_blank" href="../preview">it can see the preview ad after scaning the QRCode</a>
			</td>
		</tr>
	</tbody>
</table>

<br/>
<br/>

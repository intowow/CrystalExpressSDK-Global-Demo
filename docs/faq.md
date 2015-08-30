﻿<h3 id='before' style='color:red'>Trouble shooting</h3>
<table border="1">
	<thead>
		<tr>
			<td align="center" style='color:green'>Question</td><td align="center" style='color:green'>Answer</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="center">App can not open or crash</td>
			<td>
				<ul>
					<li>verify your configuration of the <a target="_blank" href="../androidmanifest">AndroidManifest.xml</a> is correctly</li>
					<li>the length of the <a target="_blank" href="../androidmanifest/#meta-data">CRYSTAL_ID</a> in the AndroidManifest.xml should be 32, excludes the space character</li>
					<li>if your configuration is wrong, then you can see the error message in the DDMS, such as <span style="color:red">please adding these properties in the AndroidManifest.xml</span></li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">
				why can i not see any ads
			</td>
			<td>
				<ul>
                	<li>check your external storage (SD card) folder, if the ad downloading complete, you will find  creative in the path {root/CrystalExpressGlobe/...}</li>
                	<li>please use the Test mode for your testing first</li>
					<li>if you want to use Test mode, be sure to verify that the <a target="_blank" href="../activity_setting/#testmode">BaseActivity.java</a> has changed to I2WAPI.init(context, true)</li>
					<li>verify your internet connection is working well</li>
					<li>you can not use two Apps which have the same Crystal Id in one phone at the same time. (demo App and your App)</li>
					<li>you should reinstall App every time before you want to change the CrystalId</li>
					<li>if your internet speed is slow, then wait a minute for SDK downloading ads</li>
					<li>verify your <a target="_blank" href="../naming/#placement">Ad Placement</a></li>
					<li>verify your <a target="_blank" href="../activity_setting">Activity</a>，let all of your activity to extend the BaseActivity</li>
					<li>the ad may be in the <a target="_blank" href="https://github.com/roylo/CrystalExpressDocumentation-iOS-zh_CN/blob/master/terminology.md/#user-content-ad-serving-control-廣告投放控制">ad serving control</a> status</li>
				</ul>
			</td>
		</tr>
		<tr>
			<td align="center">
				some errors occurred when showing the landscape splash ad
			</td>
			<td>
				<ul>
					<li>verify the android:configChanges="orientation|screenSize" property has been added inside your activity of the Androidmanifest.xml, if not, your activity will go through some life-cycle again and leads some errors occur</li>
				</ul>
			</td>
		</tr>
	</tbody>
</table>

<br/>
<br/>

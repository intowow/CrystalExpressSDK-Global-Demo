package com.intowow.crystalexpress.stream.defer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.intowow.crystalexpress.BaseActivity;
import com.intowow.crystalexpress.Config;
import com.intowow.crystalexpress.LayoutManager;
import com.intowow.crystalexpress.LayoutManager.LayoutID;
import com.intowow.crystalexpress.MainActivity;
import com.intowow.crystalexpress.R;

public class MultipleDeferAdapterActivity extends BaseActivity {

	//***********************************************
	//	common UI
	//
	private final static int ITEM_SIZE = 200;
	private List<List<Object>> mItems = new ArrayList<List<Object>>(ITEM_SIZE);
	private RelativeLayout mTitleLayout = null;
	private BreadcrumbView mBreadcrumbView = null;
	private ArrayList<String> mSections;
	private ViewPager mPager;
	private SectionPagerAdapter mPagerAdapter;
	private int mTitleHeight = 0;
	private int mActiveIndex = -1;
	private int mDeferIndex = 0;
	private int mScrollState = OnScrollListener.SCROLL_STATE_IDLE;
	
	public interface PagerEventListener {
		public void onPageChanged(int pos);
	}
	
	//***********************************************
	//	stream ad 
	//
	/**you can setup placements for your section from the server*/
	private final static String[] mPlacements = Config.STREAM_PLACEMENTS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(createContentView());
	}
	
	private View createContentView() {
		LayoutManager lm = LayoutManager.getInstance(this);

		// list view simulate data
		//
		List<Object> obj = null;
		for(int i = 0 ; i < mPlacements.length; i++) {
			obj = new ArrayList<Object>();
			for (int j = 0; j < ITEM_SIZE; j++) {
				obj.add(new Object());
			}
			mItems.add(obj);
		}
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);

		RelativeLayout contentView = new RelativeLayout(this);
		contentView.setLayoutParams(params);
		contentView.setBackgroundColor(Color.parseColor("#e7e7e7"));

		mTitleHeight = lm.getMetric(LayoutID.STREAM_TITLE_HEIGHT);
		// title
		params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, mTitleHeight);
		mTitleLayout = new RelativeLayout(this);
		mTitleLayout.setId(2000);
		mTitleLayout.setLayoutParams(params);
		mTitleLayout.setBackgroundResource(R.drawable.stream_title);

		// menu
		//
		mSections = new ArrayList<String>();
		for(String tag : Config.STREAM_TAG_NAMES) {
			mSections.add(tag);
		}

		params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				lm.getMetric(LayoutID.STREAM_TITLE_HEIGHT));
		params.addRule(RelativeLayout.BELOW, 2000);
		mBreadcrumbView = new BreadcrumbView(this);
		mBreadcrumbView.setId(1002);
		mBreadcrumbView.setLayoutParams(params);
		mBreadcrumbView.setSectionList(mSections);

		// menu bottom line
		//
		params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				lm.getMetric(LayoutID.STREAM_MENU_BOTTOM_LINE_HEIGHT));
		params.addRule(RelativeLayout.BELOW, 1002);
		final View menuBottomLine = new View(this);
		menuBottomLine.setBackgroundColor(Color.parseColor("#d2d2d2"));
		menuBottomLine.setLayoutParams(params);
		menuBottomLine.setId(5000);

		// news list
		//
		mPagerAdapter = new SectionPagerAdapter(this, mSections);

		params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.BELOW, 5000);

		mPager = new ViewPager(this);
		mPager.setLayoutParams(params);
		mPager.setAdapter(mPagerAdapter);
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			//XXX@Stream-ViewPager-setActive@#Stream-ViewPager-setActive#
			@Override
			public void onPageScrollStateChanged(int state) {
				mScrollState = state;

				if (state == OnScrollListener.SCROLL_STATE_IDLE) {
					if (mActiveIndex != mDeferIndex) {

						// you can set the right adapter to active
						// after the status is idle
						//
						performDeferUpdate();
					}
				}

			}
			
			@Override
			public void onPageSelected(final int pos) {
				mDeferIndex = pos;

				if (mScrollState == OnScrollListener.SCROLL_STATE_IDLE) {

					// you can set the right adapter to active
					// after the status is idle
					//
					performDeferUpdate();
				}
			}
			//end

			@Override
			public void onPageScrolled(int pos, float positionOffset,
					int positionOffsetPixels) {
				mBreadcrumbView.select(pos + positionOffset);
			}
		});

		contentView.addView(mTitleLayout);
		contentView.addView(mBreadcrumbView);
		contentView.addView(menuBottomLine);
		contentView.addView(mPager);

		mBreadcrumbView.setOnSectionSelecteListener(new PagerEventListener() {
			@Override
			public void onPageChanged(int pos) {
				mPager.setCurrentItem(pos);
			}
		});

		final int ACTIVE_INDEX = 0;
		mBreadcrumbView.select(ACTIVE_INDEX);
		mPager.setCurrentItem(ACTIVE_INDEX);
		mPagerAdapter.refreshAd(ACTIVE_INDEX);

		return contentView;
	}

	private void performDeferUpdate() {
		mActiveIndex = mDeferIndex;
		mPagerAdapter.refreshAd(mActiveIndex);
	}

	@Override
	public void onResume() {
		super.onResume();
		
		if (mPagerAdapter != null) {
			mPagerAdapter.resumeAd();
		}
	}

	@Override
	public void onPause() {
		super.onPause();

		if (mPagerAdapter != null) {
			mPagerAdapter.stopAd();
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (mPagerAdapter != null) {
			mPagerAdapter.release();
			mPagerAdapter = null;
		}
		
		if (mPager != null) {
			mPager.setOnPageChangeListener(null);
			mPager = null;
		}
	}

	class BreadcrumbView extends LinearLayout implements OnClickListener {
		static private final int ID_TEXTBOX = 1001;

		int mSectionWidth;
		int mScreenWidth;
		int mCenter;

		Context mContext;
		int mSectionPadding;

		View v;
		HorizontalScrollView mController;
		LinearLayout mTextBox;
		View mIndicator;

		PagerEventListener mOnSectionSelectListener;

		LayoutManager mlm;

		public BreadcrumbView(Context context) {
			super(context);

			mlm = LayoutManager.getInstance(MultipleDeferAdapterActivity.this);

			setBackgroundColor(Color.WHITE);
			mContext = context;

			DisplayMetrics metrics = mContext.getResources()
					.getDisplayMetrics();
			mScreenWidth = metrics.widthPixels;
			mSectionWidth = metrics.densityDpi / 2;
			mSectionPadding = (int) (8 * metrics.density);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					mlm.getMetric(LayoutID.STREAM_TITLE_HEIGHT));
			mController = new HorizontalScrollView(mContext);
			mController.setHorizontalScrollBarEnabled(false);
			mController.setLayoutParams(params);

			params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			RelativeLayout outerWrapper = new RelativeLayout(mContext);
			outerWrapper.setLayoutParams(params);

			RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					mlm.getMetric(LayoutID.STREAM_INDICATOR_HEIGHT));
			rParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

			mIndicator = new View(mContext);
			mIndicator.setLayoutParams(rParams);
			mIndicator.setBackgroundColor(Color.parseColor("#ea5a31"));

			rParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			mTextBox = new LinearLayout(mContext);
			mTextBox.setId(ID_TEXTBOX);
			mTextBox.setOrientation(LinearLayout.HORIZONTAL);
			mTextBox.setLayoutParams(rParams);

			outerWrapper.addView(mTextBox);
			outerWrapper.addView(mIndicator);

			mController.addView(outerWrapper);

			addView(mController);
		}

		public void setColor(int bg, int highlight) {
			mController.setBackgroundColor(bg);
			mIndicator.setBackgroundColor(highlight);
		}

		public void setSectionList(ArrayList<String> sections) {
			if (sections.size() == 0) {
				return;
			}

			if (mScreenWidth / mSectionWidth > sections.size()) {
				mSectionWidth = mScreenWidth / sections.size();
			}
			mCenter = (mScreenWidth - mSectionWidth) / 2;

			LayoutParams lp = new LayoutParams(mSectionWidth,
					LayoutParams.WRAP_CONTENT);

			for (int i = 0; i < sections.size(); i++) {
				TextView tv = new TextView(mContext);
				tv.setText(sections.get(i));
				tv.setPadding(0, mSectionPadding, 0, mSectionPadding);
				tv.setGravity(Gravity.CENTER);
				tv.setTypeface(null, Typeface.BOLD);
				tv.setTextColor(Color.parseColor("#ea5a31"));
				tv.setTag(i);
				tv.setOnClickListener(this);
				tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
						mlm.getMetric(LayoutID.STREAM_MENU_TEXT_SIZE));

				mTextBox.addView(tv, lp);
			}

			ViewGroup.LayoutParams indicatorLp = (ViewGroup.LayoutParams) mIndicator
					.getLayoutParams();
			indicatorLp.width = mSectionWidth;
		}

		public void select(float position) {
			final int indicatorPos = (int) (mSectionWidth * position);

			// Move indicator
			RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mIndicator
					.getLayoutParams();
			lp.setMargins(indicatorPos, 0, 0, 0);
			mIndicator.setLayoutParams(lp);

			// reset text color
			//
			for (int i = 0; i < mTextBox.getChildCount(); i++) {
				TextView tv = (TextView) mTextBox.getChildAt(i);
				if (position != i) {
					tv.setTextColor(Color.parseColor("#b4b4b4"));
				} else {
					tv.setTextColor(Color.parseColor("#ea5a31"));
				}
			}

			// Move scroller
			mController.post(new Runnable() {
				public void run() {
					if (indicatorPos > mCenter) {
						mController.scrollTo(indicatorPos - mCenter, 0);
					} else {
						mController.scrollTo(0, 0);
					}
				}
			});
		}

		@Override
		public void onClick(View v) {
			select((Integer) v.getTag());
			if (mOnSectionSelectListener != null) {
				mOnSectionSelectListener.onPageChanged((Integer) v.getTag());
			}
		}

		public void setOnSectionSelecteListener(
				final PagerEventListener listener) {
			mOnSectionSelectListener = listener;
		}
	}

	class SectionPagerAdapter extends PagerAdapter {
		ArrayList<String> mSections;
		HashMap<Integer, RelativeLayout> mCanvas;
		SparseArray<ExtendDeferStreamAdapter> mAdapters;

		Context mContext;
		int mAdPos = 0;

		@SuppressLint("UseSparseArrays")
		public SectionPagerAdapter(final Context c, ArrayList<String> sections) {
			mContext = c;
			mSections = sections;
			mCanvas = new HashMap<Integer, RelativeLayout>();
			mAdapters = new SparseArray<ExtendDeferStreamAdapter>();
		}

		public void release() {
			mContext = null;
			if (mAdapters != null) {
				for (int i = 0; i < mAdapters.size(); ++i) {
					mAdapters.valueAt(i).release();
				}
			}
			mCanvas = null;
			mAdapters = null;
		}

		public void refreshAd(int position) {
			//	let the SDK know that this adapter is active now
			//
			if (mAdapters.get(position) != null) {
				mAdapters.get(position).setActive();
			}

			mAdPos = position;
		}

		public void stopAd() {
			if (mAdapters != null) {
				for (int i = 0; i < mAdapters.size(); ++i) {
					mAdapters.valueAt(i).onPause();
				}
			}
		}

		public void resumeAd() {
			if (mAdapters != null) {

				for (int i = 0; i < mAdapters.size(); ++i) {
					mAdapters.valueAt(i).onResume();
				}
			}
		}

		@Override
		public int getCount() {
			return mSections.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		public Object instantiateItem(View collection, final int position) {
			RelativeLayout canvas = null;
			if (mCanvas.containsKey(position)) {
				canvas = mCanvas.get(position);
			} else {

				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT,
						LinearLayout.LayoutParams.MATCH_PARENT);

				canvas = new RelativeLayout(mContext);
				canvas.setLayoutParams(params);
				mCanvas.put(position, canvas);

				final List<Object> items = mItems.get(position);
				
				//	XXX
				// adapter
				//
				final ExtendDeferStreamAdapter adapter = new ExtendDeferStreamAdapter(
						MultipleDeferAdapterActivity.this,
						mPlacements[position],
						items);

				// put the adapter
				//
				mAdapters.put(position, adapter);

				// ListView
				//
				RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.MATCH_PARENT);
				
				final ListView listView = new ListView(MultipleDeferAdapterActivity.this);
				listView.setLayoutParams(rParams);
				listView.setBackgroundColor(Color.parseColor("#e7e7e7"));
				listView.setDivider(null);
				listView.setDividerHeight(0);
				
				//	let the SDK know the scroll status
				//
				listView.setOnScrollListener(adapter);
				
				listView.setAdapter(adapter);
				
				canvas.addView(listView);
			}

			((ViewPager) collection).addView(canvas);

			//XXX@Stream-ViewPager-init@#Stream-ViewPager-init#
			if (mAdPos == position) {
				// let the SDK know that this placement is active now
				//
				refreshAd(mAdPos);
			}
			//end

			return canvas;
		}

		public void destroyItem(View collection, int position, Object view) {
			((ViewPager) collection).removeView((View) view);
			view = null;
		}
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		startActivity(intent);
		
		finish();
	}
}

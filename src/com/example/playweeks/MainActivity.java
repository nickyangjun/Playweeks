package com.example.playweeks;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private ViewPager mTabPager;
	private ImageView mTab1, mTab2, mTab3;
	private int currIndex = 0;
	private int zero = 0;
	private int one;
	private int two;
	private int three;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		mTabPager = (ViewPager) findViewById(R.id.tabpager);
		mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		mTab1 = (ImageView) findViewById(R.id.img_newcard);
		mTab2 = (ImageView) findViewById(R.id.img_charge);
		mTab3 = (ImageView) findViewById(R.id.img_consle);
		
		mTab1.setOnClickListener(new MyOnClickListener(0));
		mTab2.setOnClickListener(new MyOnClickListener(1));
		mTab3.setOnClickListener(new MyOnClickListener(2));
		
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.main_tab_default_page, null);
		View view2 = mLi.inflate(R.layout.main_tab_myactivities_page, null);
		View view3 = mLi.inflate(R.layout.main_tab_friend_page, null);
		
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			// @Override
			// public CharSequence getPageTitle(int position) {
			// return titles.get(position);
			// }

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mTabPager.setAdapter(mPagerAdapter);
		mTab1.setImageDrawable(getResources().getDrawable(
				R.drawable.newcard_f));
		
	}
	
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};
	
	public class MyOnPageChangeListener implements OnPageChangeListener {
		public void onPageSelected(int arg0) {
			Animation animation = null;
			Log.i("nick","pagechange: "+arg0);
			switch (arg0) {
			
			case 0:
				mTab1.setImageDrawable(getResources().getDrawable(
						R.drawable.newcard_f));
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.charg));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.consle));
				}
				break;
			case 1:
				mTab2.setImageDrawable(getResources().getDrawable(
						R.drawable.charg_f));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.newcard));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.consle));
				} 
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(
						R.drawable.consle_f));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.newcard));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.charg));
				} 
				break;
			
			}
			currIndex = arg0;
			
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageScrollStateChanged(int arg0) {
		}
	}

	

}

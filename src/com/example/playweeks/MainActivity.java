package com.example.playweeks;

import java.util.ArrayList;
import java.util.List;

import com.example.fragment.ViewPagerFragmentMyActivities;
import com.example.fragment.ViewPagerFragmentFriends;
import com.example.fragment.ViewPagerFragmentLogin;
import com.example.fragment.ViewPagerFragmentMain;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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

public class MainActivity extends FragmentActivity {
	
	private ViewPager mTabPager;
	private ImageView mTab1, mTab2, mTab3,mTab4;
	private int currIndex = 0;
	private int zero = 0;
	private int one;
	private int two;
	private int three;
	List<Fragment> fragmentList = new ArrayList<Fragment>();
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mContext = this;
		mTabPager = (ViewPager) findViewById(R.id.tabpager);
		mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		mTab1 = (ImageView) findViewById(R.id.img_newcard);
		mTab2 = (ImageView) findViewById(R.id.img_charge);
		mTab3 = (ImageView) findViewById(R.id.img_consle);
		mTab4 = (ImageView) findViewById(R.id.img_login);
		
		mTab1.setOnClickListener(new MyOnClickListener(0));
		mTab2.setOnClickListener(new MyOnClickListener(1));
		mTab3.setOnClickListener(new MyOnClickListener(2));
		mTab4.setOnClickListener(new MyOnClickListener(3));
		
		/*
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.main_tab_default_page, null);
		View view2 = mLi.inflate(R.layout.main_tab_myactivities_page, null);
		View view3 = mLi.inflate(R.layout.main_tab_friend_page, null);
		View view4 = mLi.inflate(R.layout.main_tab_my_page, null);
		
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
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
		//defaut mianpage
		mTabPager.setCurrentItem(0);
		mTab1.setImageDrawable(getResources().getDrawable(
				R.drawable.newcard_f));
		
*/
	
			fragmentList.add(new ViewPagerFragmentMain(mContext));

	        fragmentList.add(new ViewPagerFragmentMyActivities(mContext));

	        fragmentList.add(new ViewPagerFragmentFriends(mContext));
	        fragmentList.add(new ViewPagerFragmentLogin(mContext));
	        mTabPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(), fragmentList));
	}
	 /**

     * 定义适配器

     * 

     * @author gxwu@lewatek.com 2012-11-15

     */

    class myPagerAdapter extends FragmentPagerAdapter {


        private List<Fragment> fragmentList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList){

            super(fm);

            this.fragmentList = fragmentList;
        }

        /**

         * 得到每个页面

         */

        @Override

        public Fragment getItem(int arg0) {

            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);

        }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
		    return fragmentList == null ? 0 : fragmentList.size();
		}


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
			case 3:
				mTab4.setImageDrawable(getResources().getDrawable(
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

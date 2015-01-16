package com.example.fragment;

import com.example.playweeks.R;
import com.example.playweeks.R.layout;
import com.example.views.MyImageView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class ViewPagerFragmentMain  extends Fragment {


  
    private TextView tv = null;
    private LayoutInflater mInflater;
    private Context mContext;
    private LinearLayout mGallery;
    private int[] mImgIds;


	public ViewPagerFragmentMain(Context mContext){
		this.mContext = mContext;
    }
	private void initData()
	{
		mImgIds = new int[] { R.drawable.left_bottom, R.drawable.left_top, R.drawable.right,
				R.drawable.left_bottom, R.drawable.left_top, R.drawable.left_top, R.drawable.left_top,
				R.drawable.left_top, R.drawable.left_bottom };
	}
    /**

     * 覆盖此函数，先通过inflater inflate函数得到view最后返回

     */

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.main_tab_default_page, container, false);

    	mInflater = LayoutInflater.from(v.getContext());
    	mGallery = (LinearLayout) v.findViewById(R.id.id_gallery);
    	initData();
    	for (int i = 0; i < mImgIds.length/3; i++)
		{
    	
    		View view = mInflater.inflate(R.layout.main_page_gallery_item_img3,
					mGallery, false);
    		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),     
    				mImgIds[i*3+2]);  
    		
    		MyImageView myImageView=(MyImageView) view.findViewById(R.id.c_idea);
    		myImageView.setImageResource(mImgIds[i*3]);
    		MyImageView myImageView2=(MyImageView) view.findViewById(R.id.c_joke);
    		myImageView2.setImageResource(mImgIds[i*3+1]);
    		MyImageView myImageView3=(MyImageView) view.findViewById(R.id.c_constellation);
    		myImageView3.setImageResource(mImgIds[i*3+2]);
    		//myImageView3.setImageBitmap(ThumbnailUtils.extractThumbnail(bitmap,200,300));
    		mGallery.addView(view);
		}
    	
    	mGallery = (LinearLayout) v.findViewById(R.id.id_gallery2);
    	for (int i = 0; i < mImgIds.length/4; i++)
		{
    	
    		View view = mInflater.inflate(R.layout.main_page_gallery_item_img4,
					mGallery, false);
    		
    		MyImageView myImageView=(MyImageView) view.findViewById(R.id.item_img4_0);
    		myImageView.setImageResource(mImgIds[i*4]);
    		MyImageView myImageView2=(MyImageView) view.findViewById(R.id.item_img4_1);
    		myImageView2.setImageResource(mImgIds[i*4+1]);
    		MyImageView myImageView3=(MyImageView) view.findViewById(R.id.item_img4_2);
    		myImageView3.setImageResource(mImgIds[i*4+2]);
    		MyImageView myImageView4=(MyImageView) view.findViewById(R.id.item_img4_3);
    		myImageView4.setImageResource(mImgIds[i*4+3]);
    		//myImageView3.setImageBitmap(ThumbnailUtils.extractThumbnail(bitmap,200,300));
    		mGallery.addView(view);
		}
    
        return v;

    }

}



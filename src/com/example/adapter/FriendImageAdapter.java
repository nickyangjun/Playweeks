package com.example.adapter;

import com.example.playweeks.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendImageAdapter  extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	
	
	public FriendImageAdapter(Context c)  
	 {  
	  mContext=c;  
	  mInflater = LayoutInflater.from(mContext);
	 }  
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder;
		  if(convertView==null)  
		  {  
			  convertView = mInflater.inflate(R.layout.friends_item, null);
			  holder = new ViewHolder();
			  holder.name = (TextView) convertView.findViewById(R.id.friend_name);
			  holder.img = (ImageView) convertView.findViewById(R.id.friend_image);
			  convertView.setTag(holder);
		  }  
		  else  
		  {  
			  holder = (ViewHolder)convertView.getTag();
		  }  
		 
		  return convertView;  
	}  
	 public final class ViewHolder{
	    public TextView name;
	    public ImageView img;
	    }

}

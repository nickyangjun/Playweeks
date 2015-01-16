package com.example.fragment;

import com.example.adapter.FriendImageAdapter;
import com.example.playweeks.R;
import com.example.playweeks.R.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class ViewPagerFragmentFriends  extends Fragment {


    private String   text;

    private TextView tv = null;
    private Context mContext;



	public ViewPagerFragmentFriends(Context mContext){

        super();

        this.mContext = mContext;

    }
    /**

     * 覆盖此函数，先通过inflater inflate函数得到view最后返回

     */

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.main_tab_friend_page, container, false);

        GridView gridview=(GridView)v.findViewById(R.id.gridview);  
        gridview.setAdapter(new FriendImageAdapter(mContext));
        
        
        gridview.setOnItemClickListener(new OnItemClickListener(){
         public void onItemClick(AdapterView<?> parent, View view, int position, long id)   
         {  
        	 Toast.makeText(mContext, ""+position,Toast.LENGTH_SHORT).show();
         }  
        });  

        return v;

    }

}



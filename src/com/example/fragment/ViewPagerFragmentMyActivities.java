package com.example.fragment;

import com.example.playweeks.LoginActivity;
import com.example.playweeks.R;
import com.example.playweeks.R.layout;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class ViewPagerFragmentMyActivities  extends Fragment {


    private String   text;

    private TextView tv = null;
    private LinearLayout unLoginLayoutInflater;
    private Button btn_login;
    private Context mContext;



	public ViewPagerFragmentMyActivities(Context context){

        super();

        mContext=context;

    }
    /**

     * 覆盖此函数，先通过inflater inflate函数得到view最后返回

     */

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.main_tab_myactivities_page, container, false);

        unLoginLayoutInflater = (LinearLayout) v.findViewById(R.id.unlogin_layout);
      //  unLoginLayoutInflater.setVisibility(View.GONE);
        
        btn_login = (Button) unLoginLayoutInflater.findViewById(R.id.act_login);
        btn_login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(mContext,com.example.playweeks.LoginActivity.class));
				startActivity(intent);
			}
        	
        });
        
        

        return v;

    }

}



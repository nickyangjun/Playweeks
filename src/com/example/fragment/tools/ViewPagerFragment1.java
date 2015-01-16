package com.example.fragment.tools;

import com.example.playweeks.R;
import com.example.playweeks.R.layout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class ViewPagerFragment1  extends Fragment {


    private String   text;

    private TextView tv = null;



	public ViewPagerFragment1(String text){

        super();

        this.text = text;

    }
    /**

     * 覆盖此函数，先通过inflater inflate函数得到view最后返回

     */

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.main_tab_my_page, container, false);

      

        return v;

    }

}



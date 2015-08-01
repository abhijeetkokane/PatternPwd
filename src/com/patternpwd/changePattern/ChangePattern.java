package com.patternpwd.changePattern;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.ppa.R;

public class ChangePattern extends SherlockFragmentActivity{
	
	public static ViewPager viewPager2;
	
	public static ArrayList<String> patternFirst,patternSecond;
	public static String FinalPattern;
	public static String prePattern;
	public static String fullName,email_Id,phNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_pattern);
		
		getSupportActionBar().setIcon(R.drawable.change_pattern);
		getSupportActionBar().setTitle("Change Pattern");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		viewPager2=(ViewPager)findViewById(R.id.pager2);
		FragmentAdapters mAdapter=new FragmentAdapters(getSupportFragmentManager()
				,ChangePattern.this);
		
		patternFirst = new ArrayList<String>();
		patternSecond = new ArrayList<String>();
		
		viewPager2.setAdapter(mAdapter);
		
	}
}
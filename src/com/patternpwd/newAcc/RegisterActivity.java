package com.patternpwd.newAcc;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.ppa.R;

public class RegisterActivity extends SherlockFragmentActivity {
	public static ActionBar actionBar;
	
	public static String fullName,email_Id,phNo;
	public static ArrayList<String> patternFirst,patternSecond;
	public static String FinalPattern;
	FragmentAdapter mAdapter;
	public static ViewPager viewPager;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.register_layout);
	
		actionBar = getSupportActionBar();
		actionBar.setIcon(R.drawable.new_account);
		actionBar.setTitle("New Account");
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		context = this;
		
		viewPager=(ViewPager)findViewById(R.id.pager1);
		mAdapter=new FragmentAdapter(getSupportFragmentManager(),context);
		
		patternFirst = new ArrayList<String>();
		patternSecond = new ArrayList<String>();
		
		viewPager.setAdapter(mAdapter);
	}
	
	@Override
	public void onBackPressed() {
		showAlert();
	}
	
	public void showAlert(){
		
		final AlertDialog.Builder confirmation = new AlertDialog.Builder(context);
		
		confirmation.setTitle("Cancel Registration Process ? ");
		
		confirmation.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		confirmation.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				((Activity) context).finish();
			}
		});
		
		confirmation.show();
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {

	    case android.R.id.home:
	    	showAlert();
	         break;
	    default:
	    }
	    return super.onOptionsItemSelected(item);
	}
}
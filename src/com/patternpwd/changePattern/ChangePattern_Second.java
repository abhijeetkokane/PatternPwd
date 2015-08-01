package com.patternpwd.changePattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.ppa.R;
import com.patternpwd.SecureDB.DatabaseHandler;
import com.patternpwd.SecureDB.UserRegister;

@SuppressLint("ValidFragment")
public class ChangePattern_Second extends SherlockFragment {
	Button confirm;
	Context context;
	DatabaseHandler db;
	public ChangePattern_Second(Context context){
		this.context=context;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.confirm_pattern, container, false);
		
		db = new DatabaseHandler(context);
		
		Button done=(Button)rootView.findViewById(R.id.c_confirm);
		Button clear=(Button)rootView.findViewById(R.id.c_cancel);
		
		Button createAccount = (Button)rootView.findViewById(R.id.cpattern_register);
		Button drawPattern=(Button)rootView.findViewById(R.id.cpattern_dpattern);
		
		final ImageView img1=(ImageView)rootView.findViewById(R.id.imageView1);
		final ImageView img2=(ImageView)rootView.findViewById(R.id.imageView2);
		final ImageView img3=(ImageView)rootView.findViewById(R.id.imageView3);
		final ImageView img4=(ImageView)rootView.findViewById(R.id.imageView4);
		final ImageView img5=(ImageView)rootView.findViewById(R.id.imageView5);
		final ImageView img6=(ImageView)rootView.findViewById(R.id.imageView6);
		final ImageView img7=(ImageView)rootView.findViewById(R.id.imageView7);
		final ImageView img8=(ImageView)rootView.findViewById(R.id.imageView8);
		final ImageView img9=(ImageView)rootView.findViewById(R.id.imageView9);
		
		createAccount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ChangePattern.viewPager2.setCurrentItem(0);
			}
		});
		
		drawPattern.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ChangePattern.viewPager2.setCurrentItem(1);
			}
		});
		
		clear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ChangePattern.patternSecond.clear();
				
				img1.setEnabled(true);
				img2.setEnabled(true);
				img3.setEnabled(true);
				img4.setEnabled(true);
				img5.setEnabled(true);
				img6.setEnabled(true);
				img7.setEnabled(true);
				img8.setEnabled(true);
				img9.setEnabled(true);
			}
		});
		
		img1.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				v.setEnabled(false);
				
				ChangePattern.patternSecond.add("1");
				
				return true;
			}
		});
		
		img2.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				v.setEnabled(false);
				
				ChangePattern.patternSecond.add("2");
				
				return true;
			}
		});
		
		img3.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				v.setEnabled(false);
				
				ChangePattern.patternSecond.add("3");
				
				return true;
			}
		});
		
		img4.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				v.setEnabled(false);
				
				ChangePattern.patternSecond.add("4");
				
				return true;
			}
		});
		
		img5.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				v.setEnabled(false);
				
				ChangePattern.patternSecond.add("5");
				
				return true;
			}
		});
		
		img6.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				v.setEnabled(false);
				
				ChangePattern.patternSecond.add("6");
				
				return true;
			}
		});
		
		img7.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				v.setEnabled(false);
				
				ChangePattern.patternSecond.add("7");
				
				return true;
			}
		});
		
		img8.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				v.setEnabled(false);
				
				ChangePattern.patternSecond.add("8");
				
				return true;
			}
		});
		
		img9.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				v.setEnabled(false);
				
				ChangePattern.patternSecond.add("9");
				
				return true;
			}
		});
		
		done.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(ChangePattern.patternSecond.size() > 3){
					
					if(ChangePattern.patternSecond.size() == ChangePattern.patternFirst.size()){
						if(checkPattern()){
							
							//_emailId, _pattern, _fullName, _mobNo
							db.updateUserPattern(new UserRegister(
									ChangePattern.email_Id,
									ChangePattern.FinalPattern,
									ChangePattern.fullName,
									ChangePattern.phNo));

							Toast.makeText(context,"Pattern Match User Pattern updated Sucessfully !", Toast.LENGTH_LONG).show();

							((Activity) context).finish();
							
						}else{
							Toast.makeText(context,"Draw same pattern as earlier .", Toast.LENGTH_LONG).show();
						}
					}else{
						Toast.makeText(context,"Draw same pattern as earlier .", Toast.LENGTH_LONG).show();
					}
					
				}else{
					Toast.makeText(context,"More Than 3 dots must be selected", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		
		
		return rootView;
	}
	
	
	boolean checkPattern(){
		boolean state = false;
		String tempPattern1 = "",tempPattern2 = "";
		
		for(int i = 0 ; i < ChangePattern.patternFirst.size() ; i++){
			
			if(ChangePattern.patternFirst.get(i).equals(ChangePattern.patternSecond.get(i))){
				tempPattern1 = ChangePattern.patternFirst.get(i)+tempPattern1;
				tempPattern2 = ChangePattern.patternSecond.get(i)+tempPattern2;
				
				state = true;		//return true
			}else{
				state = false;		//return false
				break ;//break for loop
			}
		}
		
		if(tempPattern1.length() > 0 && tempPattern2.length() > 0){
			if(tempPattern1.equals(tempPattern2)){
				ChangePattern.FinalPattern = reverseString(tempPattern1);
			//	Toast.makeText(context, ChangePattern.FinalPattern, Toast.LENGTH_LONG).show();
			}
		}
		return state;
	}
	
	public String reverseString(String original){
		String reversedString = "";
		int length = original.length();
		
		for ( int i = length - 1 ; i >= 0 ; i-- ){
			reversedString = reversedString + original.charAt(i);
		}
		return reversedString;
	}
}
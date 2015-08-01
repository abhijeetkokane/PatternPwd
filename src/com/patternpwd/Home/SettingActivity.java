package com.patternpwd.Home;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.ppa.R;
import com.patternpwd.SecureDB.DatabaseHandler;
import com.patternpwd.SecureDB.UserRegister;
import com.patternpwd.changePattern.ChangePattern;
import com.patternpwd.secureArea.Helper;
import com.patternpwd.secureArea.HomeSecureActivity;

public class SettingActivity extends SherlockActivity{
Context context;
ActionBar actionBar;
TextView general,about,changerText;
ListView recordList;
public static ArrayList<String> dataList1,dataList2,dataList3;
public static ArrayAdapter<String> dataAdapter;
DatabaseHandler db;
List<UserRegister> allUser = null;
boolean admin;
CheckBox apphomescreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_layout);
        context = this;
        
        actionBar=getSupportActionBar();
        actionBar.setIcon(R.drawable._settings);
        actionBar.setTitle("Settings");
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        general=(TextView)findViewById(R.id.generalsetting);
        about=(TextView)findViewById(R.id.about);
        changerText=(TextView)findViewById(R.id.changerText);
        apphomescreen=(CheckBox)findViewById(R.id.apphomescreen);
        recordList=(ListView)findViewById(R.id.dataList);
        
        db=new DatabaseHandler(context);
        
        dataList1 = new ArrayList<String>();
        dataList2 = new ArrayList<String>();
        dataList3 = new ArrayList<String>();
        
        dataAdapter = new DBListAdapter(context,R.layout.pane_list, dataList1,
        		dataList2,dataList3);
        
        recordList.setAdapter(dataAdapter);
        recordList.setCacheColorHint(0);
		
        general.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent toSecureArea = new Intent(context, ChangePattern.class);
				toSecureArea.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(toSecureArea);
			}
		});
        
        about.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dataList1.clear();
				dataList2.clear();
				dataList3.clear();
				changerText.setText("Pattern Password");
				
				dataList1.add("About");
				dataList2.add(" ");
				dataList3.add("The Pattern Password is an android based application use to" +
						" set password for your account according to your pattern .\n" +
						"The Pattern Password genarates a new password on each login.");
				
				dataList1.add("Application Build Version");
				dataList2.add(" ");
				dataList3.add("1.0.1");
				
				dataAdapter.notifyDataSetChanged();
			}
		});
        
        //check for previously set lock by mail
        if(new Helper().getMyStringPref(context, 
        		"lock_by_email").length() > 0){
        	apphomescreen.setChecked(true);
        }
        
        apphomescreen.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					admin=false;
					logIn();
				}else{
					admin=true;
					
					final AlertDialog.Builder confirmation = new AlertDialog.Builder(context);
					
					confirmation.setTitle("You want to remove home lock screen ? ");
					
					confirmation.setNegativeButton("No", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					
					confirmation.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							new Helper().setMyStringPref(context, 
					        		"lock_by_email","");
							
							// by clearing pref string lock screen 
							// remove from device 
						}
					});
					
					confirmation.show();
				}
			}
		});
	}
	
	public void logIn() {
		
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View layout = layoutInflater.inflate(R.layout.setting_popup, null);
		
		final TextView headtext= (TextView)layout.findViewById(R.id.headtext); 
		final EditText passwordSetting = (EditText)layout.findViewById(R.id.pwdabhi);
		final Button logIn = (Button)layout.findViewById(R.id.logIn);
		final Button cancel = (Button)layout.findViewById(R.id.cancelPop);
		
		cancel.setVisibility(Button.GONE);
		final Dialog pWindow = new Dialog(context,R.style.PopUpTheme);
		
		pWindow.setContentView(layout);
		
		if(admin == false ){
			passwordSetting.setHint("Enter Valid Email");
			passwordSetting.setInputType(InputType.TYPE_CLASS_TEXT);
			logIn.setText("Check");
			headtext.setText("Set home screen lock");
			cancel.setVisibility(Button.VISIBLE);
		}
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		
		lp.copyFrom(pWindow.getWindow().getAttributes());
		
		lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		
		pWindow.getWindow().setAttributes(lp);
		pWindow.setCanceledOnTouchOutside(false);
		pWindow.show();
		
		logIn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(admin){
				if(passwordSetting.length() > 0 && passwordSetting.getText().toString().equals("abhi$@33")){
					Toast.makeText(context, "Valid show DB", Toast.LENGTH_SHORT).show();
					pWindow.dismiss();
					changerText.setText("Database");
					
					allUser = db.getAllUsers();
					for(UserRegister singleuser : allUser){	//get single record from DB and adds in list

						dataList1.add(singleuser.getFName());
						dataList2.add(singleuser.getEmail());
						dataList3.add(singleuser.getPattern());
						
						dataAdapter.notifyDataSetChanged();
					}
				}else{
					Toast.makeText(context, "Enter Valid Password", Toast.LENGTH_SHORT).show();
				}
				}else{
					if(passwordSetting.length() > 0 && android.util.Patterns.EMAIL_ADDRESS.matcher(
							passwordSetting.getText().toString()).matches()){
						if(checkEmail(passwordSetting.getText().toString())){	
							//hideKeyBoard(context);
							Toast.makeText(context, "Valid user !", Toast.LENGTH_SHORT).show();
							showAlert(passwordSetting.getText().toString());
						}else{
							
						}
					}else{
						Toast.makeText(context, "Enter valid email", Toast.LENGTH_SHORT).show();	
					}
				}
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pWindow.dismiss();
				if(apphomescreen != null){
					apphomescreen.setChecked(false);
				}
			}
		});
	}

	public boolean checkEmail(String emailId){
		boolean checkFlag = false;
		
		allUser = db.getAllUsers();//get all user from DB
		
		if(db.getDBCount() > 0){
			
			for(UserRegister singleuser : allUser){	//get single record from DB
				//GET EMAIL_ID FROM DB AND CHECK IT WITH USER-ENTER EMAIL_ID
				if(singleuser.getEmail().equals(emailId)){
					checkFlag = true;
					break;
				}else{
					checkFlag = false;
				}
			}
		}else{
			checkFlag = false;
		}
		return checkFlag;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.setting, menu);
		return true;
	}
	
	public void hideKeyBoard(Context context) {
		
		InputMethodManager inputManager = (InputMethodManager) 
				context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow
        (((Activity) context).getCurrentFocus().getWindowToken(), 
        		InputMethodManager.HIDE_NOT_ALWAYS);
		
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
	    	finish();
	         break;
		case R.id.admin:
			admin=true;
			dataList1.clear();
			dataList2.clear();
			dataList3.clear();
			
			
			dataAdapter.notifyDataSetChanged();
			changerText.setText("");
			
			logIn();
			break;
		}
		return true;
	}
	
	public void showAlert(final String emailId){
		
		final AlertDialog.Builder confirmation = new AlertDialog.Builder(context);
		
		confirmation.setTitle("Are you sure you want to set lock screen ? ");
		
		confirmation.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		confirmation.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent toSecureArea = new Intent(context, HomeSecureActivity.class);
				//toSecureArea.putExtra("lock_by_email", emailId);
				new Helper().setMyStringPref(context, "lock_by_email", emailId);
				toSecureArea.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(toSecureArea);
				finish();
			}
		});
		
		confirmation.show();
	}
}
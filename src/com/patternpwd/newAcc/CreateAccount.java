package com.patternpwd.newAcc;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.ppa.R;
import com.patternpwd.SecureDB.DatabaseHandler;
import com.patternpwd.SecureDB.UserRegister;

@SuppressLint("ValidFragment")
public class CreateAccount extends SherlockFragment {
	
	Button submit,clearfields;
	EditText fullname,validemail,mobno;
	Context context;
	DatabaseHandler db;
	List<UserRegister> allUser = null;
	
	public CreateAccount(Context context){
		this.context=context;
	}
	
	@Override
	public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
		
	View rootView = inflater.inflate(R.layout.register, container, false);
	
	db = new DatabaseHandler(context);
	
	submit = (Button)rootView.findViewById(R.id.submit);
	clearfields=(Button)rootView.findViewById(R.id.clearfields);
	
	fullname=(EditText)rootView.findViewById(R.id.fullname);
	validemail=(EditText)rootView.findViewById(R.id.validemail);
	mobno=(EditText)rootView.findViewById(R.id.mobno);
	
	submit.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			if(fullname.length() > 0){
				
				if(validemail.length() > 0 && android.util.Patterns.EMAIL_ADDRESS.matcher(
						validemail.getText().toString()).matches()){
					
					if(mobno.length() > 0 && mobno.length() == 10){
						
						if(checkEmail(validemail.getText().toString())){
							
							RegisterActivity.actionBar.setTitle("Hi "+fullname.getText().toString());
						
							RegisterActivity.fullName = fullname.getText().toString();
							RegisterActivity.email_Id = validemail.getText().toString();
							RegisterActivity.phNo = mobno.getText().toString();
						
						
							hideKeyBoard(context);
							Toast.makeText(context, "User Available , Sucessfully Added !", Toast.LENGTH_SHORT).show();
							
							RegisterActivity.viewPager.setCurrentItem(1);
							
						}else{
							Toast.makeText(context, "User exist choose different emailId", Toast.LENGTH_SHORT).show();
						}
					}else{
						Toast.makeText(context, "Enter 10 digit mobile number", Toast.LENGTH_SHORT).show();
					}
				}else{
					Toast.makeText(context, "Enter valid email", Toast.LENGTH_SHORT).show();	
				}
			}else{
				Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show();
			}
		}
	});
	
	clearfields.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			fullname.setText("");
			validemail.setText("");
			mobno.setText("");
		}
	});
	
	return rootView;
	};

	public boolean checkEmail(String emailId){
		boolean checkFlag = false;
		
		allUser = db.getAllUsers();//get all user from DB
		
		if(db.getDBCount() > 0){
			
			for(UserRegister singleuser : allUser){	//get single record from DB
				//GET EMAIL_ID FROM DB AND CHECK IT WITH USER-ENTER EMAIL_ID
				if(singleuser.getEmail().equals(emailId)){
					checkFlag = false;
					break;
				}else{
					checkFlag = true;
				}
			}
		}else{
			checkFlag = true;
		}
		return checkFlag;
	}

	public void hideKeyBoard(Context context) {
		
		InputMethodManager inputManager = (InputMethodManager) 
				context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow
        (((Activity) context).getCurrentFocus().getWindowToken(), 
        		InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
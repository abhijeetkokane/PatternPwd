package com.patternpwd.changePattern;

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
public class CheckEmailExist extends SherlockFragment {
	
	Button submit,clearfields;
	EditText fullname,validemail,mobno;
	Context context;
	DatabaseHandler db;
	List<UserRegister> allUser = null;
	
	public CheckEmailExist(Context context){
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
	
	fullname.setVisibility(EditText.GONE);
	mobno.setVisibility(EditText.GONE);
	
	submit.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(validemail.length() > 0 && android.util.Patterns.EMAIL_ADDRESS.matcher(
					validemail.getText().toString()).matches()){

				if(checkEmail(validemail.getText().toString())){						
					hideKeyBoard(context);
					Toast.makeText(context, "User Found !", Toast.LENGTH_SHORT).show();
					
					UserRegister c_user= db.getUser(validemail.getText().toString());
					
					ChangePattern.fullName=c_user.getFName();
					ChangePattern.email_Id=c_user.getEmail();
					ChangePattern.phNo=c_user.getMobNo();
					ChangePattern.prePattern=c_user.getPattern();
					
					ChangePattern.viewPager2.setCurrentItem(1);
					setprePattern();
					//Toast.makeText(context, ChangePattern.prePattern, Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(context, "User not found try again", Toast.LENGTH_SHORT).show();
				}
			}else{
				Toast.makeText(context, "Enter valid email", Toast.LENGTH_SHORT).show();
			}
		}
	});
	
	
	clearfields.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			validemail.setText("");
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
	
	public void hideKeyBoard(Context context) {
		
		InputMethodManager inputManager = (InputMethodManager) 
				context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow
        (((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		
	}
	
	
	public void setprePattern(){
		//according to users previous pattern set images selected
		if(ChangePattern.prePattern.length() > 0){
			for(int i = 0;i < ChangePattern.prePattern.length() ;i++){
				//ChangePattern.prePattern.charAt(i);
				switch (ChangePattern.prePattern.charAt(i)){
					case 1:
						ChangePattern_First.img1.setEnabled(false);
						break;
					case 2:
						ChangePattern_First.img2.setEnabled(false);
						break;
					case 3:
						ChangePattern_First.img3.setEnabled(false);
						break;
					case 4:
						ChangePattern_First.img4.setEnabled(false);
						break;
					case 5:
						ChangePattern_First.img5.setEnabled(false);
						break;
					case 6:
						ChangePattern_First.img6.setEnabled(false);
						break;
					case 7:
						ChangePattern_First.img7.setEnabled(false);
						break;
					case 8:
						ChangePattern_First.img8.setEnabled(false);
						break;
					case 9:
						ChangePattern_First.img9.setEnabled(false);
						break;
				}
			}
		}
	}
}
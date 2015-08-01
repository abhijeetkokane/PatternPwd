package com.patternpwd.secureArea;

import java.util.List;
import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ppa.R;
import com.patternpwd.SecureDB.DatabaseHandler;
import com.patternpwd.SecureDB.UserRegister;

public class HomeSecureActivity extends Activity {
	
    
    Context context;
    EditText password;
    Button result;
    DatabaseHandler db;
    List<UserRegister> allUser = null;
    String UUIDS;
    String PATTERN_STRING;
    String final_password = "";
    boolean emailFlag ;
    TextView text1,text2,text3,text4,text5,text6,text7,text8,text9;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_lock);
        context = this;
        
        db = new DatabaseHandler(context);
        
        password=(EditText)findViewById(R.id.finalPasswordText);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        
        result=(Button)findViewById(R.id.finalResult);
        
        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
        text5 = (TextView)findViewById(R.id.text5);
        text6 = (TextView)findViewById(R.id.text6);
        text7 = (TextView)findViewById(R.id.text7);
        text8 = (TextView)findViewById(R.id.text8);
        text9 = (TextView)findViewById(R.id.text9);
        
        createAndsetUUID();
        
        generatePasswordbyEmail();
		
        CheckBox show_passwd=(CheckBox)findViewById(R.id.show_passwd);
        //emailId
        
        show_passwd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (!isChecked) {
                    // show password
					password.setTransformationMethod(PasswordTransformationMethod.getInstance());
				} else {
                    // hide password
					password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				}
			}
		});
        
        result.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(password.length() > 0){
					if(password.getText().toString().equals(final_password)){
						Toast.makeText(context, "Correct Password", Toast.LENGTH_SHORT).show();
						finish();
					}else{
						Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show();
					}
				}else{
					Toast.makeText(context, "Enter password from Grid_Text", Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
    
    
    private void createAndsetUUID() {
    	UUIDS = UUID.randomUUID().toString();
        UUIDS = UUIDS.replace("-", "");
        
    	text1.setText(UUIDS.charAt(1)+"");
        text2.setText(UUIDS.charAt(2)+"");
        text3.setText(UUIDS.charAt(3)+"");
        text4.setText(UUIDS.charAt(4)+"");
        text5.setText(UUIDS.charAt(5)+"");
        text6.setText(UUIDS.charAt(6)+"");
        text7.setText(UUIDS.charAt(7)+"");
        text8.setText(UUIDS.charAt(8)+"");
        text9.setText(UUIDS.charAt(9)+"");
        
    }

    private void generatePasswordbyEmail(){
    	
    	final_password = ""; 
		allUser = db.getAllUsers();
		emailFlag = false;
			
			for(UserRegister singleuser : allUser){
				
				if(singleuser.getEmail().equals(new Helper().getMyStringPref(context, "lock_by_email"))){
					PATTERN_STRING = singleuser.getPattern();
					emailFlag = true;
				}else{
					emailFlag = false;
				}
			}
			
			
			if(emailFlag){
				
				//Toast.makeText(context, "User Found ", Toast.LENGTH_SHORT).show();
				
				for (int i = 0 ; i < PATTERN_STRING.length() ; i++){
					
					String singlecharacterofPattern_inString =
							Character.toString((PATTERN_STRING.charAt(i)));
					
					int finalIntegerValfromabove = Integer.parseInt(singlecharacterofPattern_inString);
					
					final_password = final_password + Character.toString(
							UUIDS.charAt(finalIntegerValfromabove));
					
				}
			}else{
				// if email id not found then disable lock screen
				Toast.makeText(context, "Register first", Toast.LENGTH_SHORT).show();
				finish();
			}
    }
    
    @Override
    public void onBackPressed() {
    	Toast.makeText(context, "Enter valid password to unlock", Toast.LENGTH_SHORT).show();
    }
}
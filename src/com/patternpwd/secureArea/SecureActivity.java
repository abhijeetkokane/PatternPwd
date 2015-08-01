package com.patternpwd.secureArea;


import java.util.List;
import java.util.UUID;

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
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

public class SecureActivity extends SherlockActivity {
	
    ActionBar actionBar;
    Context context;
    EditText email,password;
    Button check,result;
    DatabaseHandler db;
    List<UserRegister> allUser = null;
    String UUIDS;
    String PATTERN_STRING;
    String final_password = "";
    boolean emailFlag ;
    ScrollView gridTextArea;
    LinearLayout emailHolder;
    TextView text1,text2,text3,text4,text5,text6,text7,text8,text9;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secure_layout);
        context = this;
        
        db = new DatabaseHandler(context);
        
        actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.secure);
        actionBar.setTitle("Secure Area");
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        emailHolder = (LinearLayout)findViewById(R.id.linear1);
        
        gridTextArea=(ScrollView)findViewById(R.id.gridTextArea);
        gridTextArea.setVisibility(ScrollView.GONE);
        
        email=(EditText)findViewById(R.id.email_Id);
        password=(EditText)findViewById(R.id.finalPasswordText);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        
        check=(Button)findViewById(R.id.checkButton);
        result=(Button)findViewById(R.id.finalResult);
        
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
        
        email.setText(getIntent().getStringExtra("emailId"));
        
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
        
        check.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				generatePasswordbyEmail();
			}
		});
        
        result.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(password.length() > 0){
					if(password.getText().toString().equals(final_password)){
						Toast.makeText(context, "Correct Password", Toast.LENGTH_SHORT).show();
						//Toast.makeText(context, "!! HURREY ABHIJEET U R BEST !!", Toast.LENGTH_LONG).show();
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
    	
    	/*  randomUUID(); 
    	 *  Static factory to retrieve a type 4 
    	 * (pseudo randomly generated) UUID. 
    	 * The UUID is generated using a 
    	 * cryptographically strong pseudo 
    	 * random number generator. */
    	
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
    	
    	if(email.length() > 0 && android.util.Patterns.EMAIL_ADDRESS.matcher(
				email.getText().toString()).matches()){
			
			final_password = "";	//clears final password string 
			allUser = db.getAllUsers();//get all user from DB
			emailFlag = false;
			
			for(UserRegister singleuser : allUser){	//get single record from DB
				
				//GET EMAIL_ID FROM DB AND CHECK IT WITH USER-ENTER EMAIL_ID
				//IF FOUND SAME THEN RETRIVE ITS PATTERN FROM patterns COLOUMN . 
				
				if(singleuser.getEmail().equals(email.getText().toString())){
					PATTERN_STRING = singleuser.getPattern();
					emailFlag = true;
				}else{
					emailFlag = false;
				}
			}
			
			
			if(emailFlag){
				
				Toast.makeText(context, "User found", Toast.LENGTH_SHORT).show();
				
				actionBar.setTitle(email.getText().toString());
				
				emailHolder.setVisibility(LinearLayout.GONE);
				
				gridTextArea.setVisibility(ScrollView.VISIBLE);
				
				for (int i = 0 ; i < PATTERN_STRING.length() ; i++){
					
					// here we get all character one after another
					// char singlechar_from_pattern_string = PATTERN_STRING.charAt(i);
					
					String singlecharacterofPattern_inString =
							Character.toString((PATTERN_STRING.charAt(i)));
					
					int finalIntegerValfromabove = Integer.parseInt(singlecharacterofPattern_inString);
					
		//			String finalStringcharofPasswordfrom_GridText = Character.toString(
		//					UUIDS.charAt(finalIntegerValfromabove));
					
					final_password = final_password + Character.toString(
							UUIDS.charAt(finalIntegerValfromabove));
					
				}
			}else{
				Toast.makeText(context, "Email_id not found", Toast.LENGTH_SHORT).show();
			}
		}else{
			Toast.makeText(context, "Enter valid email", Toast.LENGTH_SHORT).show();
		}
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.securearea, menu);
		
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			finish();
			break;
		case R.id.refresh_pattern:
			createAndsetUUID();
			generatePasswordbyEmail();
			break;
		}

		return true;
	}
}
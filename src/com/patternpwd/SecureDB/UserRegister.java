package com.patternpwd.SecureDB;

public class UserRegister {
	
	//private variables
	String _emailId;
	String _pattern;
	String _fullName;
	String _mobNo;

	//private variables
	int _email_idNumber;
	String _patternUser;
	
	// Empty constructor
	public UserRegister(){
		
	}
	
	// constructor
	public UserRegister(String _emailId){
		this._emailId = _emailId;
	}
		
	// constructor
	public UserRegister(String _emailId,String _pattern,String _fullName,String _mobNo){
		this._emailId = _emailId;
		this._pattern = _pattern;
		this._fullName = _fullName;
		this._mobNo = _mobNo;
	}
	
	// constructor
	public UserRegister(int _email_idNumber){
		this._email_idNumber = _email_idNumber;
	}
				
	// constructor
	public UserRegister(int _email_idNumber,String _pattern ){
		this._email_idNumber = _email_idNumber;
		this._pattern = _pattern;
	}
	
	
	// getting pattern
	public String getPattern(){
		return this._pattern;
	}
	
	// setting pattern
	public void setPattern(String _pattern){
		this._pattern = _pattern;
	}
	
	// getting email
	public String getEmail(){
		return this._emailId;
	}
	
	// setting email
	public void setEmail(String _emailId){
		this._emailId = _emailId;
	}
	
	// getting f_name
	public String getFName(){
		return this._fullName;
	}
		
	// setting f_name
	public void setFName(String _fullName){
		this._fullName = _fullName;
	}
	
	// getting mobNo
	public String getMobNo(){
		return this._mobNo;
	}
			
	// setting mobNo
	public void setMobNo(String _mobNo){
		this._mobNo = _mobNo;
	}
}
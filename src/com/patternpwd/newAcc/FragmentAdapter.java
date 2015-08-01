package com.patternpwd.newAcc;
	 
	
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
	 
	public class FragmentAdapter extends FragmentPagerAdapter {
		
		Context context;
		
		public FragmentAdapter(FragmentManager fm,Context context) {
			super(fm);
			this.context=context;
		}

		@Override
	    public Fragment getItem(int index) {
	 
	        switch (index) {
	        
	        case 0:
	            return new CreateAccount(context);
	        case 1:
	            return new DrawPattern_First(context);
	        case 2:
	            return new Draw_Pattern_Second(context);
	        }
	 
	        return null;
	    }
	 
	    @Override
	    public int getCount() {
	    	return 3;
	    }
	}
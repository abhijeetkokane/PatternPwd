package com.patternpwd.changePattern;
	 
	
	import android.content.Context;
	import android.support.v4.app.Fragment;
	import android.support.v4.app.FragmentManager;
	import android.support.v4.app.FragmentPagerAdapter;
		 
		public class FragmentAdapters extends FragmentPagerAdapter {
			
			Context context;
			
			public FragmentAdapters(FragmentManager fm,Context context) {
				super(fm);
				this.context=context;
			}

			@Override
		    public Fragment getItem(int index) {
		 
		        switch (index) {

		        case 0:
		            return new CheckEmailExist(context);
		        case 1:
		            return new ChangePattern_First(context);
		        case 2:
		            return new ChangePattern_Second(context);
		        }
		        return null;
		    }
		 
		    @Override
		    public int getCount() {
		    	return 3;
		    }
		}
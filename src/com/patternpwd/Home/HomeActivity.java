package com.patternpwd.Home;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.patternpwd.newAcc.RegisterActivity;
import com.patternpwd.secureArea.SecureActivity;
import com.ppa.R;

public class HomeActivity extends SherlockActivity {
	
	Context context;
	ResizableImageView imageview;
	Handler handler;
	Runnable runnable;
	TextView headText;
	RelativeLayout newAccount,secureArea,settings,help,contactUs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homeoptions);
		
		context = this;
		
		imageview = (ResizableImageView)findViewById(R.id.imgChanger);
		
		getSupportActionBar();
		
		//getSupportActionBar().setBackgroundDrawable(getResources()
		//		.getDrawable(R.drawable.actionbar_bg));
		
		headText=(TextView)findViewById(R.id.headText);
		newAccount=(RelativeLayout)findViewById(R.id.new_acc);
		secureArea=(RelativeLayout)findViewById(R.id.secure_area);
		settings=(RelativeLayout)findViewById(R.id.settings);
		help=(RelativeLayout)findViewById(R.id.help);
		contactUs=(RelativeLayout)findViewById(R.id.contactUs);
		
		newAccount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent create_new_Acc = new Intent(HomeActivity.this,RegisterActivity.class);
				create_new_Acc.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(create_new_Acc);
			}
		});
		
		secureArea.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent secureArea = new Intent(HomeActivity.this,SecureActivity.class);
				secureArea.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(secureArea);
			}
		});
		
		
		settings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent setting = new Intent(HomeActivity.this,SettingActivity.class);
				setting.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(setting);
			}
		});
		
		
		help.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				help_PopUp();
			}
		});
		
		
		contactUs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				about_us_PopUp();
			}
		});
		
		final int []imageArray = {R.drawable.one,R.drawable.two,R.drawable.three};

		handler  = new Handler();
		
		runnable = new Runnable() {
			int i=0;
			public void run() {

				ImageViewAnimatedChange(context,imageview,imageArray[i]);
				i++;
				if(i>imageArray.length-1)
				{
					i=0;    
				}
				handler.postDelayed(this, 5000);  //for interval...
			}
		};
		handler.postDelayed(runnable, 50); //for initial delay..
	}
	
	long wait;
	@Override
	public void onBackPressed()
	{
		if(wait + 3000 > System.currentTimeMillis()){
			finish();
		}else{
			Toast.makeText(getBaseContext(), "Press Back Once again to Exit", 2000).show();
		}
		
		wait = System.currentTimeMillis();
	}

	public void about_us_PopUp() {
		
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View layout = layoutInflater.inflate(R.layout.about_us, null);
		
		Button ok = (Button)layout.findViewById(R.id.ok);
		
		ImageView abhi_fb=(ImageView)layout.findViewById(R.id.abhi_fb);
		ImageView chintu_fb=(ImageView)layout.findViewById(R.id.chintu_fb);
		
		final Dialog pWindow = new Dialog(context,R.style.PopUpTheme);
		
		pWindow.setContentView(layout);
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		
		lp.copyFrom(pWindow.getWindow().getAttributes());
		
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		
		pWindow.getWindow().setAttributes(lp);
		pWindow.setCanceledOnTouchOutside(false);
		pWindow.show();
		
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pWindow.dismiss();
			}
		});
		
		abhi_fb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("https://www.facebook.com/abhijeet.kokane.7")));
			}
		});
		
		chintu_fb.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("https://www.facebook.com/sumeet.sable1")));
			}
		});
	}

	public void help_PopUp() {
		
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View layout = layoutInflater.inflate(R.layout.help, null);
		
		Button ok = (Button)layout.findViewById(R.id.okhelp);
		
		final Dialog pWindow = new Dialog(context,R.style.PopUpTheme);
		
		pWindow.setContentView(layout);
		
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		
		lp.copyFrom(pWindow.getWindow().getAttributes());
		
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		
		pWindow.getWindow().setAttributes(lp);
		pWindow.setCanceledOnTouchOutside(false);
		pWindow.show();
		
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pWindow.dismiss();
			}
		});
	}
	
	public static void ImageViewAnimatedChange(Context c, final ImageView v, final int new_image) {
	    final Animation anim_out = AnimationUtils.loadAnimation(c, android.R.anim.fade_out); 
	    final Animation anim_in  = AnimationUtils.loadAnimation(c, android.R.anim.fade_in); 
	    anim_out.setAnimationListener(new AnimationListener()
	    {
	        @Override public void onAnimationStart(Animation animation) {}
	        @Override public void onAnimationRepeat(Animation animation) {}
	        @Override public void onAnimationEnd(Animation animation)
	        {
	            v.setImageResource(new_image);
	            anim_in.setAnimationListener(new AnimationListener() {
	                @Override public void onAnimationStart(Animation animation) {}
	                @Override public void onAnimationRepeat(Animation animation) {}
	                @Override public void onAnimationEnd(Animation animation) {}
	            });
	            v.startAnimation(anim_in);
	        }
	    });
	    v.startAnimation(anim_out);
	}
}
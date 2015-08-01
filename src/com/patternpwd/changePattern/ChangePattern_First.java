package com.patternpwd.changePattern;

import android.annotation.SuppressLint;
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

@SuppressLint("ValidFragment")
public class ChangePattern_First extends SherlockFragment {//implements OnTouchListener {
	
	Context context;
	public static ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
	public ChangePattern_First(Context context) {
			this.context=context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
	View rootView = inflater.inflate(R.layout.draw_pattern, container, false);
	
	Button submit=(Button)rootView.findViewById(R.id.d_confirm);
	Button clear=(Button)rootView.findViewById(R.id.d_cancel);
	Button createAccount = (Button)rootView.findViewById(R.id.cpattern_register);
	
	img1=(ImageView)rootView.findViewById(R.id.imageView1);
	img2=(ImageView)rootView.findViewById(R.id.imageView2);
	img3=(ImageView)rootView.findViewById(R.id.imageView3);
	img4=(ImageView)rootView.findViewById(R.id.imageView4);
	img5=(ImageView)rootView.findViewById(R.id.imageView5);
	img6=(ImageView)rootView.findViewById(R.id.imageView6);
	img7=(ImageView)rootView.findViewById(R.id.imageView7);
	img8=(ImageView)rootView.findViewById(R.id.imageView8);
	img9=(ImageView)rootView.findViewById(R.id.imageView9);
	
	
	submit.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			ChangePattern.viewPager2.setCurrentItem(2);
		}
	});
	
	createAccount.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			ChangePattern.viewPager2.setCurrentItem(0);
		}
	});
	
	
	clear.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			ChangePattern.patternFirst.clear();
					
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
			
			ChangePattern.patternFirst.add("1");
			
			return true;
		}
	});
	
	img2.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			ChangePattern.patternFirst.add("2");
			
			return true;
		}
	});
	
	img3.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			ChangePattern.patternFirst.add("3");
			
			return true;
		}
	});
	
	img4.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			ChangePattern.patternFirst.add("4");
			
			return true;
		}
	});
	
	img5.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			ChangePattern.patternFirst.add("5");
			
			return true;
		}
	});
	
	img6.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			ChangePattern.patternFirst.add("6");
			
			return true;
		}
	});
	
	img7.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			ChangePattern.patternFirst.add("7");
			
			return true;
		}
	});
	
	img8.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			ChangePattern.patternFirst.add("8");
			
			return true;
		}
	});
	
	img9.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			ChangePattern.patternFirst.add("9");
			
			return true;
		}
	});
	
	
	submit.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(ChangePattern.patternFirst.size() > 3){
			//	Toast.makeText(context, ChangePattern.patternFirst.toString(), Toast.LENGTH_SHORT).show();
				ChangePattern.viewPager2.setCurrentItem(2);
			}else{
				Toast.makeText(context,"More Than 3 dots must be selected", Toast.LENGTH_LONG).show();
			}
		}
	});
	return rootView;
	}
 
//  public boolean onTouch(View v, MotionEvent event) {
//    int action = event.getAction();
//    switch (action) {
//    case MotionEvent.ACTION_DOWN:
//      downx = event.getX();
//      downy = event.getY();
//      break;
//    case MotionEvent.ACTION_MOVE:
//      break;
//    case MotionEvent.ACTION_UP:
//      upx = event.getX();
//      upy = event.getY();
//      canvas.drawLine(downx, downy, upx, upy, paint);
//      imageView.invalidate();
//      break;
//    case MotionEvent.ACTION_CANCEL:
//      break;
//    default:
//      break;
//    }
//    return true;
//  }
}
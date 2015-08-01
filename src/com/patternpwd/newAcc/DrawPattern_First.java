package com.patternpwd.newAcc;

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
public class DrawPattern_First extends SherlockFragment {//implements OnTouchListener {
	
	Context context;
	
	public DrawPattern_First(Context context) {
			this.context=context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	RegisterActivity.patternFirst.clear();
	
	View rootView = inflater.inflate(R.layout.draw_pattern, container, false);
	
	Button submit=(Button)rootView.findViewById(R.id.d_confirm);
	Button clear=(Button)rootView.findViewById(R.id.d_cancel);
	Button createAccount = (Button)rootView.findViewById(R.id.cpattern_register);
	
	final ImageView img1=(ImageView)rootView.findViewById(R.id.imageView1);
	final ImageView img2=(ImageView)rootView.findViewById(R.id.imageView2);
	final ImageView img3=(ImageView)rootView.findViewById(R.id.imageView3);
	final ImageView img4=(ImageView)rootView.findViewById(R.id.imageView4);
	final ImageView img5=(ImageView)rootView.findViewById(R.id.imageView5);
	final ImageView img6=(ImageView)rootView.findViewById(R.id.imageView6);
	final ImageView img7=(ImageView)rootView.findViewById(R.id.imageView7);
	final ImageView img8=(ImageView)rootView.findViewById(R.id.imageView8);
	final ImageView img9=(ImageView)rootView.findViewById(R.id.imageView9);
	
	/*imageView = (ImageView) rootView.findViewById(R.id.mainImageView);
	 
    Display currentDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
    
    float dw = currentDisplay.getWidth();
    float dh = currentDisplay.getHeight();
 
    bitmap = Bitmap.createBitmap((int) dw, (int) dh,
        Bitmap.Config.ARGB_8888);
    canvas = new Canvas(bitmap);
    paint = new Paint();
    paint.setColor(Color.GREEN);
    imageView.setImageBitmap(bitmap);
 
    imageView.setOnTouchListener(this);*/
	
	createAccount.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			RegisterActivity.viewPager.setCurrentItem(0);
		}
	});
	
	
	clear.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			RegisterActivity.patternFirst.clear();
					
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
			
			RegisterActivity.patternFirst.add("1");
			
			return true;
		}
	});
	
	img2.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			RegisterActivity.patternFirst.add("2");
			
			return true;
		}
	});
	
	img3.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			RegisterActivity.patternFirst.add("3");
			
			return true;
		}
	});
	
	img4.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			RegisterActivity.patternFirst.add("4");
			
			return true;
		}
	});
	
	img5.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			RegisterActivity.patternFirst.add("5");
			
			return true;
		}
	});
	
	img6.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			RegisterActivity.patternFirst.add("6");
			
			return true;
		}
	});
	
	img7.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			RegisterActivity.patternFirst.add("7");
			
			return true;
		}
	});
	
	img8.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			RegisterActivity.patternFirst.add("8");
			
			return true;
		}
	});
	
	img9.setOnTouchListener(new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			v.setEnabled(false);
			
			RegisterActivity.patternFirst.add("9");
			
			return true;
		}
	});
	
	
	submit.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(RegisterActivity.patternFirst.size() > 3){
			//	Toast.makeText(context, RegisterActivity.patternFirst.toString(), Toast.LENGTH_SHORT).show();
				RegisterActivity.viewPager.setCurrentItem(2);
			}else{
				Toast.makeText(context,"More than 3 dots must be selected", Toast.LENGTH_LONG).show();
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
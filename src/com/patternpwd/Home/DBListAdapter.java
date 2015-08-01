package com.patternpwd.Home;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ppa.R;
import com.patternpwd.SecureDB.DatabaseHandler;
import com.patternpwd.SecureDB.UserRegister;

	public class DBListAdapter  extends ArrayAdapter<String>{

		final Context context;
		final ArrayList<String> values1,values2,values3;
		DatabaseHandler db;
		List<UserRegister> allUser = null;
		
		public DBListAdapter(Context context, int resource, ArrayList<String> values1
				,ArrayList<String> values2,ArrayList<String> values3) {
			super(context, resource, values1);
			
			this.values1 = values1;
			this.values2 = values2;
			this.values3 = values3;
			this.context = context;
		}

		public View getView(final int position, View convertView, ViewGroup parent){
			
			LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView = inflater.inflate(R.layout.two_linelist, parent, false);
		    
			TextView dbtext1 = (TextView) convertView.findViewById(R.id.textView1);
			EditText dbtext2 = (EditText) convertView.findViewById(R.id.editText1);
			ImageView delete = (ImageView)convertView.findViewById(R.id.imageView1);
			
			RelativeLayout relaLayout = (RelativeLayout)convertView.findViewById(R.id.RelativeLayout01);
			TextView description = (TextView) convertView.findViewById(R.id.TextView01);
			
			relaLayout.setVisibility(RelativeLayout.GONE);
			
			dbtext1.setText(values1.get(position)+"   "+values2.get(position));
			
			dbtext2.setEnabled(false);
			
			dbtext2.setText(values3.get(position));
			
			/*
			 * INSTEAD OF CREATING NEW ADAPTER I INVISIBLE DELETE IMAGE
			 * CHANGE TYPE OF PATTERNTEXT TO NORMAL
			 * I CHECK THIS ACCORDING TO SECOND LIST INPUT AS blank space " "
			 */
			
			if(values2.get(position).equals(" ")){
				relaLayout.setVisibility(RelativeLayout.VISIBLE);//visible text_view layout
				
				description.setText(values3.get(position));
				
				delete.setVisibility(ImageView.GONE);//hide delete
				dbtext2.setVisibility(EditText.GONE);//hide edit_text
			}
			
			delete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					final AlertDialog.Builder confirmation = new AlertDialog.Builder(context);
					
					confirmation.setTitle("Delete This User ?");
					
					confirmation.setNegativeButton("No", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
					
					confirmation.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							db = new DatabaseHandler(context);
							db.deleteUser(new UserRegister(values2.get(position))); //_emailId
							
							Toast.makeText(context, values1.get(position)+" Deleted", Toast.LENGTH_SHORT).show();
							
							allUser = db.getAllUsers();
							
							SettingActivity.dataList1.clear();
							SettingActivity.dataList2.clear();
							SettingActivity.dataList3.clear();
							
							for(UserRegister singleuser : allUser){	//get single record from DB and adds in list

								SettingActivity.dataList1.add(singleuser.getFName());
								SettingActivity.dataList2.add(singleuser.getEmail());
								SettingActivity.dataList3.add(singleuser.getPattern());
								
								SettingActivity.dataAdapter.notifyDataSetChanged();
							}
						}
					});
					
					confirmation.show();
				}
			});
			
			return convertView;
		}		
}
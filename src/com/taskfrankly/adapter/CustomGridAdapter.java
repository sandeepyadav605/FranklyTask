package com.taskfrankly.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.taskfrankly.R;

public class CustomGridAdapter extends BaseAdapter {

	private Context context;
	private final ArrayList<Integer> gridValues;
	private LayoutInflater inflater ;

	//Constructor to initialize values
	public CustomGridAdapter(Context context,ArrayList<Integer> gridValues) {
		this.context        = context;
		this.gridValues     = gridValues;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// Number of times getView method call depends upon gridValues.size
		return gridValues.size();
	}

	@Override
	public Object getItem(int position) {
		return gridValues.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}


	// Number of times getView method call depends upon gridValues.length

	public View getView(int position, View convertView, ViewGroup parent) {
		// LayoutInflator to call external grid_item.xml file
		View gridView;
		if (convertView == null) {
			// get layout from grid_item.xml ( Defined Below )
			gridView = inflater.inflate( R.layout.grid_item , null);

			// set value into textview
			TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);

			int value=gridValues.get(position);
			if(value !=0){
				textView.setText(""+value);
			}




		} else {

			gridView = (View) convertView;
		}
		return gridView;
	}
}
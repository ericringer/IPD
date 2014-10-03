package com.ericringer.sfindiefest.types;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ericringer.sfindiefest.R;

public class SimpleAdapter extends ArrayAdapter<String> {

	/**
	 * The Class ViewHolder.
	 */
	public static class ViewHolder{
		TextView txtComment;

	}
	private List<String> items;
	private Context mContext;
	private LayoutInflater inflater;

	public SimpleAdapter(Context context, List<String> objects) {
		super(context, R.layout.row_comment, objects);
		items = objects;
		mContext = context;
		inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if(rowView == null){
			rowView= inflater.inflate(R.layout.row_comment, parent, false);
			ViewHolder h = new ViewHolder();
			h.txtComment=(TextView)rowView.findViewById(R.id.txtComment);
			rowView.setTag(h);
		}
		ViewHolder holder = (ViewHolder)rowView.getTag();
		holder.txtComment.setText(items.get(position));
		return rowView;


	}
}
package com.ericringer.sfindiefest.types;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ericringer.sfindiefest.R;

public class NameSearchAdapter extends ArrayAdapter<KnownFor> {

	/**
	 * The Class ViewHolder.
	 */
	public static class ViewHolder{
		TextView txtTitle;

	}
	private List<KnownFor> items;
	private Context mContext;
	private LayoutInflater inflater;

	public NameSearchAdapter(Context context, List<KnownFor> objects) {
		super(context, R.layout.row_known_for, objects);
		items = objects;
		mContext = context;
		inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if(rowView == null){
			rowView= inflater.inflate(R.layout.row_known_for, parent, false);
			ViewHolder h = new ViewHolder();
			h.txtTitle=(TextView)rowView.findViewById(R.id.txtTitle);
			rowView.setTag(h);
		}
		ViewHolder holder = (ViewHolder)rowView.getTag();
		KnownFor knownFor = items.get(position);
		holder.txtTitle.setText(knownFor.getTitle());
		return rowView;


	}
}
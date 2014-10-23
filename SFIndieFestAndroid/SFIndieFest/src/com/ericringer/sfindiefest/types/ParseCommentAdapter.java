package com.ericringer.sfindiefest.types;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ericringer.sfindiefest.R;
import com.parse.ParseObject;

public class ParseCommentAdapter extends ArrayAdapter<ParseObject> {

	public static class ViewHolder{
		TextView txtTitle;
		TextView txtComment;
	}
	
	private List<ParseObject> items;
	private Context mContext;
	private LayoutInflater inflater;

	public ParseCommentAdapter(Context context, List<ParseObject> objects) {
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
			h.txtTitle=(TextView)rowView.findViewById(R.id.txtTitle);
			h.txtComment=(TextView)rowView.findViewById(R.id.txtComment);
			rowView.setTag(h);
		}
		ViewHolder holder = (ViewHolder)rowView.getTag();
		
		ParseObject comment = items.get(position);
		holder.txtTitle.setText(comment.getString("filmTitle"));
		holder.txtComment.setText(comment.getString("filmComment"));
		return rowView;


	}
}
package com.ericringer.sfindiefest.types;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ericringer.sfindiefest.R;

public class FilmAdapter extends ArrayAdapter<Film> {

	/**
	 * The Class ViewHolder.
	 */
	public static class ViewHolder{
		ImageView imgFilm;
		TextView txtFilmTitle;

	}
	private List<Film> items;
	private Context mContext;
	private LayoutInflater inflater;

	public FilmAdapter(Context context, List<Film> objects) {
		super(context, R.layout.row_film, objects);
		items = objects;
		mContext = context;
		inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if(rowView == null){
			rowView= inflater.inflate(R.layout.row_film, parent, false);
			ViewHolder h = new ViewHolder();
			h.txtFilmTitle=(TextView)rowView.findViewById(R.id.txtFilmTitle);
			h.imgFilm=(ImageView)rowView.findViewById(R.id.imgFilm);
			rowView.setTag(h);
		}
		ViewHolder holder = (ViewHolder)rowView.getTag();
		Film film = items.get(position);
		holder.txtFilmTitle.setText(film.getFilmTitle());
		holder.imgFilm.setImageResource(film.getFilmImageID());
		return rowView;


	}
}
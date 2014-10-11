package com.ericringer.sfindiefest.types;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ericringer.sfindiefest.R;

public class ScheduleAdapter extends ArrayAdapter<Film> {

	/**
	 * The Class ViewHolder.
	 */
	public static class ViewHolder{
		TextView txtFilmTitle;
		TextView txtFilmTime;
		ToggleButton toggleReminder;
	}
	private List<Film> items;
	private Context mContext;
	private LayoutInflater inflater;

	public ScheduleAdapter(Context context, List<Film> objects) {
		super(context, R.layout.row_schedule, objects);
		items = objects;
		mContext = context;
		inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if(rowView == null){
			rowView= inflater.inflate(R.layout.row_schedule, parent, false);
			ViewHolder h = new ViewHolder();
			h.txtFilmTitle=(TextView)rowView.findViewById(R.id.txtFilmTitle);
			h.txtFilmTime=(TextView)rowView.findViewById(R.id.txtFilmTime);
			h.toggleReminder = (ToggleButton)rowView.findViewById(R.id.toggleReminder);
			rowView.setTag(h);
		}
		ViewHolder holder = (ViewHolder)rowView.getTag();
		final Film film = items.get(position);
		holder.txtFilmTitle.setText(film.getFilmTitle());
		holder.txtFilmTime.setText(film.getDateTime());
		holder.toggleReminder.setOnCheckedChangeListener(null);
		holder.toggleReminder.setChecked(film.isReminderSet(mContext));
		holder.toggleReminder.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton button, boolean checked) {
				if(checked){
					addReminder(film);
				}else{
					removeReminder(film);
				}
			}});
		return rowView;
	}
	public void addReminder(Film film) {
		Calendar cal = Calendar.getInstance();
	
		cal.setTime(film.getConvertedDateTime());
		long start = cal.getTimeInMillis();
	
		cal.add(Calendar.HOUR, 1);
		long end = cal.getTimeInMillis();
	
		ContentResolver cr = mContext.getContentResolver();
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, start);
		values.put(Events.DTEND, end);
		values.put(Events.TITLE, film.getFilmTitle());
		values.put(Events.DESCRIPTION, film.getSynopsis());
		values.put(Events.CALENDAR_ID, 1);
		values.put(Events.HAS_ALARM, true);
	
		values.put(Events.EVENT_TIMEZONE,TimeZone.getDefault().getID());
		Uri uri = cr.insert(Events.CONTENT_URI, values);
	
		long eventID = Long.parseLong(uri.getLastPathSegment());
		film.setEventID(mContext,eventID);
		notifyDataSetChanged();
		
		if(eventID < 0)return;
		ContentValues reminders = new ContentValues();
		reminders.put(Reminders.EVENT_ID, eventID);
		reminders.put(Reminders.METHOD, Reminders.METHOD_ALERT);
		reminders.put(Reminders.MINUTES, 5);
		cr.insert(Reminders.CONTENT_URI, reminders);
	}
	private void removeReminder(Film film){
		long eventID = film.getEventID(mContext);
		if(eventID > 0){
			Uri uri = ContentUris.withAppendedId(Events.CONTENT_URI, eventID);
			mContext.getContentResolver().delete(uri, null, null);
			film.setEventID(mContext, -1);
		}
		notifyDataSetChanged();
	}
}
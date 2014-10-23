package com.ericringer.sfindiefest.fragments;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ericringer.sfindiefest.BuyTicketsActivity;
import com.ericringer.sfindiefest.FilmLocationActivity;
import com.ericringer.sfindiefest.FilmTrailerActivity;
import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.Film;
import com.parse.ParseInstallation;
import com.parse.ParseObject;

public class FilmDetailFragment extends BaseFragment {
	private Film mFilm;

	public FilmDetailFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return prepareAndSetView(inflater.inflate(R.layout.fragment_film_details,container, false));
	}

	@Override
	public void prepareLayout() {
		TextView txtSynopsis = (TextView)findViewById(R.id.txtSynopsis);
		txtSynopsis.setText(mFilm.getSynopsis());
		TextView txtDateLocation = (TextView)findViewById(R.id.txtDateLocation);
		txtDateLocation.setText(mFilm.getDateTime());
		ImageView imgFilm = (ImageView)findViewById(R.id.imgFilm);
		imgFilm.setImageResource(mFilm.getFilmImageID());
		findViewById(R.id.btnTrailer).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),FilmTrailerActivity.class);
				i.putExtra(Film.TRAILER_URL, mFilm.getTrailerUrl());
				startActivity(i);
			}});
		findViewById(R.id.btnBuyTickets).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),BuyTicketsActivity.class);
				i.putExtra(Film.PURCHASE_URL, mFilm.getPurchaseUrl());
				startActivity(i);
			}});
		findViewById(R.id.imgFilm).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),FilmLocationActivity.class);
				i.putExtra(Film.FILM, mFilm);
				startActivity(i);
			}});
		findViewById(R.id.btnTheaterMap).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),FilmLocationActivity.class);
				i.putExtra(Film.FILM, mFilm);
				startActivity(i);
			}});
		findViewById(R.id.btnPostComment).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				postCommentDialog();
			}});
		ToggleButton toggleReminder = (ToggleButton)findViewById(R.id.toggleReminder);
		toggleReminder.setChecked(mFilm.isReminderSet(getApplicationContext()));
		toggleReminder.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton button, boolean checked) {
				if(checked){
					addReminder(mFilm);
				}else{
					removeReminder(mFilm);
				}
			}});
	}
		public void addReminder(Film film) {
			Calendar cal = Calendar.getInstance();
		
			cal.setTime(film.getConvertedDateTime());
			cal.set(Calendar.HOUR, 9);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.AM_PM, Calendar.AM);
			long start = cal.getTimeInMillis();
		
			cal.add(Calendar.HOUR, 1);
			long end = cal.getTimeInMillis();
		
			ContentResolver cr = getApplicationContext().getContentResolver();
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
			film.setEventID(getApplicationContext(),eventID);
			
			if(eventID < 0)return;
			ContentValues reminders = new ContentValues();
			reminders.put(Reminders.EVENT_ID, eventID);
			reminders.put(Reminders.METHOD, Reminders.METHOD_ALERT);
			reminders.put(Reminders.MINUTES, 5);
			cr.insert(Reminders.CONTENT_URI, reminders);
		}
		private void removeReminder(Film film){
			long eventID = film.getEventID(getApplicationContext());
			if(eventID > 0){
				Uri uri = ContentUris.withAppendedId(Events.CONTENT_URI, eventID);
				getApplicationContext().getContentResolver().delete(uri, null, null);
				film.setEventID(getApplicationContext(), -1);
			}
		}
	protected void postCommentDialog() {
		final EditText txtComment = new EditText(getActivity());
		txtComment.setHint("Comment");
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Enter Comment");
		builder.setView(txtComment);
		builder.setPositiveButton("OK", new AlertDialog.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(TextUtils.isEmpty(txtComment.getText()))return;
				
				postComment(txtComment.getText().toString());
			}});
		builder.create().show();
	}

	protected void postComment(String comment) {
		ParseObject filmComment = new ParseObject("filmComments");
		filmComment.put("filmTitle", mFilm.getFilmTitle());
		filmComment.put("filmComment", comment);
		
		filmComment.put("userID", ParseInstallation.getCurrentInstallation().getObjectId());
		filmComment.saveInBackground();
	}

	public String getTitle(){
		if(mFilm == null)return "";
		return mFilm.getFilmTitle();
	}
	public static Fragment getInstance(Film film,FragmentCallback callback) {
		FilmDetailFragment frag = new FilmDetailFragment();
		frag.setFilm(film);
		frag.setCallback(callback);
		return frag;
	}

	private void setFilm(Film film) {
		mFilm = film;
	}

}

package com.ericringer.sfindiefest.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ericringer.sfindiefest.BuyTicketsActivity;
import com.ericringer.sfindiefest.FilmLocationActivity;
import com.ericringer.sfindiefest.FilmTrailerActivity;
import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.Film;
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
	}
	protected void postCommentDialog() {
		final EditText txtComment = new EditText(getActivity());
		txtComment.setHint("Comment");
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Enter Comment");
		builder.setView(txtComment);
		builder.setPositiveButton("Share", new AlertDialog.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(TextUtils.isEmpty(txtComment.getText()))return;
				
				postComment(txtComment.getText().toString());
			}});
		builder.setNegativeButton("OK", null);
		builder.create().show();
	}

	protected void postComment(String comment) {
		ParseObject gameScore = new ParseObject("filmComments");
		gameScore.put("filmTitle", mFilm.getFilmTitle());
		gameScore.put("filmComment", comment);
		gameScore.saveInBackground();
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

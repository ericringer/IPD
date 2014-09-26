package com.ericringer.sfindiefest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.ericringer.sfindiefest.BuyTicketsActivity;
import com.ericringer.sfindiefest.FilmLocationActivity;
import com.ericringer.sfindiefest.FilmTrailerActivity;
import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.Film;

public class FilmDetailFragment extends BaseFragment {
	private Film mFilm;

	public FilmDetailFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return prepareAndSetView(inflater.inflate(R.layout.fragment_film_details,container, false));
	}

	@Override
	public void prepareLayout() {
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

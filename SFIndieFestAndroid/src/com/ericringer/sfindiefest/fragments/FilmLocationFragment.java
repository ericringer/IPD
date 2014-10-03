package com.ericringer.sfindiefest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.Film;

public class FilmLocationFragment extends BaseFragment {
	private Film mFilm;

	public FilmLocationFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return prepareAndSetView(inflater.inflate(R.layout.fragment_film_location,container, false));
	}

	@Override
	public void prepareLayout() {


	}
	
	public String getTitle(){
		return mFilm.getFilmTitle();
	}
	
	public static Fragment getInstance(Film film,FragmentCallback callback) {
		FilmLocationFragment frag = new FilmLocationFragment();
		frag.setFilm(film);
		frag.setCallback(callback);
		return frag;
	}

	private void setFilm(Film film) {
		mFilm = film;
	}

}

package com.ericringer.sfindiefest.fragments;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ericringer.sfindiefest.FilmDetailActivity;
import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.Film;
import com.ericringer.sfindiefest.types.FilmAdapter;

public class FilmsFragment extends BaseFragment {
	private List<Film> films;

	public FilmsFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return prepareAndSetView(inflater.inflate(R.layout.fragment_films,container, false));
	}

	@Override
	public void prepareLayout() {
		films = Film.getStaticFilms();
		ListView listFilms = (ListView)findViewById(R.id.listFilms);
		listFilms.setAdapter(new FilmAdapter(getActivity(),films));
		listFilms.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3) {
				//callback.switchFragment(FilmDetailFragment.getInstance(films.get(position), callback));
				Intent i = new Intent(getActivity(),FilmDetailActivity.class);
				i.putExtra(Film.FILM, films.get(position));
				getActivity().startActivity(i);
			}});
		
	}
	public String getTitle(){
		return getString(R.string.films);
	}
	public static Fragment getInstance(FragmentCallback callback) {
		FilmsFragment frag = new FilmsFragment();
		frag.setCallback(callback);
		return frag;
	}

}

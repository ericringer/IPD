package com.ericringer.sfindiefest.fragments;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.Film;
import com.ericringer.sfindiefest.types.ScheduleAdapter;

public class ScheduleFragment extends BaseFragment {
	private List<Film> films;

	public ScheduleFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return prepareAndSetView(inflater.inflate(R.layout.fragment_schedule,container, false));
	}

	@Override
	public void prepareLayout() {
		films = Film.getStaticFilms();
		
		  Collections.sort(films, new Comparator<Film>() {
		        @Override
		        public int compare(Film f1, Film f2) {
		            return f1.getDateTime().compareTo(f2.getDateTime());
		        }
		    });
		
		ListView listFilms = (ListView)findViewById(R.id.listFilms);
		listFilms.setAdapter(new ScheduleAdapter(getActivity(),films));
	}
	
	public String getTitle(){
		return getString(R.string.search);
	}
	
	public static Fragment getInstance(FragmentCallback callback) {
		ScheduleFragment frag = new ScheduleFragment();
		frag.setCallback(callback);
		return frag;
	}

}

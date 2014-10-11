package com.ericringer.sfindiefest.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.ericringer.sfindiefest.R;
import com.ericringer.sfindiefest.types.Film;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FilmLocationFragment extends BaseFragment {
	private Film mFilm;
	public static final LatLng THEATER_LAT_LNG = new LatLng(37.785756,-122.430597);

    
	public FilmLocationFragment() {}

	private static View view;
	private static GoogleMap map;
	private String TAG = getClass().getSimpleName();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		   createView(inflater);
		    try {
		    	SupportMapFragment supFrag = (SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.theaterMap);
		    	if(supFrag == null){
		    		view = null;
		    		return onCreateView(inflater,container,savedInstanceState);
		    	}
			    map = supFrag.getMap();
			    if(map == null) {
			    	view = null;
			    	return onCreateView(inflater,container,savedInstanceState);
			    }

		    } catch (InflateException e) {
		    	Log.e(TAG, "Couldn't inflate",e);
		    }
			prepareAndSetView(view);
		return view;
	}

	private void createView(LayoutInflater inflater) {
		Log.i(TAG, "In createView");
		if (view != null) {
		        ViewGroup parent = (ViewGroup) view.getParent();
		        if (parent != null)
		            parent.removeView(view);
		    }else{
		    	view = inflater.inflate(R.layout.fragment_film_location,null);
		    }
		prepareAndSetView(view);
	}

	@Override
	protected void prepareLayout() {
		    if(map == null)map = ((SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.theaterMap))
		            .getMap();
		    try{
		    	map.clear();
				Marker theaterMarker = map.addMarker(new MarkerOptions().position(THEATER_LAT_LNG)
					 	 		.title("New People Cinema")
					 	 		.snippet("1746 Post St, San Francisco, CA 94115"));
				theaterMarker.showInfoWindow();
		    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(THEATER_LAT_LNG, 14));
		    	map.setMyLocationEnabled(true);
		    	
		 		// Zoom in, animating the camera.
		 		map.animateCamera(CameraUpdateFactory.zoomTo(14), 3000, null);
		 		
		 		findViewById(R.id.btnDirections).setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
							    Uri.parse("http://maps.google.com/maps?daddr=" + THEATER_LAT_LNG.latitude + "," + THEATER_LAT_LNG.longitude + "(New People Cinema)"));
						startActivity(intent);
					}});
		    }catch(Exception e){
		    	Log.e(TAG, "Error Adding Theater Location",e);
		    }
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

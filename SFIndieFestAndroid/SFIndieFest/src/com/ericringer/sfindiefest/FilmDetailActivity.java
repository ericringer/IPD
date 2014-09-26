package com.ericringer.sfindiefest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.ericringer.sfindiefest.fragments.FilmDetailFragment;
import com.ericringer.sfindiefest.types.Film;

public class FilmDetailActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_film_detail);
		prepareLayout();
	}

	private void prepareLayout() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Film film = (Film) getIntent().getExtras().getSerializable(Film.FILM);
		FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
		trans.add(R.id.fragFilmDetail, FilmDetailFragment.getInstance(film, null), "fragFilmDetail");
		trans.commit();		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
	        //NavUtils.navigateUpFromSameTask(this);
	    	onBackPressed();
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}

}

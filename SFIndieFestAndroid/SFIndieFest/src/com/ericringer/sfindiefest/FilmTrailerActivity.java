package com.ericringer.sfindiefest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ericringer.sfindiefest.types.Film;

@SuppressLint("SetJavaScriptEnabled")
public class FilmTrailerActivity extends Activity {
	private String trailerUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_film_trailer);
		
		trailerUrl = getIntent().getExtras().getString(Film.TRAILER_URL, "");
		if(TextUtils.isEmpty(trailerUrl)){
	    	finish();
			return;
		}
		
		prepareLayout();
	}

	private void prepareLayout() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		WebView webTrailer = (WebView)findViewById(R.id.webTrailer);
		webTrailer.getSettings().setJavaScriptEnabled(true);
		webTrailer.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
			
			
		});
		webTrailer.loadUrl(trailerUrl);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
	    	onBackPressed();
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
}

package com.ericringer.sfindiefest.types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class NameSearch {
	public static final String API_URL = "http://imdb.wemakesites.net/api/1.0/get/name/?q=";
	public String TAG = NameSearch.class.getSimpleName();
	
	public void performSearch(String name,NameSearchListener listener){
		NameSearchTask searchTask = new NameSearchTask();
		searchTask.setListener(listener);
		searchTask.execute(name);
	}
	
	private class NameSearchTask extends AsyncTask<String,Void,NameSearchResult>{
		private NameSearchListener listener;
		@Override
		protected NameSearchResult doInBackground(String... search) {
			String searchName = search[0];
			
			try {
				searchName = URLEncoder.encode(searchName, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String jsonData = performGet(NameSearch.API_URL + searchName);
			NameSearchResult result = new NameSearchResult(jsonData);
			return result;
		}
		
		@Override
		protected void onPostExecute(NameSearchResult result) {
			if(listener!=null)listener.onResultRecieved(result);
			super.onPostExecute(result);
		}

		private String performGet(String url){
			InputStream inputStream = null;
			String result = "";
			try {

				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
				inputStream = httpResponse.getEntity().getContent();
				if(inputStream != null){
					result = readResult(inputStream);
					Log.d(TAG,"Result: " + result);
				}else{
					result = "{}";
				}

			} catch (Exception e) {
				Log.e(TAG, "Error performing get",e);
			}

			return result;
		}

		private String readResult(InputStream inputStream) {
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
			String line = "";
			String result = "";
			try{
				while((line = bufferedReader.readLine()) != null){
					result += line;
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return result;

		}
		public void setListener(NameSearchListener listener) {
			this.listener = listener;
		}
	}
	
	public interface NameSearchListener{
		void onResultRecieved(NameSearchResult result);
	}
}

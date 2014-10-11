
package com.ericringer.sfindiefest.types;

import org.json.JSONException;
import org.json.JSONObject;


public class KnownFor{
   	private static final String TITLE = "title";
	private static final String URL = "url";
	
	private String title;
   	private String url;

 	public KnownFor(JSONObject jsonObject) {
			try {
				if(jsonObject.has(TITLE))title = jsonObject.getString(TITLE);
				if(jsonObject.has(URL))url = jsonObject.getString(URL);
			} catch (JSONException e) {
				e.printStackTrace();
			}
	}
	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
}

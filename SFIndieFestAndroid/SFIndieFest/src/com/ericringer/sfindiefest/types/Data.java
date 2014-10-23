
package com.ericringer.sfindiefest.types;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Data{
   	private static final String BIO = "bio";
	private static final String BIRTHDATE = "birthDate";
	private static final String IMDBENDPOINT = "imdbEndPoint";
	private static final String IMDBURL = "imdbUrl";
	private static final String NAME = "name";
	private static final String PHOTO = "photo";
	private static final String KNOWNFOR = "knownFor";
   	
	private String bio;
   	private String birthDate;
   	private String imdbEndPoint;
   	private String imdbUrl;
   	private List<KnownFor> knownFor;
   	private String name;
   	private String photo;
   	public Data(){
   		knownFor = new ArrayList<KnownFor>();
   	}
 	public Data(JSONObject jsonObject) {
			try {
				if(jsonObject.has(BIO))bio = jsonObject.getString(BIO);
				if(jsonObject.has(BIRTHDATE))birthDate = jsonObject.getString(BIRTHDATE);
				if(jsonObject.has(IMDBENDPOINT))imdbEndPoint = jsonObject.getString(IMDBENDPOINT);
				if(jsonObject.has(IMDBURL))imdbUrl = jsonObject.getString(IMDBURL);
				if(jsonObject.has(NAME))name = jsonObject.getString(NAME);
				if(jsonObject.has(PHOTO))photo = jsonObject.getString(PHOTO);
				if(!jsonObject.has(KNOWNFOR))return;
				
				knownFor = new ArrayList<KnownFor>();
				JSONArray jArray = jsonObject.getJSONArray(KNOWNFOR);
				int count = jArray.length();
				
				for(int i = 0;i<count;i++){
					knownFor.add(new KnownFor((JSONObject) jArray.get(i)));
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
 	}
	public String getBio(){
		return this.bio;
	}
	public void setBio(String bio){
		this.bio = bio;
	}
 	public String getBirthDate(){
		return this.birthDate;
	}
	public void setBirthDate(String birthDate){
		this.birthDate = birthDate;
	}
 	public String getImdbEndPoint(){
		return this.imdbEndPoint;
	}
	public void setImdbEndPoint(String imdbEndPoint){
		this.imdbEndPoint = imdbEndPoint;
	}
 	public String getImdbUrl(){
		return this.imdbUrl;
	}
	public void setImdbUrl(String imdbUrl){
		this.imdbUrl = imdbUrl;
	}
 	public List<KnownFor> getKnownFor(){
		return this.knownFor;
	}
	public void setKnownFor(List<KnownFor> knownFor){
		this.knownFor = knownFor;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getPhoto(){
		return this.photo;
	}
	public void setPhoto(String photo){
		this.photo = photo;
	}
}

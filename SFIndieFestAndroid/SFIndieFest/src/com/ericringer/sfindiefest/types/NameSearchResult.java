
package com.ericringer.sfindiefest.types;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class NameSearchResult{
   	private static final String STATUS = "status";
	private static final String DATA = "data";
	
	private Data data;
	private String status;
	private String dataMessage;
	public NameSearchResult(String jsonString){
		try {
			JSONObject json = new JSONObject(jsonString);
			fromJSON(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private void fromJSON(JSONObject json){
			try {
				if(json.has(STATUS))status = json.getString(STATUS);
				if(!isSuccess()){
					if(json.has(DATA))setDataMessage(json.getString(DATA));
					return;
				}
				
				if(json.has(DATA))data = new Data(json.getJSONObject(DATA));
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
	}
	public boolean isSuccess(){
		if(TextUtils.isEmpty(status))return false;
		if(status.contains("success"))return true;
		return false;
	}
   	public Data getData(){
   		if(data == null)data = new Data();
		return this.data;
	}
	public void setData(Data data){
		this.data = data;
	}
	public String getDataMessage() {
		return dataMessage;
	}
	public void setDataMessage(String dataMessage) {
		this.dataMessage = dataMessage;
	}
}

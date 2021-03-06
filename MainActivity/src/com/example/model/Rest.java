package com.example.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Xml.Encoding;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.parser.TokenParser;

//	To import Resting, copy the .JAR files in Alvin's Seg Dropbox 
//folder to the 'libs' folder of the chosen project. 
//Go to Project > Properties > Java Build Path > Libraries > Add external JARS
//and select all the JAR files
import com.google.resting.Resting;
import com.google.resting.component.EncodingTypes;
import com.google.resting.component.RequestParams;
import com.google.resting.component.impl.BasicRequestParams;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.method.post.PostHelper;

public class Rest extends AsyncTask<String, Void, Void>{

	private final String USERNAME = "emr";
	private final String PASSWORD = "3mrh1s";
	
	private RequestParams 		params;
	private List<Header> 		headers;
	private ServiceResponse 	response;
	
	private String 			    content;
	private String 				url;
	private String 				method;	
	private int 				port;
	private boolean 			result;
	
	
	public Rest(String method){
		params = new BasicRequestParams();
		headers = new ArrayList<Header>();
		headers.add(new BasicHeader("Authorization","Basic " + 
		Base64.encodeToString((USERNAME + ":" + PASSWORD).getBytes(),Base64.NO_WRAP)));
		port = 80;
		this.method = method;
	}
	
	//adds key : value params
	public void addRequestParams(String arg0, String arg1){
		params.add(arg0, arg1);
	}
	
	public void addToJSON(String key, String value){
		JSONObject Data = new JSONObject();
		try{			
			Data.put(key,value);
		} catch(JSONException e){
			System.out.println(e.getMessage().toString());
		}
	}
	
	/* Setter methods */
	
	public void setURL(String url){
		this.url = url;
	}

	
	/* Getter Methods */
	
	public String getURL(){
		return url;
	}
	
	public String getContent(){
		return content;
	}
	
	public ServiceResponse getResponse(){
		return response;
	}
	

	@Override
	protected Void doInBackground(String... params) {
		System.out.println(this.params);
		
		if(method == "GET"){
			try{
				response = Resting.get(url, this.params, EncodingTypes.UTF8, headers, 0);
				content = response.getResponseString();
				result = true;
	        } catch(Exception e){
				result = false;
			}
		}
		else{
			try{
				//response = PostHelper.get(url, port, EncodingTypes.UTF8, this.params,headers, null);
				//EncodingTypes.UTF8, this.params,headers, null);
				content = response.getResponseString();
				result = true;
	        } catch(Exception e){
				result = false;
			}
		}
		
		
		return null;
		
	}

	public boolean getResult(){
		return result;
	}
	


}

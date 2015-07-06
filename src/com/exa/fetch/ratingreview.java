package com.exa.fetch;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;





public class ratingreview extends ActionBarActivity {
	String resp,resp1,s,email,rview="";
	Integer flag;
	protected static String url1="your url.php";
	protected static String url2="your url.php";
	JSONArray jarray,jarray2;
	JSONObject json=new JSONObject();
	JSONObject json2=new JSONObject();
	Integer rate=0,cnt=1,urate=0;
	EditText editreview,ratingupdate;
	TextView review;
	RatingBar ratings;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ratingreview);
		 Bundle extras = getIntent().getExtras();
	        if (extras != null) {
					email =extras.getString("email");
	        }
	        System.out.println("coming 0");
	      editreview=(EditText)findViewById(R.id.editreview);
	      review=(TextView)findViewById(R.id.review);
	      ratingupdate=(EditText)findViewById(R.id.ratingupdate);
	      
	      new getreviewcall().execute(); 
	      final Button getreview = (Button) findViewById(R.id.getreview);
			getreview.setOnClickListener
				(
					new Button.OnClickListener() 
						{
							public void onClick(View v)
							{
								System.out.println("HAHAHAHAHAHA");
								new call().execute();

							}
						}
				);
			
			
		}
		catch(Exception e)
		{
			System.out.println("the error is "+e.toString());
		}
	}
	
	///////////////////////////////////////////////////////////////////post_review.php
	
	private class call extends AsyncTask<Void,Void,Void>
    {
    	 protected void onPreExecute() {
    	        Toast.makeText(getApplicationContext(), "Starting",
    	                Toast.LENGTH_LONG).show();
    	    }

		@Override
		protected Void doInBackground(Void... params) {
			 try {
 	        	HttpClient httpclient = new DefaultHttpClient();
 	            HttpPost httppost = new HttpPost("your url.php");
 	            System.out.println("hello");
 	            try {
 	            	flag=0;
 	            	 List<NameValuePair> data= new ArrayList<NameValuePair>(4);
 	            	  data.add(new BasicNameValuePair("rating",ratingupdate.getText().toString()));
 	            	  data.add(new BasicNameValuePair("review",editreview.getText().toString()));
 	            	  data.add(new BasicNameValuePair("email",email));
 	             	  data.add(new BasicNameValuePair("counter",cnt.toString()));
 	                 httppost.setEntity(new UrlEncodedFormEntity(data));
	                  System.out.println(""+data);
	                 
                     HttpResponse rs=httpclient.execute(httppost);
	        	  
 	  	            System.out.println("hello");
 
 	            	  
 	            	} 	
 	            	catch (Exception e) 
 	            		{
 	            		System.out.println("kay ahhe he");
 	            		// TODO Auto-generated catch block
 	            		e.printStackTrace();
 	            		}
 	            }
 	        	catch (Exception e) {
	            		System.out.println("kay nahi");

 	                // TODO Auto-generated catch block
 	        		e.printStackTrace();
 	            }
	    		return null;
		}
		

		  
	    protected void onPostExecute(Void void1) {
	    	  System.out.println("coming 3");
		      
	    
	      Toast.makeText(getApplicationContext(), "Review given", Toast.LENGTH_SHORT).show();
	    }

    }
   
	
	///////////////////////////////////////////////////////////////////
	
	
	
	private class getreviewcall extends AsyncTask<Void,Void,Void>
    {
    	 protected void onPreExecute() {
    	        Toast.makeText(getApplicationContext(), "Starting",
    	                Toast.LENGTH_LONG).show();
    	    }

		@Override
		protected Void doInBackground(Void... params) {
			  System.out.println("coming 1");
		      
			  HttpPost httppost = new HttpPost(url1);
			  List<NameValuePair> data= new ArrayList<NameValuePair>(1);
              data.add(new BasicNameValuePair("request",email));
             
	 	try
    	{
	 		  System.out.println("coming 2");
		      
	 		HttpClient httpclient=new DefaultHttpClient();
    		httppost.setEntity(new UrlEncodedFormEntity(data));
    		HttpResponse rs=httpclient.execute(httppost);
    		HttpGet get = new HttpGet(url2);
    		
    		System.out.println(get);
    		HttpResponse response=httpclient.execute(get);
    		System.out.println(response.toString());
    		HttpEntity entity=response.getEntity();
    		resp=EntityUtils.toString(entity);
    		System.out.println(resp);
    		
    		HttpGet get1 = new HttpGet("you url.php");
    		
       	  	HttpResponse response1=httpclient.execute(get1);
       	  	System.out.println(response1.toString());
       	  	HttpEntity entity1=response1.getEntity();
       	  	resp1=EntityUtils.toString(entity1);
             
    		System.out.println(""+resp1.toString());
    	}
    	catch(Exception e)
    	{
    		Log.e("log_tag","Error in http connection "+e.toString());
    	}
    	try
    	{
    		    	 jarray=new JSONArray(resp);
    		    	 System.out.println("why why");
    		    	 jarray2=new JSONArray(resp1);
    		    		for(int i=0;i<jarray.length();i++)
    			    	{
    			    		System.out.println(i+"time");
    					      
    			    		try {
    							json=jarray.getJSONObject(i);
    							rview=rview+"\n"+(i+1)+json.getString("review");
    							
    						} catch (JSONException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    						}
    			    	}
    		    		try {
    			    		for(int i=0;i<jarray2.length();i++)
    				    	{
    			    			System.out.println("error"+i);
    			    	    
    			    			json2=jarray2.getJSONObject(i);
    							if((json2.getString("o_email_id")).equals(email))
    							{
    							rate=Integer.parseInt(json2.getString("ratings"));
    							cnt=Integer.parseInt(json2.getString("counter"));
    							urate=Integer.parseInt(ratingupdate.getText().toString());
    							rate=((rate*cnt)+urate)/(cnt+1);
    							cnt=cnt+1;
    							System.out.println("\n"+cnt+rate+urate);
    							}
    				    	}
    			    		
    					} catch (JSONException e) {
    						System.out.println("howwwww!!!!!!!!!!!!!!11");
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    		
    		    	
    	}
    	catch(Exception e)
    	{
    		Log.e("log_tag","error parsing data"+e.toString());
    	}
    	System.out.println("i am here before return");
    		return null;
		}
		

		  
	    protected void onPostExecute(Void void1) {
	    	  System.out.println("coming 3");
		      
	    
	    	review.setText(rview);
	    
	    	
	      Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
	    }

    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}




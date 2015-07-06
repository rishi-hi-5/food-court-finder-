package com.exa.fetch;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.annotation.SuppressLint;
//import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("NewApi") public class updatemenu extends ActionBarActivity {
	
	String s;
	protected static String url="your url.php";
	String resp,acceptstring;
	Integer flag=0;
	EditText menu1,menu2,menu3,menu4,menu5,menu6,thali_rate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	try{
    		System.out.println("now creating intent for update menu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatemenu);
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }   
        System.out.println(" intent successful for update menu");

	   Bundle extras = getIntent().getExtras();
        if (extras != null) 
        {
             acceptstring = extras.getString("accept");
             System.out.println("hello"+acceptstring);
        }
        menu1=(EditText)findViewById(R.id.menu1);
        menu2=(EditText)findViewById(R.id.menu2);
        menu3=(EditText)findViewById(R.id.menu3);
        menu4=(EditText)findViewById(R.id.menu4);
        menu5=(EditText)findViewById(R.id.menu5);
     
        thali_rate=(EditText)findViewById(R.id.thali_rate);
        System.out.println("i am back");
        
        final Button save = (Button) findViewById(R.id.update);
     		save.setOnClickListener
     			(
     				new Button.OnClickListener() 
     					{
     						public void onClick(View v)
     						{
     					
     							try {
     				                Class.forName("android.os.AsyncTask");
     				            } catch (ClassNotFoundException e) {
     				                e.printStackTrace();
     				            }
     							new call().execute();
     						}
     					}
     			);     	
	    	} 
	    	catch(Exception e)
	    	{}
    }

	private class call extends AsyncTask<String,Void,Integer>
	{
		protected void onPreExecute() 
		{
    	        Toast.makeText(getApplicationContext(), "Starting",
    	                Toast.LENGTH_LONG).show();
    	}

		@Override
		protected Integer doInBackground(String... params)
		{
			
		
            	try 
            	{
            		HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(url);
            		System.out.println("why why why");
            		flag=0;
            		List<NameValuePair> data= new ArrayList<NameValuePair>(6);
            		data.add(new BasicNameValuePair("menu_item","1)"+menu1.getText().toString()+"\n2)"+menu2.getText().toString()+"\n3)"+menu3.getText().toString()+"\n4)"+menu4.getText().toString()+"\n5)"+menu5.getText().toString()));
            		data.add(new BasicNameValuePair("o_email_id",acceptstring));
            		data.add(new BasicNameValuePair("m_thali_rate",thali_rate.getText().toString()));           		
            		httppost.setEntity(new UrlEncodedFormEntity(data));
            		System.out.println(""+data);            		
            		httpclient.execute(httppost);
                  
            	} 	
            	catch (Exception e) 
        		{
        		}
    			
      		System.out.println("i am here before return");
    		return flag;
		}
		

		  
	    protected void onPostExecute(Integer flag) 
	    {
	    	 System.out.println("in post");
	    	 Toast.makeText(getApplicationContext(), "menu updated", Toast.LENGTH_LONG).show();
	  
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

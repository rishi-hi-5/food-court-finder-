package com.exa.fetch;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.exa.fetch.MainActivity.call;

//import com.exa.fetch.MainActivity.call;

import android.content.Entity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class location extends ActionBarActivity {
	private List<mess_loc> mess_list = new ArrayList<mess_loc>();
	protected static String url1="your url.php";
	protected static String url2="your url.php";
	String resp,menu_display;
	String s,selected;
	Integer flag=0;
	JSONObject json=new JSONObject();
	ArrayAdapter<mess_loc> adapter;
	Spinner dropdown;
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locate);
    	 dropdown= (Spinner)findViewById(R.id.spinner1);
      // System.out.println("what happened");
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    	String[] items = new String[]{"boys", "girls","veg","non veg","expense","rating"};
    	ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
    	dropdown.setAdapter(spinneradapter);
    	dropdown.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
              selected=dropdown.getSelectedItem().toString();
              mess_list.clear();
                new locate_mess().execute(selected);
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            	
              
            }
        });
        //new locate_mess().execute(selected);
       
    	}
    	catch(Exception e)
    	{
    		System.out.println(" error"+e.toString());
    	}
    }
	 

	private void location_call() {
	
		ListView list = (ListView) findViewById(R.id.messListView);
		System.out.println("location call");
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked,
					int position, long id) {
				 System.out.println("i am here loc1");
				mess_loc clickedCar = mess_list.get(position);
				String message = "You clicked position " + position
								+ " Which is car make " + clickedCar.getname();
				Intent displaydetails=new Intent(location.this,details.class);
				displaydetails.putExtra("messname", clickedCar.getname());
				startActivity(displaydetails);
				Toast.makeText(location.this, message, Toast.LENGTH_LONG).show();
			}
		});
	}
	
	private void populateListView() {
		 System.out.println("i am here loc3");
		adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.messListView);

		list.setAdapter(adapter);
	}
	private class MyListAdapter extends ArrayAdapter<mess_loc> {
		public MyListAdapter() {
			
			super(location.this, R.layout.locate, mess_list);
			 System.out.println("i am here loc4");
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure we have a view to work with (may have been given null)
			View itemView = convertView;
			
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.locatelist, parent, false);
				
			}
		   // mess_list.clear();
	    	

			System.out.println("i am here loc 5");
			// Find the car to work with.
			mess_loc currentmess = mess_list.get(position);
			 System.out.println("i am here loc 5 1");
			// Make:
			TextView messname = (TextView) itemView.findViewById(R.id.item_txtname);
			 System.out.println("i am here loc 5 1 1");
			System.out.println(""+currentmess.getname());
			messname.setText(currentmess.getname());
			
			 System.out.println("i am here loc 5 2");
			// Year:
			TextView contact = (TextView) itemView.findViewById(R.id.item_txtcontact);
			System.out.println(""+currentmess.getcontact());
			
			contact.setText("" +currentmess.getcontact());
			 System.out.println("i am here loc 5 3");
			// Condition:
			TextView address = (TextView) itemView.findViewById(R.id.textaddress);
			System.out.println(""+currentmess.getaddress());
			
			address.setText(""+currentmess.getaddress());
			adapter.notifyDataSetChanged();
			 System.out.println("i am here loc 5 4");

			
			return itemView;
		}	
	}
		

	
	  private class locate_mess extends AsyncTask<String,Void,Integer>
	    {
		  HttpPost httppost = new HttpPost(url1);
	    	 protected void onPreExecute() {
	    	        Toast.makeText(getApplicationContext(), "Starting",
	    	                Toast.LENGTH_LONG).show();
	    	    }

			@Override
			protected Integer doInBackground(String... params) {
		 	try
	    	{
		 		List<NameValuePair> data= new ArrayList<NameValuePair>(6);
                data.add(new BasicNameValuePair("request",selected));
               
		 		 System.out.println("i am here loc 6");
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
	    		System.out.println("\n\n\nhello\n\n\n");
	    	}
	    	catch(Exception e)
	    	{
	    		Log.e("log_tag","Error in http connection "+e.toString());
	    		//resultview.setText("couldnt found connection");
	    	}
	    	try
	    	{
	    		s="";
	    		
	    		json=null;
	    		JSONArray jarray=new JSONArray(resp);
	    		for(int i=0;i<jarray.length();i++)
	    		{
	    			//s = s + "Name : "+" "+json.getString("o_contact_no")+" "+json.getString("o_address")+"\n\n";
        			
	    			 System.out.println("i am here loc7");
	    			 
	    					json=jarray.getJSONObject(i);
	    					System.out.println(""+json.getString("m_name"));
	    					menu_display=json.getString("m_name");
	    					//System.out.println("i am here 2");
	    					//s = s + "Name : "+json.getString("m_name")+" "+json.getString("o_contact_no")+" "+json.getString("o_address")+"\n\n";
	        				mess_list.add(new mess_loc(json.getString("m_name"),json.getString("o_contact_no"),json.getString("o_address")));	    				
	    		}
	    	
	    	}
	    	catch(Exception e)
	    	{
	    		Log.e("log_tag","error parsing data"+e.toString());
	    	}
	    	System.out.println("i am here before return");
	    		//onPostExecute(flag);
	    	//return flag;
	    		return 0;
			}
			

			  
		    protected void onPostExecute(Integer i) {
		        Toast.makeText(getApplicationContext(), "refreshing list", Toast.LENGTH_LONG).show(); 
		        System.out.println("i am here loc8"+i);
		      
		        populateListView();
		        
		        location_call();
		  
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

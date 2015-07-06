package com.exa.fetch;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;




@SuppressLint("NewApi") public class registerowner extends ActionBarActivity {

	String s,rado1,rado2;
	
	String resp;
	Integer flag=0;
	EditText Firstname,lastname,contact,area,password,email,messname,thalirate;
	RadioGroup radiogender,radiofood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerowner);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
     
        Firstname=(EditText) findViewById(R.id.Firstname);
        lastname=(EditText) findViewById(R.id.lastname);
        contact=(EditText)findViewById(R.id.contact);
        area=(EditText)findViewById(R.id.area);
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.email);
        messname=(EditText)findViewById(R.id.messname);
        thalirate=(EditText)findViewById(R.id.thalirate);
        radiogender = (RadioGroup) findViewById(R.id.boygirlradio);
        radiofood = (RadioGroup) findViewById(R.id.foodtyperadio);
        
        radiogender.setOnCheckedChangeListener(new OnCheckedChangeListener() 
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.boy:
                        // do operations specific to this selection
                    	rado1="b";
                    break;

                    case R.id.girl:
                        // do operations specific to this selection
                    	rado1="g";
                    break;
                    
                    case R.id.co_ed:
                        // do operations specific to this selection
                    	rado1="bg";
                    break;
                    

                }


            }
        });
        radiofood.setOnCheckedChangeListener(new OnCheckedChangeListener() 
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.veg:
                        // do operations specific to this selection
                    	rado2="v";
                    break;

                    case R.id.nonveg:
                        // do operations specific to this selection
                    	rado2="n";
                    break;
                    
                    case R.id.both:
                        // do operations specific to this selection
                    	rado2="vn";
                    break;

                }


            }
        });
        final Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener
		(
			new Button.OnClickListener() 
				{
					public void onClick(View v)
					{
				
						try {
			                Class.forName("android.os.AsyncTask");
			            } catch (ClassNotFoundException e) {
			                // TODO Auto-generated catch block
			                e.printStackTrace();
			            }
						new registerowneractivity().execute();
					}
				}
		);
    }
    public final static boolean isValidEmail(CharSequence target) {
	    if (target == null) {
	        return false;
	    } else {
	        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	    }
	}


    private class registerowneractivity extends AsyncTask<String,Void,Integer>
    {
    	 protected void onPreExecute() {
    	        Toast.makeText(getApplicationContext(), "Starting",
    	                Toast.LENGTH_LONG).show();
    	    }
    	 
    	    protected  Integer doInBackground(String... params) {
    	        try {
    	        	HttpClient httpclient = new DefaultHttpClient();
    	            HttpPost httppost = new HttpPost("your url.php");
    	            
    	            try {
    	            	flag=0;
    	            	 List<NameValuePair> data= new ArrayList<NameValuePair>(9);
    	                  data.add(new BasicNameValuePair("o_name",Firstname.getText().toString()+" "+lastname.getText().toString()));
    	                  if(isValidEmail(email.getText().toString()))
    	                  {
    	                	  data.add(new BasicNameValuePair("o_email_id",email.getText().toString()));
    	                  }
    	                  else
    	                  {
    	                	  flag=1;
    	                  }
    	                  if(flag==0)
    	                  {
    	                  data.add(new BasicNameValuePair("o_contact_no",contact.getText().toString()));
    	                  data.add(new BasicNameValuePair("o_address",area.getText().toString()));
    	                  data.add(new BasicNameValuePair("m_name",messname.getText().toString()));
    	                  data.add(new BasicNameValuePair("m_thali_rate",thalirate.getText().toString()));
    	                  data.add(new BasicNameValuePair("b_g",rado1));
    	                  data.add(new BasicNameValuePair("v_n",rado2));
    	                  data.add(new BasicNameValuePair("o_password",password.getText().toString()));
    	                  httppost.setEntity(new UrlEncodedFormEntity(data));
    	                  System.out.println(""+data);
    	                 
                          HttpResponse rs=httpclient.execute(httppost);
    	                  }
    	            	} 	
    	            	catch (Exception e) 
    	            		{
    	            		// TODO Auto-generated catch block
    	            		}
    	            }
    	        	catch (Exception e) {
    	                // TODO Auto-generated catch block
    	            }
    	        
    	        return flag;
    	    }
    	 
    	   protected void onPostExecute(Integer i) {
    			  System.out.println("i am here"+i);
   	    	if(i==1)
   	    	{
   	    		Toast.makeText(getApplicationContext(), "Invalid email ID", Toast.LENGTH_LONG).show();
   	      	  
   	    	}
   	    	else
   	    	{
   	        Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
   	    	}
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

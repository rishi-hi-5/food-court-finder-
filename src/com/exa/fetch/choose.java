package com.exa.fetch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




@SuppressLint("NewApi") public class choose extends ActionBarActivity {

	TextView resultview;
	String s;
	protected static String url="your url";
	String resp;
	Integer flag=0;
	EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
        StrictMode.enableDefaults();
        resultview=(TextView) findViewById(R.id.result); 
        email=(EditText) findViewById(R.id.Name);
        password=(EditText) findViewById(R.id.Password);
        final Button student = (Button) findViewById(R.id.student);
		student.setOnClickListener
			(
				new Button.OnClickListener() 
					{
						public void onClick(View v)
						{
							Intent loginscreen=new Intent(choose.this,MainActivity.class);
							loginscreen.putExtra("user","student");
							startActivity(loginscreen);
						}
					}
			);
		
		final Button mess_owner=(Button) findViewById(R.id.mess_owner);
			mess_owner.setOnClickListener
				(
						new Button.OnClickListener() 
						{
							public void onClick(View v)
							{
								Intent loginscreen=new Intent(choose.this,MainActivity.class);
								loginscreen.putExtra("user","mess_owner");
								startActivity(loginscreen);
						
							}
						}	
					
				);
			
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

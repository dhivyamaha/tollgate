package com.example.tollgate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class form4 extends Activity {
    Spinner spnr;
    
    String[] tollgates = {
"credit card",
"debit card"
    };
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f4);
 
        spnr = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tollgates);
 
        spnr.setAdapter(adapter);
        
        Button submit;
        submit=(Button)findViewById(R.id.button1);
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				spnr.setOnItemSelectedListener(
		                new AdapterView.OnItemSelectedListener() {
		 
		                    @Override
		                    public void onItemSelected(AdapterView<?> adapter, View v,
		                            int i, long lng) {
		 
		                        String position = adapter.getItemAtPosition(i).toString();
		                        if(position=="credit card")
		                        {
		                        	Intent i1= new Intent(form4.this,form6.class);
		            				startActivity(i1);
		                        }
		                        else if(position=="debit card")
		                        {
		                        	Intent i1= new Intent(form4.this,form5.class);
		            				startActivity(i1);
		                        }
		                       
		                    }
		 
		                    @Override
		                    public void onNothingSelected(AdapterView<?> arg0) {
		                        // TODO Auto-generated method stub
		                    	Toast.makeText(getApplicationContext(),
		    							"Select one transaction type...!!!", Toast.LENGTH_SHORT).show();
		 
		                    }
		 
		                }
		            );
				
			}
		});
    }
 
}
	
	


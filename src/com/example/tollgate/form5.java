 package com.example.tollgate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.RadioGroup;
//import android.widget.Spinner;
import android.widget.Toast;

public class form5 extends Activity {
	EditText a, b;
	String cardnumber;
	String CVVnumber;
	
    
	Button ok;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f5);
		a = (EditText) findViewById(R.id.editText1);
		b = (EditText) findViewById(R.id.editText3);
		
      
		  

		ok = (Button) findViewById(R.id.button1);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cardnumber=a.getText().toString();
			    CVVnumber=b.getText().toString();
				
				
					try {
						cardnumber = URLEncoder.encode(cardnumber, "UTF-8");
						CVVnumber = URLEncoder.encode(CVVnumber, "UTF-8");
						String url = "http://10.100.9.191/patientreg.php?CARDNUMBER="+ cardnumber.trim() + "&CVVNUMBER="+ CVVnumber.trim();
						System.out.println(url);
						pass_value_to_db get = new pass_value_to_db();
						get.execute(new String[] { url });

					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Intent i1 = new Intent(form5.this, form7.class);
					startActivity(i1);

	}
	
	});
}


		class pass_value_to_db extends AsyncTask<String, Void, String> {

			ProgressDialog dialog;

			@Override
			protected void onPreExecute() { // TODO Auto-generated method stub
				dialog = new ProgressDialog(form5.this);
				dialog.setTitle("Processing...");
				dialog.setMessage("Please wait.");
				dialog.setCancelable(false);
				dialog.show();
			}

			@Override
			protected String doInBackground(String... urls) {
				String result = "";
				for (String url : urls) {
					InputStream is = null;
					try {

						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost(url);
						HttpResponse response = httpclient.execute(httppost);
						int status = response.getStatusLine().getStatusCode();
						Log.d("KG", "status=" + status);

						if (status == 200) {
							HttpEntity entity = response.getEntity();
							is = entity.getContent();
							BufferedReader reader = new BufferedReader(
									new InputStreamReader(is, "iso-8859-1"), 8);
							String line = "";
							while ((line = reader.readLine()) != null) {
								result += line;
							}
							is.close();

							Log.v("KG", result);

						}
					}
					catch (Exception ex) {
						Log.e("Error", ex.toString());
					}
					
					//Toast.makeText(getApplicationContext(),"Registered successfully", Toast.LENGTH_LONG).show();
					//Intent i=new Intent(SubActivity.this,MainActivity.class);
					//startActivity(i);
				}
				return result;
			}

			protected void onPostExecute(String result) {
				Log.v("KG", "output=" + result);
				result = result.trim(); //
				// Toast.makeText(getApplicationContext(), result, //
				// Toast.LENGTH_LONG).show();
				if (result.equals("false")) {

					// *******************************************************

					Toast.makeText(getApplicationContext(),
							" Please try again later...", Toast.LENGTH_SHORT).show();
				} else {
					System.out.println(result);

				}

				if (dialog != null)
					dialog.dismiss();

			}
		}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


		
		

				
		
	

	    }

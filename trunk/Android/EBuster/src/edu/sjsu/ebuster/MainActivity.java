package edu.sjsu.ebuster;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	private EditText emailField;
	private EditText passwordField;
	private Button logonBtn;
	private AlertDialog alertDialog;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        logonBtn = (Button)findViewById(R.id.logon_button);
        emailField = (EditText)findViewById(R.id.email_field);
        passwordField = (EditText)findViewById(R.id.password_field);
        
        logonBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
			case R.id.logon_button:
				new CheckCredentials().execute("test");
				//if(validateUser(emailField.getText().toString(),passwordField.getText().toString()))
					
					//finish();
				break;
		}
		
	}
	
	private void showLoading(){
		Builder aDialog = new AlertDialog.Builder(this);
    	//aDialog.setNeutralButton("OK", null);
		aDialog.setTitle("Please Wait");
    	aDialog.setMessage("Checking Credentials...");
    	
    	alertDialog = aDialog.create();
    	alertDialog.show();
	}
	
	private void showDialog(String apiKey){
		
		Builder aDialog = new AlertDialog.Builder(this);
    	
    	if(apiKey != null){
    		alertDialog.dismiss();
    		
    		Intent intent = new Intent(getApplicationContext(),LandingActivity.class);
    		startActivity(intent);
    		return;
    	}else{
    		aDialog.setTitle("Failure");
    		aDialog.setMessage("Please check your email and password");
    		aDialog.setNeutralButton("OK", null);
    	}
    	alertDialog.dismiss();
    	alertDialog = aDialog.create();
    	alertDialog.show();
	}
	
	private class CheckCredentials extends AsyncTask<String, Void, String> {

		protected void onPreExecute(){
			showLoading();
		}
		
	    protected String doInBackground(String... args) {
	    	try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//AndroidHttpClient aHttpClient = AndroidHttpClient.newInstance("Android");
			
//	    	HttpPost httpPost = new HttpPost(args[0]);
//	    	httpPost.setHeader("Content-Type", "application/json");
//	    	
//			try {
//				//httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//				httpPost.setEntity(new StringEntity(args[1]));
//				aHttpClient.execute(httpPost);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				return null;
//				//Toast.makeText(getApplicationContext(), "Error sending to server", Toast.LENGTH_SHORT).show();
//			} finally {
//				aHttpClient.close();
//			}
			
			//Toast.makeText(getApplicationContext(), "Successful Transfer", Toast.LENGTH_SHORT).show();
			//return null;
	    	return "ABCD12345";
	    }
	    
	    protected void onPostExecute(String transmissionSuccess) {
	    	showDialog(transmissionSuccess);
	     }
    }
    
}

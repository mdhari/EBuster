package edu.sjsu.ebuster.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.util.Log;
import edu.sjsu.ebuster.models.MusicModel;
import edu.sjsu.ebuster.parsers.MusicParser;

public class MusicData {
	private final String TAG = "MusicData";
	
	public List<MusicModel> getData(Context context,String url){
		Log.i(TAG,"getData with Context");
		try {
			InputStream input = context.getAssets().open("music.json");
			int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            String jsonText = new String(buffer);
			MusicParser musicParser = new MusicParser();
			return musicParser.parse(jsonText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ArrayList<MusicModel>();
	}
	
	public void getData(String url){
		AndroidHttpClient aHttpClient = AndroidHttpClient.newInstance("Android");
		
		Log.i(TAG,"TRYING URL:::::::" + url);
		
		HttpGet httpMethod = new HttpGet(url);
    	
		try {
			//httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			//httpPost.setEntity(new StringEntity(args[1]));
			HttpResponse response = aHttpClient.execute(httpMethod);
			response.getEntity().getContent();
			Log.i(TAG," RESPONSE: " + response.getEntity().getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			aHttpClient.close();
		}
	}

}

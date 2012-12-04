package edu.sjsu.ebuster.parsers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import edu.sjsu.ebuster.models.MusicModel;

public class MusicParser {

	private final String TAG = "MusicParser";
	
	public List<MusicModel> parse(String data){
		
		List<MusicModel> musicModels = new ArrayList<MusicModel>();
		
		try {
			JSONArray jsonArray = new JSONArray(data);
			for(int i = 0; i < jsonArray.length();i++){
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				MusicModel musicModel = new MusicModel();
				
				musicModel.setEventId(Integer.parseInt(jsonObj.get("eventId").toString()));
				musicModel.setTitle(jsonObj.get("title").toString());
				musicModel.setVenue_name(jsonObj.get("venue_name").toString());
				musicModel.setVenue_address(jsonObj.get("venue_address").toString());
				musicModel.setCity_name(jsonObj.get("city_name").toString());
				musicModel.setRegion_abbr(jsonObj.get("region_abbr").toString());
				musicModel.setPostal_code(jsonObj.get("postal_code").toString());
				musicModel.setLatitude(jsonObj.get("latitude").toString());
				musicModel.setLongitude(jsonObj.get("longitude").toString());
				musicModel.setImage_url(jsonObj.getJSONObject("medium").get("url").toString());
				
				String rawDate = jsonObj.get("start_time").toString();
				musicModel.setStart_time(new Date(Long.parseLong(rawDate.substring(6, 19))));
				
				Log.i(TAG, "musicModel: " + musicModel);
				
				musicModels.add(musicModel);
			}
//			Log.i(TAG,"jsonArray" + jsonArray.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return musicModels;
	}
	
}

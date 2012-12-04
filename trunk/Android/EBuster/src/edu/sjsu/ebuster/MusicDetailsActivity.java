package edu.sjsu.ebuster;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.sjsu.ebuster.common.Utils;
import edu.sjsu.ebuster.models.MusicModel;

public class MusicDetailsActivity extends Activity implements OnClickListener{

	private final String TAG = "MusicDetailsActivity";
	
	private double lat;
	private double lon;
	private MusicModel musicModel;
	private LocationManager locationManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_details);
        musicModel = getIntent().getParcelableExtra("MusicModel");
        Log.i(TAG,"MusicModel toString: " + musicModel);
        
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

//		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
		      // Called when a new location is found by the network location provider.
		    	lat = location.getLatitude();
		    	lon = location.getLongitude();
		      //makeUseOfNewLocation(location);
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		  };
		  
		  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        Bitmap bm = Utils.getImageBitmap(musicModel.getImage_url());
        if(bm!=null){
        	((ImageView)findViewById(R.id.music_image)).setImageBitmap(bm);
        }
        ((TextView)findViewById(R.id.music_title)).setText(musicModel.getTitle());
        ((TextView)findViewById(R.id.venue_name)).setText(musicModel.getVenue_name());
        ((TextView)findViewById(R.id.venue_addr1)).setText(musicModel.getVenue_address());
        ((TextView)findViewById(R.id.venue_addr2)).setText(musicModel.getCity_name() + ", " + 
        musicModel.getRegion_abbr() + " " + musicModel.getPostal_code());
        
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");
        ((TextView)findViewById(R.id.start_time)).setText(sdf.format(musicModel.getStart_time()));
        
        ((Button)findViewById(R.id.map_it_btn)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_music_details, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.map_it_btn:
			double lat = Double.parseDouble(musicModel.getLatitude());
			double lon = Double.parseDouble(musicModel.getLongitude());
			Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if(location != null){ 
			Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
					Uri.parse("http://maps.google.com/maps?saddr=" +location.getLatitude() +","+location.getLongitude() + "&daddr="+lat+","+lon));
					startActivity(intent);
			}
			break;
		}
		
	}

    
}

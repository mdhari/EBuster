package edu.sjsu.ebuster;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.sjsu.ebuster.adapters.MusicListAdapter;
import edu.sjsu.ebuster.data.MusicData;
import edu.sjsu.ebuster.models.MusicModel;

public class MusicActivity extends Activity implements OnItemClickListener{

	private final String TAG = "MusicActivity";
	private AlertDialog alertDialog;
	private ListView listView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        
        new LoadData().execute();
        
        listView = (ListView)findViewById(R.id.music_list);
        listView.setAdapter(new MusicListAdapter(getApplicationContext()));
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_music, menu);
        return true;
    }
    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		Intent intent = new Intent(getApplicationContext(),MusicDetailsActivity.class);
		MusicListAdapter musicListAdapter = (MusicListAdapter)listView.getAdapter();
		MusicModel musicModel = (MusicModel)musicListAdapter.getItem(position);
		intent.putExtra("MusicModel", musicModel);
		startActivity(intent);
	}
	
	private void showLoading(){
		Builder aDialog = new AlertDialog.Builder(this);
    	//aDialog.setNeutralButton("OK", null);
		aDialog.setTitle("Please Wait");
    	aDialog.setMessage("Loading...");
    	
    	alertDialog = aDialog.create();
    	alertDialog.show();
	}
	
	private void dismissLoading(){
		alertDialog.dismiss();
	}
	
	private class LoadData extends AsyncTask<Void, Void, List<MusicModel>> {

		@Override
		protected void onPreExecute() {
			showLoading();
		}


		@Override
		protected void onPostExecute(List<MusicModel> result) {
			//listView.setMusicEvents(result);
			Log.i(TAG, "onPOSTEXECUTE: " + result);
			MusicListAdapter musicListAdapter = (MusicListAdapter)listView.getAdapter();
			musicListAdapter.setMusicEvents(result);
			musicListAdapter.notifyDataSetChanged();
			dismissLoading();
		}


		@Override
		protected List<MusicModel> doInBackground(Void... params) {
			MusicData musicData = new MusicData();
	        return musicData.getData(getApplicationContext(),"file:///android_asset/music.json");
		}
    }

    
}

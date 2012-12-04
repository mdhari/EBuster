package edu.sjsu.ebuster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import edu.sjsu.ebuster.adapters.MovieListAdapter;

public class MovieActivity extends Activity implements OnItemClickListener {

	private ListView listView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        
        listView = (ListView)findViewById(R.id.movie_list);
        listView.setAdapter(new MovieListAdapter(getApplicationContext()));
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_movie, menu);
        return true;
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		Intent intent = new Intent(getApplicationContext(),MovieDetailsActivity.class);
		startActivity(intent);
	}

    
}

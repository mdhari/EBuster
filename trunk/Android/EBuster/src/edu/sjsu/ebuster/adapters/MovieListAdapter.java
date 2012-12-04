package edu.sjsu.ebuster.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import edu.sjsu.ebuster.R;
import edu.sjsu.ebuster.models.MovieModel;

public class MovieListAdapter extends BaseAdapter implements ListAdapter{
	
	private List<MovieModel> movies = new ArrayList<MovieModel>();
	private LayoutInflater inflater;
	
	public MovieListAdapter(Context context){
		inflater = LayoutInflater.from(context);
	}
	
	private class MovieSlotHolder{
		private ImageView image;
		private TextView title;
	}

	@Override
	public int getCount() {
		return 6;
		//return movies.size();
	}

	@Override
	public Object getItem(int position) {
		return movies.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		MovieSlotHolder holder;

		if (convertView == null) {
            convertView = inflater.inflate(R.layout.movie_slot, null);

            holder = new MovieSlotHolder();
//            holder.text = (TextView) convertView.findViewById(R.id.menuText);
//            holder.icon = (ImageView) convertView.findViewById(R.id.menuIcon);

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView and the ImageView.
            holder = (MovieSlotHolder) convertView.getTag();
        }
		 // Bind the data efficiently with the holder.
        //holder.text.setText(mMenuVods.get(position).getOpt());
        return convertView;
	}

}

package edu.sjsu.ebuster.adapters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import edu.sjsu.ebuster.R;
import edu.sjsu.ebuster.common.Utils;
import edu.sjsu.ebuster.models.MusicModel;

public class MusicListAdapter extends BaseAdapter implements ListAdapter {

	public final String TAG = "MusicListAdapter";

	private List<MusicModel> musicEvents = new ArrayList<MusicModel>();

	public void setMusicEvents(List<MusicModel> musicEvents) {
		this.musicEvents = musicEvents;
	}

	private LayoutInflater inflater;

	public MusicListAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	private class MusicSlotHolder {
		private ImageView image;
		private TextView title;
		private TextView time;
	}

	@Override
	public int getCount() {
		return musicEvents.size();
	}

	@Override
	public Object getItem(int position) {
		return musicEvents.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		MusicSlotHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.music_slot, null);

			holder = new MusicSlotHolder();
			holder.title = (TextView) convertView
					.findViewById(R.id.movie_title);
			holder.image = (ImageView) convertView
					.findViewById(R.id.movie_image);
			holder.time = (TextView) convertView.findViewById(R.id.music_start_time);
			

			convertView.setTag(holder);
		} else {
			// Get the ViewHolder back to get fast access to the TextView and
			// the ImageView.
			holder = (MusicSlotHolder) convertView.getTag();
		}
		// Bind the data efficiently with the holder.
		MusicModel musicModel = (MusicModel) getItem(position);

		// MH: jpeg isn't being recognized by SKIA decoder
		Bitmap bm = Utils.getImageBitmap(musicModel.getImage_url());

		if (bm != null) {
			holder.image.setImageBitmap(bm);
		} else {
			holder.image.setImageResource(R.drawable.concert);
		}

		holder.title.setText(musicModel.getTitle());
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");
		holder.time.setText(sdf.format(musicModel.getStart_time()));

		Log.i(TAG, "The title is: " + musicModel.getTitle());
		// holder.text.setText(mMenuVods.get(position).getOpt());
		return convertView;
	}

}

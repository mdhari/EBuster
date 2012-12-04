package edu.sjsu.ebuster.common;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Utils {
	public final static String TAG = "Utils";
	
	// since SKIA decoder hates non-png format
	// this utility function should help
	public static Bitmap getImageBitmap(String url) {
		Bitmap bm = null;
		
		try {
			URL aURL = new URL(url);
			URLConnection conn = aURL.openConnection();
			conn.setConnectTimeout(2000);
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bm = BitmapFactory.decodeStream(bis);
			bis.close();
			is.close();
		} catch (IOException e) {
			Log.e(TAG, "Error getting bitmap", e);
		}
		return bm;
	}
}

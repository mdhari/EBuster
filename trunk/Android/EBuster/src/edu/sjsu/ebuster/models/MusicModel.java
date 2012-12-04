package edu.sjsu.ebuster.models;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class MusicModel implements Parcelable {


	public MusicModel(Parcel in) {
		this.eventId = in.readInt();
		this.title = in.readString();
		this.image_url  = in.readString();
		this.description= in.readString();
		this.venue_name= in.readString();
		this.venue_address= in.readString();
		this.city_name= in.readString();
		this.region_abbr= in.readString();
		this.postal_code= in.readString();
		this.latitude= in.readString();
		this.longitude= in.readString();
		this.start_time = new Date(in.readLong());
		
	}
	public MusicModel() {
		
	}
	@Override
	public String toString() {
		return "MusicModel [eventId=" + eventId + ", title=" + title
				+ ", image_url=" + image_url + ", description=" + description
				+ ", venue_name=" + venue_name + ", venue_address="
				+ venue_address + ", city_name=" + city_name + ", region_abbr="
				+ region_abbr + ", postal_code=" + postal_code + ", latitude="
				+ latitude + ", longitude=" + longitude + ", start_time="
				+ start_time + "]";
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getVenue_name() {
		return venue_name;
	}
	public void setVenue_name(String venue_name) {
		this.venue_name = venue_name;
	}
	public String getVenue_address() {
		return venue_address;
	}
	public void setVenue_address(String venue_address) {
		this.venue_address = venue_address;
	}
	public String getRegion_abbr() {
		return region_abbr;
	}
	public void setRegion_abbr(String region_abbr) {
		this.region_abbr = region_abbr;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	private int eventId;
	private String title;
	private String image_url;
	private String description;
	private String venue_name;
	private String venue_address;
	private String city_name;
	private String region_abbr;
	private String postal_code;
	private String latitude;
	private String longitude;
	private Date start_time;
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(eventId);
		dest.writeString(title);
		dest.writeString(image_url);
		dest.writeString(description);
		dest.writeString(venue_name);
		dest.writeString(venue_address);
		dest.writeString(city_name);
		dest.writeString(region_abbr);
		dest.writeString(postal_code);
		dest.writeString(latitude);
		dest.writeString(longitude);
		dest.writeLong(start_time.getTime());
		
	}
	
	public static final Parcelable.Creator<MusicModel> CREATOR
    = new Parcelable.Creator<MusicModel>() {

public MusicModel createFromParcel(Parcel in) {
			return new MusicModel(in);
}

public MusicModel[] newArray(int size) {
    return new MusicModel[size];
}
};
	
	
}

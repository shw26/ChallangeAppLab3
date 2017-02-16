package group1.tcss450.uw.edu.challangeapp.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by user on 2017/2/12.
 */

public class Setlist implements Serializable{
    private int    mShowid;
    private String mShowdate;
    private String mShort_date;
    private String mLong_date;
    private String mRelative_date;
    private String mUrl;
    private String mGapchart;
    private String mArtist;
    private int    mArtistid;
    private int    mVenueid;
    private String mVenue;
    private String mLocation;
    private String mSetlistdata;
    private String mSetlistnotes;
    private String mRating;

    public Setlist(JSONObject oneData) throws JSONException {
        create(oneData);
    }

    private void create(JSONObject oneData) throws JSONException {
        int error_code = 0; //jsonObject.getInt("error_code");
        if (error_code != 0){
//            String error_message = jsonObject.getString("error_message");
 //           Log.d("Setlist", error_message);
        }else {
//            JSONArray data = jsonObject.getJSONArray("data");
//
//            //takes only the first data set
//            JSONObject oneData = (JSONObject) data.get(0);

 //           JSONObject oneData = jsonObject;

            mShowid         = oneData.getInt("showid");
            mShowdate       = oneData.getString("showdate");
            mShort_date     = oneData.getString("short_date");
            mLong_date      = oneData.getString("long_date");
            mRelative_date  = oneData.getString("relative_date");
            mUrl            = oneData.getString("url");
            mGapchart       = oneData.getString("gapchart");
            mArtist         = oneData.getString("artist");
            mArtistid       = oneData.getInt("artistid");
            Log.e("artist setlist",mArtist);
            mVenueid        = oneData.getInt("venueid");
            mVenue          = oneData.getString("venue");
            mLocation       = oneData.getString("location");
            mSetlistdata    = oneData.getString("setlistdata");
            mSetlistnotes   = oneData.getString("setlistnotes");
            mRating         = oneData.getString("rating");
        }
    }


    public int getShowid() {
        return mShowid;
    }

    public String getShowdate() {
        return mShowdate;
    }

    public String getShort_date() {
        return mShort_date;
    }

    public String getLong_date() {
        return mLong_date;
    }

    public String getRelative_date() {
        return mRelative_date;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getGapchart() {
        return mGapchart;
    }

    public String getArtist() {
        return mArtist;
    }

    public int getArtistid() {
        return mArtistid;
    }

    public int getVenueid() {
        return mVenueid;
    }

    public String getVenue() {
        return mVenue;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getSetlistdata() {
        return mSetlistdata;
    }

    public String getSetlistnotes() {
        return mSetlistnotes;
    }

    public String getRating() {
        return mRating;
    }
}

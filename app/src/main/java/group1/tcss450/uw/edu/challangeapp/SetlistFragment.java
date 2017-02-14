package group1.tcss450.uw.edu.challangeapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import group1.tcss450.uw.edu.challangeapp.model.Setlist;



public class SetlistFragment extends Fragment {

    public static final String KEY = "key";
    private LinearLayout myLL;

    @Override
    public void onStart(){
        super.onStart();

        myLL = (LinearLayout) getActivity().findViewById(R.id.setlist);
        if (getArguments() != null) {
            Setlist[] arg = (Setlist[]) getArguments().get(KEY);
//            Log.d("arg", String.valueOf(arg[1].getArtist()));
            JSONObject jsonObject = null;
//            try {
//                jsonObject = new JSONObject(arg);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            Setlist setlist = new Setlist();
//            try {
//                setlist.create(jsonObject);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            updateContent(arg);
        }
    }

    public void updateContent(Setlist[] setlist){
        int n = setlist.length;
        Log.d("setlist setlistf", setlist[0].getArtist());
        final TextView[] tvArr = new TextView[n*15];
        //final LinearLayout myLL = (LinearLayout) getActivity().findViewById(R.id.setlist);

        for(int i = 0; i < n; i++){
            final TextView tv1 = new TextView(getActivity());
            tv1.setText(String.valueOf(setlist[i].getShowid()));
            myLL.addView(tv1, i*15+0);
            tvArr[i*15+0] = tv1;

           // tv = new TextView(getActivity());
            final TextView tv2 = new TextView(getActivity());
            tv2.setText(setlist[i].getShowdate());;
            myLL.addView(tv2,  i*15+1);
            tvArr[i*15+1] = tv2;

           // tv = new TextView(getActivity());
            final TextView tv3 = new TextView(getActivity());
            tv3.setText(setlist[i].getShort_date());
            myLL.addView(tv3);
            tvArr[i*15+2] = tv3;

           // tv = new TextView(getActivity());
            final TextView tv4 = new TextView(getActivity());
            tv4.setText(setlist[i].getLong_date());;
            myLL.addView(tv4);
            tvArr[i*15+3] = tv4;

           // tv = new TextView(getActivity());
            final TextView tv5 = new TextView(getActivity());
            tv5.setText(setlist[i].getRelative_date());
            myLL.addView(tv5);
            tvArr[i*15+4] = tv5;

           // tv = new TextView(getActivity());
            final TextView tv6 = new TextView(getActivity());
            tv6.setText(setlist[i].getUrl());
            myLL.addView(tv6);
            tvArr[i*15+5] = tv6;

           // tv = new TextView(getActivity());
            final TextView tv7 = new TextView(getActivity());
            tv7.setText(setlist[i].getGapchart());
            myLL.addView(tv7);
            tvArr[i*15+6] = tv7;

          //  tv = new TextView(getActivity());
            final TextView tv8 = new TextView(getActivity());
            tv8.setText(setlist[i].getArtist());
            myLL.addView(tv8);
            tvArr[i*15+7] = tv8;

           // tv = new TextView(getActivity());
            final TextView tv9 = new TextView(getActivity());
            tv9.setText(String.valueOf(setlist[i].getArtistid()));
            myLL.addView(tv9);
            tvArr[i*15+8] = tv9;

          //  tv = new TextView(getActivity());
            final TextView tv10 = new TextView(getActivity());
            tv10.setText(setlist[i].getVenue());
            myLL.addView(tv10);
            tvArr[i*15+9] = tv10;

          //  tv = new TextView(getActivity());
            final TextView tv11 = new TextView(getActivity());
            tv11.setText(String.valueOf(setlist[i].getVenueid()));
            myLL.addView(tv11);
            tvArr[i*15+10] = tv11;

          //  tv = new TextView(getActivity());
            final TextView tv12 = new TextView(getActivity());
            tv12.setText(setlist[i].getLocation());
            myLL.addView(tv12);
            tvArr[i*15+11] = tv12;

         //   tv = new TextView(getActivity());
            final TextView tv13 = new TextView(getActivity());
            tv13.setText(setlist[i].getSetlistdata());
            myLL.addView(tv13);
            tvArr[i*15+12] = tv13;

         //   tv = new TextView(getActivity());
            final TextView tv14 = new TextView(getActivity());
            tv14.setText(setlist[i].getSetlistnotes());
            myLL.addView(tv14);
            tvArr[i*15+13] = tv14;

        //    tv = new TextView(getActivity());
            final TextView tv15 = new TextView(getActivity());
            tv15.setText(setlist[i].getRating());
            myLL.addView(tv15);
            tvArr[i*15+14] = tv15;

        }

//        tv = (TextView) getActivity().findViewById(R.id.tv01);
//        tv.setText(String.valueOf(setlist[i].getShowid()));
//        tv = (TextView) getActivity().findViewById(R.id.tv02);
//        tv.setText(setlist.getShowdate());
//        tv = (TextView) getActivity().findViewById(R.id.tv03);
//        tv.setText(setlist.getShort_date());
//        tv = (TextView) getActivity().findViewById(R.id.tv04);
//        tv.setText(setlist.getLong_date());
//        tv = (TextView) getActivity().findViewById(R.id.tv05);
//        tv.setText(setlist.getRelative_date());
//        tv = (TextView) getActivity().findViewById(R.id.tv06);
//        tv.setText(setlist.getUrl());
//        tv = (TextView) getActivity().findViewById(R.id.tv07);
//        tv.setText(setlist.getGapchart());
//        tv = (TextView) getActivity().findViewById(R.id.tv08);
//        tv.setText(setlist.getArtist());
//        tv = (TextView) getActivity().findViewById(R.id.tv09);
//        tv.setText(String.valueOf(setlist.getArtistid()));
//        tv = (TextView) getActivity().findViewById(R.id.tv10);
//        tv.setText(setlist.getVenue());
//        tv = (TextView) getActivity().findViewById(R.id.tv11);
//        tv.setText(String.valueOf(setlist.getVenueid()));
//        tv = (TextView) getActivity().findViewById(R.id.tv12);
//        tv.setText(setlist.getLocation());
//        tv = (TextView) getActivity().findViewById(R.id.tv13);
//        tv.setText(setlist.getSetlistdata());
//        tv = (TextView) getActivity().findViewById(R.id.tv14);
//        tv.setText(setlist.getSetlistnotes());
//        tv = (TextView) getActivity().findViewById(R.id.tv15);
//        tv.setText(setlist.getRating());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setlist, container, false);
    }




}

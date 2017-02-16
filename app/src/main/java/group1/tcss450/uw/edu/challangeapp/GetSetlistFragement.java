package group1.tcss450.uw.edu.challangeapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import group1.tcss450.uw.edu.challangeapp.model.Setlist;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GetSetlistFragement.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class GetSetlistFragement extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;

    private static final String PARTIAL_URL
            = "http://cssgate.insttech.washington.edu/~cfb3/TCSS450A-W17/phish/setlist/";

    private static final String[] IDS = new String[] {
            "1479253444", "1252691618", "1252633496", "1252343167", "1250887835", "1252877083",
            "1252956590", "1252989413", "1479253406", "1475149035"};

    AutoCompleteTextView act;
    public GetSetlistFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_get_setlist_fragement, container, false);
        Button b = (Button) v.findViewById(R.id.random);
        b.setOnClickListener(this);
        b = (Button) v.findViewById(R.id.tenRecent);
        b.setOnClickListener(this);
        b = (Button) v.findViewById(R.id.go);
        b.setOnClickListener(this);

        act = (AutoCompleteTextView) v.findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, IDS);

        act.setAdapter(adapter);


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        AsyncTask<String, Void, String> task = null;
        String option = new String();
        if (mListener != null){
            switch (v.getId()) {
                case R.id.random:
                    task = new WebServiceTask();
                    option = "random.php";
                    break;
                case R.id.tenRecent:
                    task = new WebServiceTask();
                    option = "recent.php";
                    break;
                case R.id.go:
                    String actText = act.getText().toString();
                    option = "getShow.php?show_id=" + actText;
                    task = new WebServiceTask();
                    break;
            }
        }
        task.execute(PARTIAL_URL, option);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Setlist[] setlist);
    }

    private class WebServiceTask extends AsyncTask<String, Void, String> {
        private final String SERVICE = "random.php";

        @Override
        protected String doInBackground(String... strings) {
            String response = "";
            HttpURLConnection urlConnection = null;
            String url = strings[0];
            String option = strings[1];
            try {
                URL urlObject = new URL(url + option);
                urlConnection = (HttpURLConnection) urlObject.openConnection();
                InputStream content = urlConnection.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    response += s;
                }
            } catch (Exception e) {
                response = "Unable to connect, Reason: "
                        + e.getMessage();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("getset",result);
            Setlist setlist[] = new Setlist[0];
            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }

            try {
                JSONObject jObj = new JSONObject(result);

                JSONObject response = jObj.getJSONObject("response");
                int count = response.getInt("count");
                setlist = new Setlist[count];
                JSONArray data = response.getJSONArray("data");
                for (int i = 0; i < count; i++){

                    //takes data set
                    JSONObject oneData = (JSONObject) data.get(i);
                    setlist[i] = new Setlist(oneData);
                    //setlist[i].create(oneData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mListener.onFragmentInteraction(setlist);

        }
    }

    private class tenRecentWebServiceTask extends AsyncTask<String, Void, String> {
        private final String SERVICE = "recent.php";

        @Override
        protected String doInBackground(String... strings) {
            String response = "";
            HttpURLConnection urlConnection = null;
            String url = strings[0];
            try {
                URL urlObject = new URL(url + SERVICE);
                urlConnection = (HttpURLConnection) urlObject.openConnection();
                InputStream content = urlConnection.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    response += s;
                }
            } catch (Exception e) {
                response = "Unable to connect, Reason: "
                        + e.getMessage();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("getset",result);
            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }
            // TODO: 2017/2/14
            Setlist[] setlist = new Setlist[0];
            try {
                JSONObject jObj = new JSONObject(result);

                JSONObject response = jObj.getJSONObject("response");
                int count = response.getInt("count");
                setlist = new Setlist[count];
                JSONArray data = response.getJSONArray("data");
                for (int i = 0; i < count; i++){

                    //takes data set
                    JSONObject oneData = (JSONObject) data.get(i);
                    Log.i("onedata",oneData.getString("artist"));
                    setlist[i] = new Setlist(oneData);
                    //setlist[i].create(oneData);

                    //Log.d("gsf ",String.valueOf(setlist[i].getArtistid()));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("gsf 2",String.valueOf(setlist[2].getArtist()));
            Log.d("gsf 7",String.valueOf(setlist[7].getArtist()));
            mListener.onFragmentInteraction(setlist);

        }
    }
}

package group1.tcss450.uw.edu.challangeapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThirdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ThirdFragment extends Fragment implements View.OnClickListener{

    private static final String PARTIAL_URL
            = "http://cssgate.insttech.washington.edu/" +
            "~shw26/dbconnect";
    private OnFragmentInteractionListener mListener;
    private String mUsername;
    private String mPassword;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third, container, false);
        Button b = (Button) v.findViewById(R.id.registerSubmitButton);
        b.setOnClickListener(this);
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
        attemptLogin(v);
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
        void onFragmentInteraction(Fragment fragment, String username, String password);
    }

    /**
     *
     * @param view
     */
    protected void attemptLogin(View view){
        TextView usernameTextView = (TextView) getActivity().findViewById(R.id.usernamethird);
        TextView passwordTextView = (TextView) getActivity().findViewById(R.id.passwordthird);
        TextView repasswordTextView = (TextView) getActivity().findViewById(R.id.repeatpasswordthird);
        boolean cancel = false;
        View focusView = null;

        usernameTextView.setError(null);
        passwordTextView.setError(null);
        repasswordTextView.setError(null);
        String username = usernameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String repassword = repasswordTextView.getText().toString();

        //if the password is entered and legit
        if (TextUtils.isEmpty(password) || password.length() <4) {
            passwordTextView.setError(getString(R.string.invalid_password)+" 4");
            focusView = passwordTextView;
            cancel = true;
        }
        if (TextUtils.isEmpty(username) || username.length() <4){
            usernameTextView.setError(getString(R.string.invalid_username)+" 4");
            focusView = usernameTextView;
            cancel = true;
        }
        if (TextUtils.isEmpty(repassword) || !password.matches(repassword)){
            repasswordTextView.setError(getString(R.string.doesnt_match));
            focusView = repasswordTextView;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }else {

            mPassword=password;
            mUsername=username;
            AsyncTask<String, Void, String> task = null;
            //mSignInBtn.setEnabled(false);
            task = new ThirdFragment.PostWebServiceTask();
            task.execute(PARTIAL_URL, username, password);
        }
    }

    private class PostWebServiceTask extends AsyncTask<String, Void, String> {
        private final String SERVICE = "_register.php";

        @Override
        protected String doInBackground(String... strings) {
            if (strings.length != 3) {
                throw new IllegalArgumentException("Three String arguments required.");
            }
            String response = "";
            HttpURLConnection urlConnection = null;
            String url = strings[0];
            try {
                URL urlObject = new URL(url + SERVICE);
                urlConnection = (HttpURLConnection) urlObject.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                //my_name=username&my_pw=password
                String data = URLEncoder.encode("my_name", "UTF-8")
                        + "=" + URLEncoder.encode(strings[1], "UTF-8")
                        + "&" + URLEncoder.encode("my_pw", "UTF-8")
                        + "=" + URLEncoder.encode(strings[2], "UTF-8");
                wr.write(data);
                wr.flush();

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
            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG)
                        .show();
                //reg_btn.setEnabled(true);
                return;
            }else if (!result.startsWith("true")){
                //if the username or the password is not correct.
                //reg_btn.setEnabled(true);
                Toast.makeText(getActivity(),"username already exist", Toast.LENGTH_SHORT).show();
            }else if (result.startsWith("true")){
                //if the username and password matches a data in the db.
                Toast.makeText(getActivity(),"create and login success",Toast.LENGTH_SHORT).show();
                //reg_btn.setEnabled(true);
                //goToMainActivity();
                Fragment fragment = new FourthFragment();
                mListener.onFragmentInteraction(fragment, mUsername, mPassword);
            }

        }

    }
}

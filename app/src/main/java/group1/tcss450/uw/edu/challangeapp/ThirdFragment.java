package group1.tcss450.uw.edu.challangeapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThirdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ThirdFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;

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

            Fragment fragment = new FourthFragment();
            mListener.onFragmentInteraction(fragment, username, password);
        }
    }
}

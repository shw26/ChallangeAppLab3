package group1.tcss450.uw.edu.challangeapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FourthFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FourthFragment extends Fragment {
    public static final String KEY = "key";
    private OnFragmentInteractionListener mListener;

    public FourthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fourth, container, false);
        TextView textView = (TextView) v.findViewById(R.id.usernamefourth);
        textView = (TextView) v.findViewById(R.id.passwordfourth);
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
    public  void onStart(){
        super.onStart();
        if (getArguments() != null) {
            updateContent(getArguments().getStringArray(KEY));
        }
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
        void onFragmentInteraction(Fragment fragment);
    }
    public  void updateContent(String[] packedStr){
        TextView usernameTextView = (TextView) getActivity().findViewById(R.id.usernamefourth);
        TextView passwordTextView = (TextView) getActivity().findViewById(R.id.passwordfourth);


        usernameTextView.setText(packedStr[0]);
        passwordTextView.setText(packedStr[1]);

    }
}

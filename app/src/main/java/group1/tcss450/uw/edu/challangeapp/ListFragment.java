package group1.tcss450.uw.edu.challangeapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import group1.tcss450.uw.edu.challangeapp.model.Setlist;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private OnFragmentInteractionListener mListener;
    ListView mListView;
    Setlist[] mSetlist;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSetlist = (Setlist[]) getArguments().get("key");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        if (getArguments() != null) {
            mSetlist = (Setlist[]) getArguments().get("key");
        }
        mListView = (ListView) v.findViewById(R.id.list_listview);
        if (mSetlist == null){
            return v;
        }
        String[] listItem = new String[mSetlist.length];
        for (int i = 0; i < mSetlist.length; i++){
            listItem[i] = mSetlist[i].getShowdate();
        }

        mListView.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listItem));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Setlist[] setlist =new Setlist[1];
                setlist[0] = mSetlist[position];
                SetlistFragment setlistFragment;
                setlistFragment = new SetlistFragment();
                Bundle args = new Bundle();
                args.putSerializable(setlistFragment.KEY, setlist);
                setlistFragment.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, setlistFragment)
                        .addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });


        return v;
    }

    public void updateContent(Setlist[] setlist) {

        String[] listItem = new String[setlist.length];
        for (int i = 0; i < setlist.length; i++){
            listItem[i] = setlist[i].getShowdate();
        }

        mListView.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listItem));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Setlist[] setlist =new Setlist[1];
                setlist[0] = setlist[position];
                SetlistFragment setlistFragment;
                setlistFragment = new SetlistFragment();
                Bundle args = new Bundle();
                args.putSerializable(setlistFragment.KEY, setlist);
                setlistFragment.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, setlistFragment)
                        .addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });

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
        void onFragmentInteraction(Setlist[] setlists);
    }
}

package group1.tcss450.uw.edu.challangeapp;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
        implements FirstFragment.OnFragmentInteractionListener,
                   SecondFragment.OnFragmentInteractionListener,
                   ThirdFragment.OnFragmentInteractionListener,
                    FourthFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            if (findViewById(R.id.fragmentContainer) != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, new FirstFragment())
                        .commit();
            }
        }

    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Fragment fragment, String username, String password) {

        FourthFragment fourthFragment = new FourthFragment();
        Bundle args = new Bundle();
        String[] packedStr = new String[2];
        packedStr[0] = username;
        packedStr[1] = password;
        args.putSerializable(FourthFragment.KEY, packedStr);
        fourthFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fourthFragment)
                .addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
}

package com.popularmovies.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.popularmovies.R;
import com.popularmovies.presenter.broadcasts.NetworkStateChangeReceiver;

import static com.popularmovies.presenter.broadcasts.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener{

    private TextView errorTextView;
    View fragmentMainView;
    private String TAG = "MainActivityLOG";
    private String tag = "moviesFragmentTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        errorTextView = (TextView) findViewById(R.id.tv_error);
        fragmentMainView = findViewById(R.id.fragment_container);

        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
//                Log.d(TAG, "onReceive: " + isNetworkAvailable);
                if (isNetworkAvailable){
                    showFragment();
                } else {
                    showError();
                }
            }
        }, intentFilter);

        // Create new fragment and transaction
        Fragment mainFragment = MainFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, mainFragment, tag);
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void showFragment(){
        fragmentMainView.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.GONE);
    }

    private void showError(){
        fragmentMainView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMovieClicked(int movieId) {
        Log.d(TAG, "onMovieClicked: ");
        // Create new fragment and transaction]
        Fragment movieDetailsFragment = MovieDetailsFragment.newInstance(movieId);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, movieDetailsFragment, tag);
        transaction.addToBackStack(tag);
        // Commit the transaction
        transaction.commit();
    }
}

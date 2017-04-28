package com.popularmovies.presenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.popularmovies.R;
import com.popularmovies.model.MoviesModel;
import com.popularmovies.presenter.api.MoviesClient;
import com.popularmovies.view.IMoviesAdapterView;
import com.popularmovies.view.MainFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.popularmovies.presenter.broadcasts.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;
import static com.popularmovies.presenter.broadcasts.NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION;
import static com.popularmovies.view.MainFragment.POPULAR;

/**
 * PopularMovies Created by Mohammed Fareed on 4/7/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {
    private MoviesModel mMovieModel;
    private IMoviesAdapterView mListener;
    private String TAG = "MoviesAdapterLOG";

    public MoviesAdapter(final Context context, String filter, IMoviesAdapterView listener) {
        mListener = listener;
        MoviesClient mMovieClient = new MoviesClient();

        Call<MoviesModel> call;
        if (filter == POPULAR) {
            call = mMovieClient.getCallpopular();
        } else {
            call = mMovieClient.getCallTopRated();
        }

        retrieveData(context, call);
    }

    private void retrieveData(final Context context, Call<MoviesModel> call) {
        if (MainFragment.mSwipeRefreshLayout != null)
            MainFragment.isRefreshing(true);
        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
                networkStateIntent.putExtra(IS_NETWORK_AVAILABLE,  true);
                LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent);

                mMovieModel = response.body();
                if (MainFragment.mSwipeRefreshLayout != null)
                    MainFragment.isRefreshing(false);
                if (mMovieModel != null) {
                    notifyDataSetChanged();
                } else
                    Log.e(TAG, "onResponse: NULL" + mMovieModel.getPage());
            }
            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());

                Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
                networkStateIntent.putExtra(IS_NETWORK_AVAILABLE,  false);
                LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent);
            }
        });
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        holder.bind(mMovieModel, mListener);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}

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
import com.popularmovies.presenter.IMovies;
import com.popularmovies.presenter.api.MoviesClient;
import com.popularmovies.view.IMoviesAdapterView;
import com.popularmovies.view.MainFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.popularmovies.presenter.broadcasts.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;
import static com.popularmovies.presenter.broadcasts.NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION;
import static com.popularmovies.view.MainFragment.POPULAR;

/**
 * PopularMovies Created by Mohammed Fareed on 4/7/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> implements IMovies {
    private List<MoviesModel.ResultsBean> mMoviesList;
    private IMoviesAdapterView mListener;
    private Context mContext;
    private MoviesClient mMovieClient = new MoviesClient();
    private String TAG = "MoviesAdapterLOG";

    public MoviesAdapter(final Context context, IMoviesAdapterView listener) {
        mListener = listener;
        mContext = context;
    }

    @Override
    public void retrieveData(String filter) {
        if (MainFragment.mSwipeRefreshLayout != null)
            MainFragment.isRefreshing(true);

        Call<MoviesModel> call;
        if (filter.equals(POPULAR)) {
            call = mMovieClient.getCallpopular();
        } else {
            call = mMovieClient.getCallTopRated();
        }

        mMoviesList = new ArrayList<>();
        call.clone().enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
                networkStateIntent.putExtra(IS_NETWORK_AVAILABLE,  true);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(networkStateIntent);

                mMoviesList = response.body().getResults();
                Log.d(TAG, "onResponse: " + mMoviesList.get(0).getTitle());
                if (MainFragment.mSwipeRefreshLayout != null)
                    MainFragment.isRefreshing(false);
                if (mMoviesList != null) {
                    notifyDataSetChanged();
                } else
                    Log.e(TAG, "onResponse: NULL");
            }
            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
                networkStateIntent.putExtra(IS_NETWORK_AVAILABLE,  false);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(networkStateIntent);
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
        holder.bind(mMoviesList, mListener);
    }

    @Override
    public int getItemCount() {
        return (mMoviesList != null) ? mMoviesList.size() : 0;
    }

    private String previousSort = "";
    @Override
    public void loadMoreMoviesOnScroll(int page, String sort) {
        Call<MoviesModel> call = mMovieClient.getCallMoviesPage(sort, page);

        if (MainFragment.mSwipeRefreshLayout != null)
            MainFragment.isRefreshing(true);
        if (!sort.equals(previousSort)) {
            previousSort = sort;
        }
        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
                networkStateIntent.putExtra(IS_NETWORK_AVAILABLE,  true);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(networkStateIntent);

                // append the new list and call notifyDataSetChanged
                mMoviesList.addAll(response.body().getResults());
                Log.d(TAG, "onResponse: " + mMoviesList.size());
                notifyDataSetChanged();

                if (MainFragment.mSwipeRefreshLayout != null)
                    MainFragment.isRefreshing(false);
            }
            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());

                Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
                networkStateIntent.putExtra(IS_NETWORK_AVAILABLE,  false);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(networkStateIntent);
            }
        });
    }
}

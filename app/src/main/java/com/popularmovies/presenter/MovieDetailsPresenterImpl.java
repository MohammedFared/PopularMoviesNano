package com.popularmovies.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.popularmovies.model.MovieDetailModel;
import com.popularmovies.presenter.api.MoviesClient;
import com.popularmovies.view.IMovieDetailsView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.popularmovies.presenter.broadcasts.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;
import static com.popularmovies.presenter.broadcasts.NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION;

/**
 * PopularMovies Created by Mohammed Fareed on 4/28/2017.
 */

public class MovieDetailsPresenterImpl implements IMovieDetailsPresenter {

    IMovieDetailsView mIView;
    Context mContext;
    private String TAG = "MovieDetailsPresImplLOG";

    public MovieDetailsPresenterImpl(Context context, IMovieDetailsView iMovieDetailsView, int movieId){
        mIView = iMovieDetailsView;
        mContext = context;

        retrieveData(movieId);
    }

    private void retrieveData(int movieId) {
        MoviesClient moviesClient = new MoviesClient();
        Call<MovieDetailModel> call = moviesClient.getCallMovie(movieId);

        mIView.showProgressDialog();
        call.enqueue(new Callback<MovieDetailModel>() {
            @Override
            public void onResponse(Call<MovieDetailModel> call, Response<MovieDetailModel> response) {
                mIView.hideProgressDialog();
                // update the network status
                Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
                networkStateIntent.putExtra(IS_NETWORK_AVAILABLE,  true);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(networkStateIntent);

                MovieDetailModel movieDetailModel = response.body();

                if (movieDetailModel != null) {
                    String title = movieDetailModel.getOriginal_title();
                    String date = movieDetailModel.getRelease_date();
                    double rate = movieDetailModel.getVote_average();
                    String posterPath = movieDetailModel.getPoster_path();
                    String overView = movieDetailModel.getOverview();
                    int length = movieDetailModel.getRuntime();
                    Log.d(TAG, "onResponse: " + title);
                    mIView.updateViewsWithValues(title, date, rate, posterPath, overView, length);
                }
            }
            @Override
            public void onFailure(Call<MovieDetailModel> call, Throwable t) {
                mIView.hideProgressDialog();
                //update the network status
                Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
                networkStateIntent.putExtra(IS_NETWORK_AVAILABLE,  false);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(networkStateIntent);
            }
        });
    }
}

package com.popularmovies.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.popularmovies.model.MovieDetailModel;
import com.popularmovies.model.MovieReviewsModel;
import com.popularmovies.model.MovieTrailersModel;
import com.popularmovies.presenter.api.MoviesClient;
import com.popularmovies.view.IMovieDetailsView;

import java.util.ArrayList;

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
    Boolean isTrailersViewed = false
            , isReviewsViewed = false;

    public MovieDetailsPresenterImpl(Context context, IMovieDetailsView iMovieDetailsView, int movieId){
        mIView = iMovieDetailsView;
        mContext = context;

        retrieveMovieData(movieId);
    }

    @Override
    public void getTrailers(final int movieId) {
        if (!isTrailersViewed) {
            MoviesClient moviesClient = new MoviesClient();
            Call<MovieTrailersModel> call = moviesClient.getCallMovieTrailers(movieId);

            mIView.showProgressDialog();
            call.enqueue(new Callback<MovieTrailersModel>() {
                @Override
                public void onResponse(Call<MovieTrailersModel> call, Response<MovieTrailersModel> response) {
                    mIView.hideProgressDialog();
                    MovieTrailersModel movieTrailersModel = response.body();

                    if (movieTrailersModel != null) {
                        isTrailersViewed = true;
                        ArrayList<String> trailersKeys = new ArrayList<>(), trailersNames = new ArrayList<>();
                        for (MovieTrailersModel.ResultsBean movieTrailer : movieTrailersModel.getResults()) {
                            trailersKeys.add(movieTrailer.getKey());
                            trailersNames.add(movieTrailer.getName());
                        }
                        mIView.trailersResponse(trailersKeys, trailersNames);
                    } else
                        Toast.makeText(mContext, "Movie has no trailers", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<MovieTrailersModel> call, Throwable t) {
                    mIView.hideProgressDialog();

                }
            });
        }
    }

    @Override
    public void getReviews(final int movieId) {
        if (!isReviewsViewed) {
            MoviesClient moviesClient = new MoviesClient();
            Call<MovieReviewsModel> call = moviesClient.getCallMovieReviews(movieId);
            mIView.showProgressDialog();
            call.enqueue(new Callback<MovieReviewsModel>() {
                @Override
                public void onResponse(Call<MovieReviewsModel> call, Response<MovieReviewsModel> response) {
                    mIView.hideProgressDialog();
                    MovieReviewsModel movieReviews = response.body();

                    if (movieReviews != null) {
                        isReviewsViewed = true;
                        ArrayList<String> authors = new ArrayList<>(), contents = new ArrayList<>();
                        for (MovieReviewsModel.ResultsBean movieReview : movieReviews.getResults()) {
                            authors.add(movieReview.getAuthor());
                            contents.add(movieReview.getContent());
                        }
                        mIView.reviewsResponse(authors, contents);
                    } else
                        Toast.makeText(mContext, "Movie has no reviews", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<MovieReviewsModel> call, Throwable t) {
                    mIView.hideProgressDialog();
                }
            });
        }
    }

    private void retrieveMovieData(int movieId) {
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
                    mIView.updateViewsWithValues(title, date, rate/2, posterPath, overView, length);
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

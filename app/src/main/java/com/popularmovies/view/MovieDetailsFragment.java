package com.popularmovies.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.popularmovies.R;
import com.popularmovies.Utils;
import com.popularmovies.presenter.IMovieDetailsPresenter;
import com.popularmovies.presenter.MovieDetailsPresenterImpl;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends Fragment implements IMovieDetailsView{
    // the fragment initialization parameters
    private static final String ARG_MOVIE_ID = "movie_id";
    private String TAG = "MovieDetailsFragmentLOG";
    private TextView nameTextView, yearTextView, ratingTextView, descTextView, trailersButtonTV,
            reviewsButtonTV, lengthTextView;
    private Button addToFavorites;
    private ImageView moviePosterImageView;

    private IMovieDetailsPresenter mIMovieDetailsPresenter;

    private int movieId;

    private View mView;
    private AppCompatRatingBar ratingView;


    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsFragment newInstance(int movieId) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            movieId = getArguments().getInt(ARG_MOVIE_ID);
        }
        mIMovieDetailsPresenter = new MovieDetailsPresenterImpl(getContext(), this, movieId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView  = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        nameTextView = (TextView) mView.findViewById(R.id.tv_movie_name);
        descTextView = (TextView) mView.findViewById(R.id.tv_movie_desc);
        lengthTextView = (TextView) mView.findViewById(R.id.tv_movie_length);
        yearTextView = (TextView) mView.findViewById(R.id.tv_movie_year);
        ratingTextView = (TextView) mView.findViewById(R.id.tv_rating);
        ratingView = (AppCompatRatingBar) mView.findViewById(R.id.appCompatRatingBar_movie_rating);
        moviePosterImageView = (ImageView) mView.findViewById(R.id.iv_movie_poster);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // called from the presenter with the data to update the views
    @Override
    public void updateViewsWithValues(String title, String date, double rate, String posterPath, String overView, int length) {
        rate/=2;
        nameTextView.setText(title);
        descTextView.setText(overView);
        lengthTextView.setText(String.valueOf(length) + "min");
        ratingTextView.setText(String.valueOf(rate));
        yearTextView.setText(date);

        ratingView.setRating((float) rate);

        Picasso.with(getContext())
                .load(Utils.IMAGEURLBASE + posterPath)
                .into(moviePosterImageView);
    }

    private ProgressDialog mProgressDialog;
    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage("Loading ...");
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.show();
    }
    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}

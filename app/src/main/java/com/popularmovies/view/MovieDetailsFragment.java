package com.popularmovies.view;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.popularmovies.R;
import com.popularmovies.Utils;
import com.popularmovies.presenter.IMovieDetailsPresenter;
import com.popularmovies.presenter.MovieDetailsPresenterImpl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends Fragment implements IMovieDetailsView {
    // the fragment initialization parameters
    private static final String ARG_MOVIE_ID = "movie_id";
    private String TAG = "MovieDetailsFragmentLOG";
    private TextView nameTextView, yearTextView, ratingTextView, descTextView, trailersTV,
            reviewsTV, lengthTextView;
    private Button addToFavBtn;
    private ImageView moviePosterImageView;
    private LinearLayout reviewsLinearLayout, trailersLinearLayout;

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
        if (getArguments() != null) {
            movieId = getArguments().getInt(ARG_MOVIE_ID);
        }
        mIMovieDetailsPresenter = new MovieDetailsPresenterImpl(getContext(), this, movieId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        nameTextView = (TextView) mView.findViewById(R.id.tv_movie_name);
        descTextView = (TextView) mView.findViewById(R.id.tv_movie_desc);
        lengthTextView = (TextView) mView.findViewById(R.id.tv_movie_length);
        yearTextView = (TextView) mView.findViewById(R.id.tv_movie_year);
        ratingTextView = (TextView) mView.findViewById(R.id.tv_rating);
        ratingView = (AppCompatRatingBar) mView.findViewById(R.id.appCompatRatingBar_movie_rating);
        moviePosterImageView = (ImageView) mView.findViewById(R.id.iv_movie_poster);
        trailersTV = (TextView) mView.findViewById(R.id.tv_trailers);
        reviewsTV = (TextView) mView.findViewById(R.id.tv_reviews);
        addToFavBtn = (Button) mView.findViewById(R.id.btn_add_to_favorites);

        reviewsLinearLayout = (LinearLayout) mView.findViewById(R.id.ll_reviews);
        reviewsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReviewsViewClicked(view);
            }
        });
        trailersLinearLayout = (LinearLayout) mView.findViewById(R.id.ll_trailers);
        trailersLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTrailersViewClicked(view);
            }
        });
        addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddToFavClicked(view);
            }
        });

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // called from the presenter with the data to update the views
    @Override
    public void updateViewsWithValues(String title, String date, double rate, String posterPath, String overView, int length) {
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

    @Override
    public void trailersResponse(final ArrayList<String> trailersKeys, ArrayList<String> trailersNames) {
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < trailersKeys.size(); i++) {
            Button btn = new Button(getContext());
            btn.setId(i + 1);
            btn.setText(trailersNames.get(i));
            btn.setLayoutParams(lp);
            final int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String videoKey = trailersKeys.get(finalI);
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" +
                                videoKey)));
                    } catch (ActivityNotFoundException ex) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" +
                                videoKey)));
                    }
                }
            });
            trailersLinearLayout.addView(btn);
        }
    }

    @Override
    public void reviewsResponse(ArrayList<String> authors, ArrayList<String> contents) {
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < authors.size(); i++) {
            TextView textViewAuthor = new TextView(getContext());
            textViewAuthor.setText(authors.get(i));
            textViewAuthor.setLayoutParams(lp);
            textViewAuthor.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
            reviewsLinearLayout.addView(textViewAuthor);

            TextView textViewContent = new TextView(getContext());
            textViewContent.setText(contents.get(i));
            textViewContent.setLayoutParams(lp);
            textViewContent.setPadding(16, 8, 8, 8);
            reviewsLinearLayout.addView(textViewContent);
        }
    }

    void onTrailersViewClicked(View view) {
        mIMovieDetailsPresenter.getTrailers(movieId);
    }


    void onReviewsViewClicked(View view) {
        mIMovieDetailsPresenter.getReviews(movieId);
    }

    //TODO: On add to favorites button clicked
    void onAddToFavClicked(View view) {

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

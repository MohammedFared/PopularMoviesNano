package com.popularmovies.presenter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.popularmovies.R;
import com.popularmovies.Utils;
import com.popularmovies.model.MoviesModel;
import com.popularmovies.view.IMoviesAdapterView;
import com.squareup.picasso.Picasso;

/**
 * PopularMovies Created by Mohammed Fareed on 4/28/2017.
 */

public class MoviesViewHolder extends RecyclerView.ViewHolder {
    private ImageView posterImageView;
    private TextView nameMovieView;
    private RatingBar ratingMovieView;

    public MoviesViewHolder(View itemView) {
        super(itemView);
        posterImageView = (ImageView) itemView.findViewById(R.id.iv_movieposter);
        nameMovieView = (TextView) itemView.findViewById(R.id.tv_name);
        ratingMovieView = (RatingBar) itemView.findViewById(R.id.rb_movie_rating);
    }

    public void bind(MoviesModel mMovieModel, final IMoviesAdapterView mListener) {

        if (mMovieModel != null) {
            final MoviesModel.ResultsBean movie = mMovieModel.getResults().get(getAdapterPosition());
            String movieName = movie.getTitle();
            double rate = movie.getVote_average();
            String poster = movie.getPoster_path();

            nameMovieView.setText(movieName);
            Picasso.with(itemView.getContext())
                    .load(Utils.IMAGEURLBASE + poster).into(posterImageView);
            ratingMovieView.setNumStars(5);
            ratingMovieView.setMax(5);
            ratingMovieView.setRating((float) rate / 2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int movieId = movie.getId();
                    mListener.onMovieClicked(movieId);
                }
            });
        }
    }
}
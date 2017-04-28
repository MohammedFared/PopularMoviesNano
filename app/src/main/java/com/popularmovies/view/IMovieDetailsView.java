package com.popularmovies.view;

/**
 * PopularMovies Created by Mohammed Fareed on 4/28/2017.
 */

public interface IMovieDetailsView {
    void showProgressDialog();
    void hideProgressDialog();
    void updateViewsWithValues(String title, String date, double rate, String posterPath, String overView, int length);
}

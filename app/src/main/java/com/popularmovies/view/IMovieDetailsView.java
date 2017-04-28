package com.popularmovies.view;

import java.util.ArrayList;

/**
 * PopularMovies Created by Mohammed Fareed on 4/28/2017.
 */

public interface IMovieDetailsView {
    void showProgressDialog();
    void hideProgressDialog();
    void updateViewsWithValues(String title, String date, double rate, String posterPath, String overView, int length);
    void trailersResponse(ArrayList<String> trailersKeys, ArrayList<String> trailersNames);
    void reviewsResponse(ArrayList<String> authors, ArrayList<String> contents);
}

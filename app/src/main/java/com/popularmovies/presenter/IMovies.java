package com.popularmovies.presenter;

/**
 * PopularMovies Created by Mohammed Fareed on 4/28/2017.
 */

public interface IMovies {
    void loadMoreMoviesOnScroll(int page, String sort);
    void retrieveData(String filter);
}

package com.popularmovies.presenter.api;

import com.popularmovies.BuildConfig;
import com.popularmovies.model.MovieDetailModel;
import com.popularmovies.model.MovieReviewsModel;
import com.popularmovies.model.MovieTrailersModel;
import com.popularmovies.model.MoviesModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * PopularMovies Created by Mohammed Fareed on 4/7/2017.
 */
public class MoviesClient {
    private Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create());

    private Retrofit retrofit = builder.build();

    private MoviesClientInterface client = retrofit.create(MoviesClientInterface.class);

    private Call<MoviesModel> callpopular = client.popularMovies(BuildConfig.MOVIE_API_KEY);
    private Call<MoviesModel> callTopRated = client.topRatedMovies(BuildConfig.MOVIE_API_KEY);

    public Call<MoviesModel> getCallpopular() {
        return callpopular;
    }
    public Call<MoviesModel> getCallTopRated() {
        return callTopRated;
    }
    public Call<MovieDetailModel> getCallMovie(int movieId) {
        return client.movie(movieId, BuildConfig.MOVIE_API_KEY);
    }
    public Call<MovieTrailersModel> getCallMovieTrailers(int movieId) {
        return client.movieTrailers(movieId, BuildConfig.MOVIE_API_KEY);
    }
    public Call<MovieReviewsModel> getCallMovieReviews(int movieId) {
        return client.movieReviews(movieId, BuildConfig.MOVIE_API_KEY);
    }

    public Call<MoviesModel> getCallMoviesPage(String sort, int page) {
        return client.moviesPageAndSort(sort, page, BuildConfig.MOVIE_API_KEY);
    }

    private interface MoviesClientInterface {
        @GET("movie/popular")
        Call<MoviesModel> popularMovies(@Query("api_key") String apiKey);

        @GET("movie/top_rated")
        Call<MoviesModel> topRatedMovies(@Query("api_key") String apiKey);

        @GET("movie/{sort}")
        Call<MoviesModel> moviesPageAndSort(@Path("sort") String sort, @Query("page") int page,@Query("api_key") String apiKey);

        @GET("movie/{movieId}")
        Call<MovieDetailModel> movie(@Path("movieId") int movieId, @Query("api_key") String apiKey);

        @GET("movie/{movieId}/videos")
        Call<MovieTrailersModel> movieTrailers(@Path("movieId") int movieId, @Query("api_key") String apiKey);

        @GET("movie/{movieId}/reviews")
        Call<MovieReviewsModel> movieReviews(@Path("movieId") int movieId, @Query("api_key") String apiKey);
    }
}

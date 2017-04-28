package com.popularmovies.model;

/**
 * PopularMovies Created by Mohammed Fareed on 4/18/2017.
 */

public class MovieDetailModel {

    /**
     * adult : false
     * backdrop_path : /5pAGnkFYSsFJ99ZxDIYnhQbQFXs.jpg
     * belongs_to_collection : {"id":748,"name":"X-Men Collection","poster_path":"/1Zo4J5SAni8lyXt7NxJwJZ0f0ip.jpg","backdrop_path":"/Abnosho2v3bcdvDww6T7Hfeczm1.jpg"}
     * budget : 97000000
     * genres : [{"id":28,"name":"Action"},{"id":18,"name":"Drama"},{"id":878,"name":"Science Fiction"}]
     * homepage : http://www.foxmovies.com/movies/logan
     * id : 263115
     * imdb_id : tt3315342
     * original_language : en
     * original_title : Logan
     * overview : In the near future, a weary Logan cares for an ailing Professor X in a hide out on the Mexican border. But Logan's attempts to hide from the world and his legacy are up-ended when a young mutant arrives, being pursued by dark forces.
     * popularity : 78.627847
     * poster_path : /45Y1G5FEgttPAwjTYic6czC9xCn.jpg
     * production_companies : [{"name":"Twentieth Century Fox Film Corporation","id":306},{"name":"Donners' Company","id":431},{"name":"Marvel Entertainment","id":7505},{"name":"TSG Entertainment","id":22213}]
     * production_countries : [{"iso_3166_1":"US","name":"United States of America"}]
     * release_date : 2017-02-28
     * revenue : 597833299
     * runtime : 137
     * spoken_languages : [{"iso_639_1":"es","name":"Español"},{"iso_639_1":"en","name":"English"}]
     * status : Released
     * tagline : His Time Has Come
     * title : Logan
     * video : false
     * vote_average : 7.5
     * vote_count : 2450
     */

    private boolean adult;
    private String homepage;
    private int id;
    private int runtime;
    private String original_title;
    private String overview;
    private String poster_path;
    private String release_date;
    private double vote_average;


    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }
}

package com.popularmovies.model;

import java.util.List;

/**
 * PopularMovies Created by Mohammed Fareed on 4/7/2017.
 */

public class MoviesModel {

    /**
     * page : 1
     * results : [{"poster_path":"/tWqifoYuwLETmmasnGHO7xBjEtt.jpg","adult":false,"overview":"A live-action adaptation of Disney's version of the classic 'Beauty and the Beast' tale of a cursed prince and a beautiful young woman who helps him break the spell.","release_date":"2017-03-17","id":321612,"original_title":"Beauty and the Beast","title":"Beauty and the Beast","backdrop_path":"/6aUWe0GSl69wMTSWWexsorMIvwU.jpg","vote_count":1333,"vote_average":7.1}]
     * total_results : 19469
     * total_pages : 974
     */

    private int page;
    private int total_results;
    private int total_pages;
    private List<ResultsBean> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * poster_path : /tWqifoYuwLETmmasnGHO7xBjEtt.jpg
         * adult : false
         * overview : A live-action adaptation of Disney's version of the classic 'Beauty and the Beast' tale of a cursed prince and a beautiful young woman who helps him break the spell.
         * release_date : 2017-03-17
         * id : 321612
         * original_title : Beauty and the Beast
         * title : Beauty and the Beast
         * backdrop_path : /6aUWe0GSl69wMTSWWexsorMIvwU.jpg
         * vote_count : 1333
         * vote_average : 7.1
         */

        private String poster_path;
        private boolean adult;
        private String overview;
        private String release_date;
        private int id;
        private String original_title;
        private String title;
        private int vote_count;
        private double vote_average;

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }
    }
}

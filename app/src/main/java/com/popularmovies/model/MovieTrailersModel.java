package com.popularmovies.model;

import java.util.List;

/**
 * PopularMovies Created by Mohammed Fareed on 4/28/2017.
 */

public class MovieTrailersModel {

    /**
     * id : 263115
     * results : [{"id":"58cfc8499251415a61037481","iso_639_1":"en","iso_3166_1":"US","key":"XaE_9pfybL4","name":"Official Trailer #2 [UK]","site":"YouTube","size":1080,"type":"Trailer"},{"id":"58cfc820c3a36850fb033208","iso_639_1":"en","iso_3166_1":"US","key":"RH3OxVFvTeg","name":"Official Trailer 2","site":"YouTube","size":1080,"type":"Trailer"},{"id":"58f62dea92514127a600d8c3","iso_639_1":"en","iso_3166_1":"US","key":"Div0iP65aZo","name":"Official Trailer","site":"YouTube","size":1080,"type":"Trailer"}]
     */

    private int id;
    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 58cfc8499251415a61037481
         * iso_639_1 : en
         * iso_3166_1 : US
         * key : XaE_9pfybL4
         * name : Official Trailer #2 [UK]
         * site : YouTube
         * size : 1080
         * type : Trailer
         */

        private String id;
        private String key;
        private String name;
        private String site;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}

package com.popularmovies.model;

import java.util.List;

/**
 * PopularMovies Created by Mohammed Fareed on 4/28/2017.
 */

public class MovieReviewsModel {

    /**
     * id : 263115
     * page : 1
     * results : [{"id":"58bbf107c3a368666b032be5","author":"anythingbutfifi","content":"**LOGAN REVIEW: THE WOLVERINE GETS A SUPER SEND-OFF**\r\n\r\n\"Owing to its agitated hero\u2019s misfortunes through the ages, this is a film that\u2019s acutely aware of the dangers of emotional exploitation, and it spares its audience a similar fate. With Logan, Mangold and co-writer Scott Frank tell the definitive story of the Wolverine, in an involving and deeply satisfying series finale. It shows the fate of mutants when age starts to weary them, with stakes that feel real, and empathy that\u2019s earned.\"\r\n\r\nREAD THE FULL REVIEW AT SBS MOVIES: http://www.sbs.com.au/movies/review/logan-review-wolverine-gets-super-send","url":"https://www.themoviedb.org/review/58bbf107c3a368666b032be5"},{"id":"58c34453925141239c000e72","author":"Movie Queen41","content":"There may be some fine performances in this movie, but I honestly think the critics overrated this latest entry in the X-Men saga. The performances of Wolverine and Prof. X by Hugh Jackman and Patrick Stewart are extraordinary. They create a believable and loving father-son bond between their characters, with Logan caring for the ninety year old leader of the X-Men after a horrible event occurs at the Xavier school the year before. Stephen Merchant takes over the role of Caliban from Tómas Lemarquis, who played the character in X-Men Apocalypse, and transforms him into an ally to Logan and Charlies. Merchant is quite good in the role. But what really dragged down the movie is its nihilism. The other X-Men are completely missing from the film and mutants have been wiped out almost completely. A sense of doom and hopelessness looms over the film. This movie completely upends the warm and hopeful epilogue of Days of Future Past, where the X-Men were restored to life and glory and mutants weren't extinct after all. Death seems to stalk Logan and Charles wherever they go. There is also a conspicuous lack of significant female characters in this movie, too. The only female of note is Laura/ X-23, and she spends most of the film mute. There is also a complete lack of any strong or memorable villains. No one ever reaches the level of greatness like other X-Men villains such as Magneto or William Stryker. So, despite some good performances, this film is a bit overrated and also a little too bleak and depressing for my taste.","url":"https://www.themoviedb.org/review/58c34453925141239c000e72"},{"id":"58e21cbb925141281900a93d","author":"Chicken McFugget","content":"One of the best and most mature Marvel movies to date.\r\n\r\nThe first half of this movie is a huge delight and a huge thrill. Hugh Jackman and Patrick Stewart are so entertaining to watch as characters they've known for 17 years. They've taken their characters a step further here in a script that allows them to explore themselves emotionally more than ever before. This movie is strictly part-drama. Yes, that's right. Finally, a Marvel superhero movie you can confidently stamp the drama genre tag on.\r\n\r\nThe effects, stunts and gore are really quite shocking. You can really feel the weight of each character's immense power, and more importantly, the struggles that come along with them. This further fleshes out each character.\r\n\r\nThe movie starts to lose the enjoyability of its ensemble halfway through, and what happens after is what you might expect from a typical X-Men film. Action, sci-fi and more action/sci-fi. The villains are as interesting as they can be I suppose. They lack much development but are there to fuel the real story. Anyway, I can't say much else without spoiling the plot. I'll just say that I wish the movie would have taken a different direction.","url":"https://www.themoviedb.org/review/58e21cbb925141281900a93d"},{"id":"58e3b3c8925141281901f557","author":"Gimly","content":"If you don't like superhero movies, this is the superhero movie for you. And if you do like superhero movies? Watch _Logan_ anyway. It's bloody brilliant.\r\n\r\n_Final rating:★★★★ - An all round good movie with a little something extra._","url":"https://www.themoviedb.org/review/58e3b3c8925141281901f557"}]
     * total_pages : 1
     * total_results : 4
     */

    private int id;
    private int page;
    private int total_pages;
    private int total_results;
    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 58bbf107c3a368666b032be5
         * author : anythingbutfifi
         * content : **LOGAN REVIEW: THE WOLVERINE GETS A SUPER SEND-OFF**

         "Owing to its agitated hero’s misfortunes through the ages, this is a film that’s acutely aware of the dangers of emotional exploitation, and it spares its audience a similar fate. With Logan, Mangold and co-writer Scott Frank tell the definitive story of the Wolverine, in an involving and deeply satisfying series finale. It shows the fate of mutants when age starts to weary them, with stakes that feel real, and empathy that’s earned."

         READ THE FULL REVIEW AT SBS MOVIES: http://www.sbs.com.au/movies/review/logan-review-wolverine-gets-super-send
         * url : https://www.themoviedb.org/review/58bbf107c3a368666b032be5
         */

        private String id;
        private String author;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

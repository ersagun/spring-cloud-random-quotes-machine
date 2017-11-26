package shared;

import shared.wiki.Wiki;

public class TweetWiki {

    private Tweet tweet;
    private Wiki wiki;

    public TweetWiki() {
    }

    public TweetWiki(Tweet tweet, Wiki wiki) {
        this.tweet = tweet;
        this.wiki = wiki;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }
}
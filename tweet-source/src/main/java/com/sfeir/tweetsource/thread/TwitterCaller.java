package com.sfeir.tweetsource.thread;

import com.sfeir.tweetsource.channel.TweetSourceChannel;
import org.springframework.integration.support.MessageBuilder;
import shared.Tweet;
import shared.User;
import twitter4j.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwitterCaller implements Runnable {

    private User user;


    private TweetSourceChannel tweetSourceChannel;
    private AtomicInteger counter;
    private static final Logger LOGGER = Logger.getLogger(TwitterCaller.class.getName());
    private Twitter twitter = TwitterFactory.getSingleton();

    public TwitterCaller(TweetSourceChannel tweetSourceChannel, User user) {
        this.user = user;
        this.counter = new AtomicInteger();
        this.tweetSourceChannel = tweetSourceChannel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void run() {

  /*
        Query query = new Query("track:" + this.user.getSearchQuery());
        QueryResult result = null;
      try {
            result = this.twitter.search(query);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for (Status status : result.getTweets()) {
            Tweet tweet = new Tweet(this.counter.getAndIncrement(),
                    (status.getUser().getName() != null) ? status.getUser().getName() : "",
                    status.getUser().getFavouritesCount(),
                    (status.getText() != null) ? status.getText() : "",
                    status.getUser().getFollowersCount(),
                    this.user
            );

*/
        if (!Thread.currentThread().isInterrupted()) {
            boolean sended = this.tweetSourceChannel.output().send(MessageBuilder.withPayload(new Tweet(0, user.getName(), 0, "", 0, user)).setHeader("type", "tweet").build());
            if (sended)
                LOGGER.log(Level.INFO, "Message is sended to user " + user.getName());
            else LOGGER.log(Level.WARNING, "Message is not sended");

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                LOGGER.log(Level.WARNING, "Connection is stopped for " + user.getName());
            }
        } else {
            LOGGER.log(Level.WARNING, "Thread is stopped");
            Thread.currentThread().interrupt();
        }

    }
}
//}

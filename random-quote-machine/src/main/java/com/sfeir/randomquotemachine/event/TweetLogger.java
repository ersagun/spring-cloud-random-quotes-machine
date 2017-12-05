package com.sfeir.randomquotemachine.event;

import com.sfeir.randomquotemachine.channel.RandomQuoteMachineChannel;
import com.sfeir.randomquotemachine.service.SseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import shared.Tweet;
import shared.TweetStats;

import java.util.logging.Level;
import java.util.logging.Logger;

@EnableBinding(RandomQuoteMachineChannel.class)
public class TweetLogger {

    private static final Logger LOGGER = Logger.getLogger(TweetLogger.class.getName());
    private final RandomQuoteMachineChannel randomQuoteMachineChannel;
    private SseManager sseManager;

    @Autowired
    public TweetLogger(RandomQuoteMachineChannel randomQuoteMachineChannel, SseManager sseManager) {
        this.randomQuoteMachineChannel = randomQuoteMachineChannel;
        this.sseManager = sseManager;
    }


    @StreamListener(target = RandomQuoteMachineChannel.INPUT_USER_TWEET_DATA, condition = "headers['type']=='tweet-data'")
    public void sendTweetToSee(Tweet tweet) {
        LOGGER.log(Level.INFO, "Tweet are sending to " + tweet.getUser().getName());
        this.sseManager.sendTweetToSseUser(tweet);
    }

    @StreamListener(RandomQuoteMachineChannel.INPUT_USER_TWEET_STATS_DATA)
    public void sendTweetStatsToSee(TweetStats tweetStats) {
        LOGGER.log(Level.INFO, "Tweet Stats are sending to " + tweetStats.getUser().getName());
        this.sseManager.sendTweetStatsToSseUser(tweetStats);
    }
}

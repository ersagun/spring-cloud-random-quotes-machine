package com.sfeir.randomquotemachine.event;

import com.sfeir.randomquotemachine.channel.RandomQuoteMachineChannel;
import com.sfeir.randomquotemachine.service.SseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import shared.Tweet;

import java.io.IOException;
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


    /**
     * Streamed translation is transmitted to sse clients
     *
     * @param tweet
     */
    @StreamListener(target = RandomQuoteMachineChannel.INPUT, condition = "headers['type']=='tweet'")
    public void sendTweetToSee(Tweet tweet) {
        try {
            if(this.sseManager.sseContainsUser(tweet.getUser())) {
                LOGGER.log(Level.INFO, "Tweet: " + tweet + " is sending to sse");
                this.sseManager.sendTweetToSseUser(tweet);
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Tried to send to user but not found");
        }
    }


    /**
     * Streamed translation is transmitted to sse clients
     * @param translation
     */
/*
    @StreamListener(RandomQuoteMachineChannel.INPUTSTATS)
    public void logTranslation(TweetStatistics tweetStatistics) {
        LOGGER.log(Level.INFO,"Translation: "+tweetStatistics+" received in queue");

        SseController.sseTranslation.forEach((k, sse) -> {
            try {
                if(tweetStatistics.getUser().getId() == k.getId())
                    sse.send(tweetStatistics);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
*/


    /**
     * Streamed translation is transmitted to sse clients
     * @param translation
     */
/*    @StreamListener(target = RandomQuoteMachineChannel.INPUT, condition = "headers['type']=='translation'")
    public void logTranslation(Translation translation) {
        LOGGER.log(Level.INFO,"Translation: "+translation+" received in queue");

        SseController.sseTranslation.forEach((k, sse) -> {
            try {
                sse.send(translation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }*/

    /**
     * Streamed tweet is transmitted to sse clients
     * @param tweet
     */
/*    @StreamListener(target = RandomQuoteMachineChannel.INPUT, condition = "headers['type']!='translation'")
    public void logTweet(Tweet tweet) {
        LOGGER.log(Level.INFO,"Tweet: "+tweet+" received in queue");

        SseController.sseTweet.forEach((k, sse) -> {
            try {
                sse.send(tweet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }*/


    /**
     * Streamed wiki is transmitted to sse clients
     * @param wiki
     */
/*    @StreamListener(RandomQuoteMachineChannel.INPUTWIKI)
    public void logWiki(Wiki wiki) {
        System.out.println("hello c'est un wiki :" + wiki);
        SseController.sseWiki.forEach((k, sse) -> {
            try {
                sse.send(wiki);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }*/

/*        SseController.sseTweet.forEach((k, sse) -> {
            try {
                System.out.println(tweet.getUser().getId() +" "+k.getId());
                if(tweet.getUser().getId() == k.getId()) {
                    sse.send(tweet);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
}

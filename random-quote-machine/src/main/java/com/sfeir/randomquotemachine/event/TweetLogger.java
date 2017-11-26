package com.sfeir.randomquotemachine.event;

import com.sfeir.randomquotemachine.channel.RandomQuoteMachineChannel;
import com.sfeir.randomquotemachine.controller.SseController;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import shared.Translation;
import shared.Tweet;
import shared.wiki.Wiki;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableBinding(RandomQuoteMachineChannel.class)
public class TweetLogger {

    private static final Logger LOGGER = Logger.getLogger( TweetLogger.class.getName() );


    /**
     * Streamed translation is transmitted to sse clients
     * @param translation
     */
    @StreamListener(target = RandomQuoteMachineChannel.INPUT, condition = "headers['type']=='translation'")
    public void logTranslation(Translation translation) {
        LOGGER.log(Level.INFO,"Translation: "+translation+" received in queue");

        SseController.sseTranslation.forEach((k, sse) -> {
            try {
                sse.send(translation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Streamed tweet is transmitted to sse clients
     * @param tweet
     */
    @StreamListener(target = RandomQuoteMachineChannel.INPUT, condition = "headers['type']!='translation'")
    public void logTweet(Tweet tweet) {
        LOGGER.log(Level.INFO,"Tweet: "+tweet+" received in queue");

        SseController.sseTweet.forEach((k, sse) -> {
            try {
                sse.send(tweet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * Streamed wiki is transmitted to sse clients
     * @param wiki
     */
    @StreamListener(RandomQuoteMachineChannel.INPUTWIKI)
    public void logWiki(Wiki wiki) {
        System.out.println("hello c'est un wiki :" + wiki);
        SseController.sseWiki.forEach((k, sse) -> {
            try {
                sse.send(wiki);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

package com.sfeir.randomquotemachine.event;

import com.sfeir.randomquotemachine.channel.RandomQuoteMachineChannel;
import com.sfeir.randomquotemachine.controller.SseController;
import com.sfeir.randomquotemachine.model.Tweet;
import com.sfeir.randomquotemachine.model.wiki.Wiki;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.io.IOException;

@EnableBinding(RandomQuoteMachineChannel.class)
public class TweetLogger {

    @StreamListener(target = RandomQuoteMachineChannel.INPUT, condition = "headers['type']=='translation'")
    public void logTweet(Tweet tweet) {
        System.out.println("hello c'est un tweet :" + tweet);

        SseController.sseTranslation.forEach((k, sse) -> {
            try {
                sse.send(tweet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @StreamListener(target = RandomQuoteMachineChannel.INPUT, condition = "headers['type']=='wikipedia'")
    public void logWiki(Wiki wiki) {
        SseController.sseTranslation.forEach((k, sse) -> {
            try {
                sse.send(wiki);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

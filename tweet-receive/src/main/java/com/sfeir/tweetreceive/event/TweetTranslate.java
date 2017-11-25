package com.sfeir.tweetreceive.event;

import com.sfeir.tweetreceive.channel.TweetTranslateChannel;
import com.sfeir.tweetreceive.model.Translation;
import com.sfeir.tweetreceive.model.Tweet;
import com.sfeir.tweetreceive.model.wiki.Wiki;
import com.sfeir.tweetreceive.service.WikipediaService;
import com.sfeir.tweetreceive.service.YandexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableBinding(TweetTranslateChannel.class)
public class TweetTranslate {

    @Autowired
    TweetTranslateChannel tweetTranslateChannel;

    @Autowired
    YandexService yandexService;

    @Autowired
    WikipediaService wikipediaService;


    /**
     * Receive Tweet, translate for 4 languages, get Wikipedia content
     * an send translation to queue with type translation and wikipedia content
     * with wikipedia type
     *
     * @param tweet
     * @return
     */
    @StreamListener(TweetTranslateChannel.INPUT)
    public void translation(Tweet tweet) {
        List<String> translates = new ArrayList<String>();
        translates.addAll(Arrays.asList("en-fr", "en-ru", "en-it", "en-tr"));
        translates.stream().forEach(x -> this.sendTranslation(this.yandexService.translateByType(tweet.getWords(), x)));
        String name = tweet.getWords().split("-")[1];
        System.out.println(name);
        Wiki wiki = this.wikipediaService.getFromWikipedia(name);
        this.sendWikipedia(wiki);
    }


    public void sendTranslation(Translation translate) {
        this.tweetTranslateChannel.output().send(MessageBuilder.withPayload(translate)
                .setHeader("type", "translation")
                .build());
    }

    public void sendWikipedia(Wiki wiki) {
        this.tweetTranslateChannel.output().send(MessageBuilder.withPayload(wiki)
                .setHeader("type", "wikipedia")
                .build());
    }


    //EXPLANATION :

    /*

    @StreamListener(TweetTranslateChannel.INPUT)
    @SendTo(TweetTranslateChannel.OUTPUT)
    public Translation translation(Tweet tweet) {
        System.out.println(tweet);
        Translation translate = new Translation();
        return translate;
    }

      @Transformer(inputChannel = TweetTranslateChannel.INPUT, outputChannel = TweetTranslateChannel.OUTPUT)
    public Translation transform(String tweet) {
        return new Translation();
    }

    */
}


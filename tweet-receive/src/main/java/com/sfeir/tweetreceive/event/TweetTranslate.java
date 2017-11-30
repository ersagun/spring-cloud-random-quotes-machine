package com.sfeir.tweetreceive.event;

import com.sfeir.tweetreceive.channel.TweetTranslateChannel;
import com.sfeir.tweetreceive.service.WikipediaService;
import com.sfeir.tweetreceive.service.YandexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.handler.annotation.SendTo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import shared.Translation;
import shared.Tweet;
import shared.TweetStatistics;
import shared.User;
import shared.wiki.Wiki;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableBinding(TweetTranslateChannel.class)
public class TweetTranslate {


    private TweetTranslateChannel tweetTranslateChannel;

    private YandexService yandexService;

    private WikipediaService wikipediaService;

    private static final Logger LOGGER = Logger.getLogger( TweetTranslate.class.getName() );

    @Autowired
    public TweetTranslate(TweetTranslateChannel tweetTranslateChannel, YandexService yandexService, WikipediaService wikipediaService) {
        this.tweetTranslateChannel = tweetTranslateChannel;
        this.yandexService = yandexService;
        this.wikipediaService = wikipediaService;
    }

    /**
     * Receive Tweet, translate for 4 languages, get Wikipedia content
     * an send translation to queue with type translation and wikipedia content
     * with wikipedia type
     *
     * @param tweet
     * @return void
     */

 /*
    @StreamListener(TweetTranslateChannel.INPUT)
    public void translation(TweetStatus tweet) {
        LOGGER.log(Level.INFO,"Tweet translation started for: " + tweet);
      List<String> translates = new ArrayList<String>();
        translates.addAll(Arrays.asList("en-fr", "en-ru", "en-it", "en-tr"));
        translates.stream().forEach(x -> this.sendTranslation(this.yandexService.translateByType(tweet.getWords(), x)));
        String name = tweet.getWords().split("-")[1];
        System.out.println(name);
        Wiki wiki = this.wikipediaService.getFromWikipedia(name);
        this.sendWikipedia(wiki);
    }*/




    @StreamListener(TweetTranslateChannel.INPUT)
    @SendTo(TweetTranslateChannel.OUTPUT)
    public  Flux<TweetStatistics> translation(Flux<Tweet> tweet) {
         return tweet.window(Duration.ofSeconds(10), Duration.ofSeconds(10))
                .flatMap(window -> window.groupBy(tweetValue -> tweetValue.getUser().getSearchQuery())
                        .flatMap(groupTweet -> calculateStatisticsForTweets(groupTweet)));
    }


    private Mono<TweetStatistics> calculateStatisticsForTweets(GroupedFlux<String, Tweet> group) {
        System.out.println("called");
        return group
                .reduce(new TweetStatistics(0, 0,0,new User()), (ts, d) -> {
                        return new TweetStatistics(ts.getNumberOfTweet() + 1, ts.getAverageNumberOfCharacterByTweet() + d.getText().length(), d.getId(),d.getUser());})
                .map(accumulator -> new TweetStatistics((accumulator.getNumberOfTweet()),(accumulator.getAverageNumberOfCharacterByTweet()/accumulator.getNumberOfTweet()), accumulator.getAverageAge(),accumulator.getUser()));
    }














public void sendTranslation(Translation translate) {
        LOGGER.log(Level.INFO,"Translation sending: " + translate);
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


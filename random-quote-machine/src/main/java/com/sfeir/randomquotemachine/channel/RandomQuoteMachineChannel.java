package com.sfeir.randomquotemachine.channel;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface RandomQuoteMachineChannel {

    String OUTPUT_SUBSCRIPTION_USER_TWEET = "output-subscription-user-tweet";
    @Output(RandomQuoteMachineChannel.OUTPUT_SUBSCRIPTION_USER_TWEET)
    MessageChannel outputSubscriptionUserTweet();

    String OUTPUT_SUBSCRIPTION_USER_TWEET_STATS = "output-subscription-user-tweet-stats";
    @Output(RandomQuoteMachineChannel.OUTPUT_SUBSCRIPTION_USER_TWEET_STATS)
    MessageChannel outputSubscriptionUserTweetStats();


    String INPUT_USER_TWEET_DATA = "input-user-tweet-data";
    @Input(RandomQuoteMachineChannel.INPUT_USER_TWEET_DATA)
    SubscribableChannel inputUserTweetData();


    String INPUT_USER_TWEET_STATS_DATA = "input-user-tweet-stats-data";
    @Input(RandomQuoteMachineChannel.INPUT_USER_TWEET_STATS_DATA)
    SubscribableChannel inputUserTweetStatsData();


}

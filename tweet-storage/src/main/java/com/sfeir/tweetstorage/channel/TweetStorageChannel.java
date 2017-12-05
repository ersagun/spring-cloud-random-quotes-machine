package com.sfeir.tweetstorage.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TweetStorageChannel {

    String INPUT_USER_TWEET_DATA = "input-user-tweet-data";
    @Input(TweetStorageChannel.INPUT_USER_TWEET_DATA)
    SubscribableChannel inputUserTweetData();


    String OUTPUT_USER_TWEET_STATS_DATA = "output-user-tweet-stats-data";
    @Output(TweetStorageChannel.OUTPUT_USER_TWEET_STATS_DATA)
    MessageChannel outputUserTweetStatsData();

}

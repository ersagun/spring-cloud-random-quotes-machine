package com.sfeir.tweetreceive.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TweetTranslateChannel {
    String INPUT = "input-tweet";
    String OUTPUT = "output-tweet";

    @Input(TweetTranslateChannel.INPUT)
    SubscribableChannel input();

    @Output(TweetTranslateChannel.OUTPUT)
    MessageChannel output();
}

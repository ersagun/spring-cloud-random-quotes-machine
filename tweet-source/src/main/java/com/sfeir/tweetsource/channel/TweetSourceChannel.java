package com.sfeir.tweetsource.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TweetSourceChannel {
    String OUTPUT = "output-tweet";

    @Output(TweetSourceChannel.OUTPUT)
    MessageChannel output();


    String INPUT = "input-user";
    @Input(TweetSourceChannel.INPUT)
    SubscribableChannel input();




}

package com.sfeir.tweetstorage.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TweetStorageChannel {
    String INPUT = "input-tweet";

    @Input(TweetStorageChannel.INPUT)
    SubscribableChannel input();


    String OUTPUT = "output-tweet";

    @Output(TweetStorageChannel.OUTPUT)
    MessageChannel output();
}

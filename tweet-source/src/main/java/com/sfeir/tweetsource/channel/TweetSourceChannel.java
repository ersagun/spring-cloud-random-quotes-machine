package com.sfeir.tweetsource.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TweetSourceChannel {
    String OUTPUT = "output-tweet";

    @Output(TweetSourceChannel.OUTPUT)
    MessageChannel output();
}

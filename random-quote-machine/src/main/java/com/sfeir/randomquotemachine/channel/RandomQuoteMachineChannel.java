package com.sfeir.randomquotemachine.channel;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface RandomQuoteMachineChannel {
    String INPUT = "input-tweet";
    @Input(RandomQuoteMachineChannel.INPUT)
    SubscribableChannel input();

    String OUTPUT = "output-user";
    @Output(RandomQuoteMachineChannel.OUTPUT)
    MessageChannel output();

    String INPUTSTATS = "tweet-statistics";
    @Input(RandomQuoteMachineChannel.INPUTSTATS)
    SubscribableChannel inputStats();

    String INPUTWIKI = "input-wiki";
    @Input(RandomQuoteMachineChannel.INPUTWIKI)
    SubscribableChannel inputWiki();




}

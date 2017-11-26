package com.sfeir.randomquotemachine.channel;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RandomQuoteMachineChannel {
    String INPUT = "input-tweet";

    @Input(RandomQuoteMachineChannel.INPUT)
    SubscribableChannel input();

    String INPUTWIKI = "input-wiki";

    @Input(RandomQuoteMachineChannel.INPUTWIKI)
    SubscribableChannel inputWiki();
}

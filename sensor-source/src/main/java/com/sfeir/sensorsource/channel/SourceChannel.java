package com.sfeir.sensorsource.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SourceChannel {
    String OUTPUT= "output";
    @Output(SourceChannel.OUTPUT)
    MessageChannel output();
}

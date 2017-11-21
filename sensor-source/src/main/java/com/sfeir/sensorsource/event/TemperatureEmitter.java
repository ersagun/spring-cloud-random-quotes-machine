package com.sfeir.sensorsource.event;

import com.sfeir.sensorsource.channel.SourceChannel;
import com.sfeir.sensorsource.model.SensorData;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;

import java.util.Random;

@EnableBinding(SourceChannel.class)
public class TemperatureEmitter {

    @InboundChannelAdapter(channel=SourceChannel.OUTPUT)
    public SensorData emitTemperatureData(){
        return new SensorData(new Random().nextInt(10),new Random().nextInt(100));
    }
}

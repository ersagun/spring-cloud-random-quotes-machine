package com.sfeir.tweetsource.event;

import com.sfeir.tweetsource.channel.TweetSourceChannel;
import com.sfeir.tweetsource.model.Tweet;
import com.sfeir.tweetsource.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;

import java.util.List;
import java.util.Random;

import static java.lang.Math.toIntExact;

@EnableBinding(TweetSourceChannel.class)
public class TweetEmitter {

    @Autowired
    TweetRepository repository;

    @InboundChannelAdapter(channel = TweetSourceChannel.OUTPUT, poller = @Poller(fixedDelay = "8000", maxMessagesPerPoll = "1"))
    public Tweet emitTweetData() {
        Pageable pageable = new PageRequest(new Random().nextInt(toIntExact(repository.count())), 1);
        Page<Tweet> pt = repository.findAll(pageable);
        List<Tweet> list = pt.getContent();
        System.out.println(list.get(new Random().nextInt(list.size())));
        return list.get(0);
    }
}

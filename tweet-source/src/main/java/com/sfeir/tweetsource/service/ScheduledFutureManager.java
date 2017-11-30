package com.sfeir.tweetsource.service;

import com.sfeir.tweetsource.channel.TweetSourceChannel;
import com.sfeir.tweetsource.thread.TwitterCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import shared.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ScheduledFutureManager {
    private ConcurrentHashMap<User, ScheduledFuture> scheduledFutureConcurrentHashMap;
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private TweetSourceChannel tweetSourceChannel;
    private static final Logger LOGGER = Logger.getLogger( ScheduledFutureManager.class.getName() );

    @Autowired
    public ScheduledFutureManager(TweetSourceChannel tweetSourceChannel,ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.tweetSourceChannel = tweetSourceChannel;
        this.scheduledFutureConcurrentHashMap = new ConcurrentHashMap<User, ScheduledFuture>();
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    public ConcurrentHashMap<User, ScheduledFuture> getScheduledFutureConcurrentHashMap() {
        return scheduledFutureConcurrentHashMap;
    }

    public void setScheduledFutureConcurrentHashMap(ConcurrentHashMap<User, ScheduledFuture> scheduledFutureConcurrentHashMap) {
        this.scheduledFutureConcurrentHashMap = scheduledFutureConcurrentHashMap;
    }

    public void createUserThreadAndAddToList(User user) {
        if(! this.scheduledFutureConcurrentHashMap.containsKey(user)) {

            ScheduledFuture scheduledFuture = threadPoolTaskScheduler.scheduleWithFixedDelay(new TwitterCaller(tweetSourceChannel,user), 1000);
            this.scheduledFutureConcurrentHashMap.put(user, scheduledFuture);
            LOGGER.log(Level.INFO,"User connection for "+user.getName()+" is created");
            //   System.out.println(this.sentimentAnalyzer("I feel sorry for Rosie 's new partner in love whose parents are devastated at the thought of their daughter being with @Rosie--a true loser."));
        }else LOGGER.log(Level.WARNING,"Connection is already exist for user "+user.getName());
    }

    public void stopUserThread(User user) {
        if (this.scheduledFutureConcurrentHashMap.containsKey(user)) {
            boolean stopped = this.scheduledFutureConcurrentHashMap.get(user).cancel(true);
            this.scheduledFutureConcurrentHashMap.remove(user);
            LOGGER.log(Level.INFO,"User connection for "+user.getName()+" is stopped");
        }

    }
}

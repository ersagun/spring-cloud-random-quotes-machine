package com.sfeir.randomquotemachine.service;

import com.sfeir.randomquotemachine.controller.SseController;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.util.NestedServletException;
import shared.Tweet;
import shared.User;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SseManager {
    private volatile Map<User, SseEmitter> sseTweet;

    private static final Logger LOGGER = Logger.getLogger(SseManager.class.getName());

    public SseManager() {
        this.sseTweet = Collections.synchronizedMap(new ConcurrentHashMap<User, SseEmitter>());
    }


    public Map<User, SseEmitter> getSseTweet() {
        return sseTweet;
    }

    public void setSseTweet(Map<User, SseEmitter> sseTweet) {
        this.sseTweet = sseTweet;
    }

    public synchronized SseEmitter addAndReturnSseEmitter(User user) {
        SseEmitter sseEmitter = new SseEmitter(600 * 1000L);
        sseTweet.put(user, sseEmitter);
        return sseEmitter;
    }

    public synchronized SseEmitter getSseEmitterFromUser(User user) {
        return sseTweet.get(user);
    }



    public synchronized SseEmitter removeUserFromSseTweet(User user) {
        if(this.sseContainsUser(user)) {
            LOGGER.log(Level.INFO,"User is removed from sset tweet list");
            this.sseTweet.get(user).complete();
        }
        return this.sseTweet.remove(user);
    }

    public synchronized boolean sseContainsUser(User user) {
        return this.sseTweet.containsKey(user);
    }


    public synchronized void sendTweetToSseUser(Tweet tweet){
        if(this.sseTweet.containsKey(tweet.getUser())) {
            try {
                this.sseTweet.get(tweet.getUser()).send(tweet);
            } catch (Exception e) {
                LOGGER.log(Level.INFO, "User "+tweet.getUser()+ "'s connection is broken.");
            }
        }
    }
}

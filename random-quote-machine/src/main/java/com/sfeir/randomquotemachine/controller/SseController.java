package com.sfeir.randomquotemachine.controller;


import com.sfeir.randomquotemachine.channel.RandomQuoteMachineChannel;
import com.sfeir.randomquotemachine.service.SseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import shared.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class SseController {

    public static Map<User, SseEmitter> sseTranslation = new ConcurrentHashMap<>();
    public static Map<User, SseEmitter> sseWiki = new ConcurrentHashMap<>();


    private static final Logger LOGGER = Logger.getLogger(SseController.class.getName());
    private static final AtomicInteger counter = new AtomicInteger();
    private RandomQuoteMachineChannel randomQuoteMachineChannel;
    private SseManager sseManager;


    @Autowired
    public SseController(RandomQuoteMachineChannel randomQuoteMachineChannel, SseManager sseManager) {
        this.randomQuoteMachineChannel = randomQuoteMachineChannel;
        this.sseManager = sseManager;
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/api/subscribe/tweet/{key}/{name}/{searchQuery}")
    @ResponseBody
    User tweetStart(@PathVariable int key, @PathVariable String name, @PathVariable String searchQuery) {
        System.out.println("Tweet subscribtion : /api/subscribe/tweet/{key}/{name}/{searchQuery}");
        User user = new User(key, name, searchQuery);
        //System.out.println(this.sseManager.getSseTweet());
        if (this.sseManager.sseContainsUser(user)) {
        randomQuoteMachineChannel.output().send(MessageBuilder.withPayload(user).setHeader("type", "create-user").build());
        LOGGER.log(Level.INFO, "User " + name + " subscribed to tweet");
        }else {
        LOGGER.log(Level.INFO, "User " + name + " not found in the sse list");
        }

    /*  SseEmitter sseEmitter = this.sseManager.addAndReturnSseEmitter(user);
        sseEmitter.onCompletion(() -> {
            this.randomQuoteMachineChannel.output().send(MessageBuilder.withPayload(user).setHeader("type", "stop-user").build());
            this.sseManager.removeUserFromSseTweet(user.getId());
        });
        sseEmitter.onTimeout(() -> {
            this.randomQuoteMachineChannel.output().send(MessageBuilder.withPayload(user).setHeader("type", "stop-user").build());
            this.sseManager.removeUserFromSseTweet(user.getId());
        });*/

        return user;
    }


    @CrossOrigin("http://localhost:4200")
    @GetMapping("/api/stream/{key}/{name}/{searchQuery}")
    @ResponseBody
    SseEmitter streamConnection(@PathVariable String name, @PathVariable String searchQuery, @PathVariable int key) {
        SseEmitter sseEmitter = new SseEmitter(1L);
        try {
            System.out.println("Stream connection is created : /api/stream/{key}/{name}/{searchQuery}");
            User user = new User(key, name, searchQuery);
            if (!this.sseManager.sseContainsUser(user)) {

                sseEmitter = this.sseManager.addAndReturnSseEmitter(user);
                LOGGER.log(Level.INFO, "User " + name + " subscribed");

                sseEmitter.onCompletion(() -> {
                    boolean closed =this.randomQuoteMachineChannel.output().send(MessageBuilder.withPayload(user).setHeader("type", "stop-user").build());
                    if(closed)
                    this.sseManager.removeUserFromSseTweet(user);
                });
                sseEmitter.onTimeout(() -> {
                    boolean closed =this.randomQuoteMachineChannel.output().send(MessageBuilder.withPayload(user).setHeader("type", "stop-user").build());
                    if(closed)
                    this.sseManager.removeUserFromSseTweet(user);
                });

            } else {
                sseEmitter = this.sseManager.getSseEmitterFromUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sseEmitter;
    }


    /*    @CrossOrigin("http://localhost:4200")
    @GetMapping("/subscribe/translation/{name}/{searchQuery}")
    @ResponseBody
    SseEmitter translation(@PathVariable String name,@PathVariable String searchQuery) {
        SseEmitter sseEmitter = new SseEmitter(60 * 100L);
        sseTranslation.put(new User(counter.getAndIncrement(),name,searchQuery), sseEmitter);
        LOGGER.log(Level.INFO,"User "+name+" subscribed to translation queue");
        return sseEmitter;
    }*/


/*    @CrossOrigin("http://localhost:4200")
    @GetMapping("/subscribe/translation/{name}/")
    @ResponseBody
    SseEmitter translation(@PathVariable String name) {
        SseEmitter sseEmitter = new SseEmitter(60 * 100L);
        sseTranslation.put(name, sseEmitter);
        LOGGER.log(Level.INFO,"User "+name+" subscribed to translation queue");
        return sseEmitter;
    }
    */


/*    @CrossOrigin("http://localhost:4200")
    @GetMapping("/subscribe/wiki/{name}/{searchQuery}")
    @ResponseBody
    SseEmitter wiki(@PathVariable String name,@PathVariable String searchQuery) {
        SseEmitter sseEmitter = new SseEmitter(60 * 100L);
        sseWiki.put(new User(counter.getAndIncrement(),name,searchQuery), sseEmitter);
        LOGGER.log(Level.INFO,"User "+name+" subscribed to wiki queue");
        return sseEmitter;
    }*/


}

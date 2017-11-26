package com.sfeir.randomquotemachine.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class SseController {

    public static Map<String, SseEmitter> sseTranslation = new ConcurrentHashMap<>();
    public static Map<String, SseEmitter> sseWiki = new ConcurrentHashMap<>();
    public static Map<String, SseEmitter> sseTweet = new ConcurrentHashMap<>();

    private static final Logger LOGGER = Logger.getLogger( SseController.class.getName() );

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/subscribe/translation/{name}")
    @ResponseBody
    SseEmitter translation(@PathVariable String name) {
        SseEmitter sseEmitter = new SseEmitter(60 * 100L);
        sseTranslation.put(name, sseEmitter);
        LOGGER.log(Level.INFO,"User "+name+" subscribed to translation queue");
        return sseEmitter;
    }


    @CrossOrigin("http://localhost:4200")
    @GetMapping("/subscribe/wiki/{name}")
    @ResponseBody
    SseEmitter wiki(@PathVariable String name) {
        SseEmitter sseEmitter = new SseEmitter(60 * 100L);
        sseWiki.put(name, sseEmitter);
        LOGGER.log(Level.INFO,"User "+name+" subscribed to wiki queue");
        return sseEmitter;
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/subscribe/tweet/{name}")
    @ResponseBody
    SseEmitter tweet(@PathVariable String name) {
        SseEmitter sseEmitter = new SseEmitter(60 * 100L);
        LOGGER.log(Level.INFO,"User "+name+" subscribed to tweet queue");
        sseTweet.put(name, sseEmitter);
        return sseEmitter;
    }

}

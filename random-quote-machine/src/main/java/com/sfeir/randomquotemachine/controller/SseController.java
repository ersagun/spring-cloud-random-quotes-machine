package com.sfeir.randomquotemachine.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SseController {

    public static Map<String, SseEmitter> sseTranslation = new ConcurrentHashMap<>();
    public static Map<String, SseEmitter> sseWiki = new ConcurrentHashMap<>();

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/subscribe/translation/{name}")
    @ResponseBody
    SseEmitter translation(@PathVariable String name) {
        SseEmitter sseEmitter = new SseEmitter(60 * 100L);
        sseTranslation.put(name, sseEmitter);
        return sseEmitter;
    }


    @CrossOrigin("http://localhost:4200")
    @GetMapping("/subscribe/wiki/{name}")
    @ResponseBody
    SseEmitter wiki(@PathVariable String name) {
        SseEmitter sseEmitter = new SseEmitter(60 * 100L);
        sseWiki.put(name, sseEmitter);
        return sseEmitter;
    }


}

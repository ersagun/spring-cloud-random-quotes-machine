package com.sfeir.tweetreceive.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import shared.wiki.Wiki;

import java.io.IOException;

@Service
public class WikipediaService {

    private RestTemplate restTemplate = new RestTemplate();

    public Wiki getFromWikipedia(String name) {
        String wikis = restTemplate.getForObject("https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=" + name, String.class);

        ObjectMapper om = new ObjectMapper();
        Wiki wiki = new Wiki();
        try {
            wiki = om.readValue(wikis, Wiki.class);
            System.out.println(wiki);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wiki;
    }

}

package com.sfeir.tweetreceive.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfeir.tweetreceive.model.Translation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class YandexService {


    RestTemplate restTemplate = new RestTemplate();

    public Translation translateByType(String text, String languageDirection) {

        String translate = "";
        try {
            translate = restTemplate.getForObject("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20171124T060825Z.a5b163da3e17fd87.88de15aaff951e7bc747b6562dc43c44362dcfaa&text=" + text + "&lang=" + languageDirection + "&format=plain&options=1", String.class);

        } catch (Exception e) {
            System.out.println(e);
        }

        ObjectMapper om = new ObjectMapper();

        Translation tl = new Translation();

        try {
            tl = om.readValue(translate, Translation.class);
            System.out.println(tl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tl;
    }
}

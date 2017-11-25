package com.sfeir.tweetsource.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Tweet implements Serializable {

    @Id
    private String id;

    private String words;
    private String pict;

    public Tweet() {
    }

    public Tweet(String words, String pict) {
        this.words = words;
        this.pict = pict;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "words='" + words + '\'' +
                ", pict='" + pict + '\'' +
                '}';
    }
}

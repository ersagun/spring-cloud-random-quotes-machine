package com.sfeir.tweetstorage.model;

public class Tweet {

    private String words;
    private String pict;

    public Tweet() {
    }

    public Tweet(String words, String pict) {
        this.words = words;
        this.pict = pict;
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

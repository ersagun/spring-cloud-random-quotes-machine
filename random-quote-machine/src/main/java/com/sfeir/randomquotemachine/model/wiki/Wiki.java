package com.sfeir.randomquotemachine.model.wiki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wiki {

    @JsonProperty("batchcomplete")
    private String batchComplete;
    private Query query;

    public Wiki() {
    }

    public Wiki(String batchComplete, Query query) {
        this.batchComplete = batchComplete;
        this.query = query;
    }

    public String getBatchComplete() {
        return batchComplete;
    }

    public void setBatchComplete(String batchComplete) {
        this.batchComplete = batchComplete;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "Wiki{" +
                "batchComplete='" + batchComplete + '\'' +
                ", query=" + query +
                '}';
    }
}

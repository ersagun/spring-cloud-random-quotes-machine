package com.sfeir.randomquotemachine.model.wiki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {

    private Page pages;

    public Query() {
    }

    public Query(Page pages) {
        this.pages = pages;
    }

    public Page getPages() {
        return pages;
    }

    public void setPages(Page pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Query{" +
                "pages=" + pages +
                '}';
    }
}

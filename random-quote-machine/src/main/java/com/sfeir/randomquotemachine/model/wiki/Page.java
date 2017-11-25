package com.sfeir.randomquotemachine.model.wiki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
    private String extract;
    private int ns;
    private int pageid;
    private String title;

    public Page() {
    }

    public Page(String extract, int ns, int pageid, String title) {
        this.extract = extract;
        this.ns = ns;
        this.pageid = pageid;
        this.title = title;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    public int getNs() {
        return ns;
    }

    public void setNs(int ns) {
        this.ns = ns;
    }

    public int getPageid() {
        return pageid;
    }

    public void setPageid(int pageid) {
        this.pageid = pageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Page{" +
                "extract='" + extract + '\'' +
                ", ns=" + ns +
                ", pageid=" + pageid +
                ", title='" + title + '\'' +
                '}';
    }
}

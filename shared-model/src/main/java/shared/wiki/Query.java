package shared.wiki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {

    private Map<String, Page> pages;

    public Query() {
    }

    public Query(Map<String, Page> pages) {
        this.pages = pages;
    }

    public Map<String, Page> getPages() {
        return pages;
    }

    public void setPages(Map<String, Page> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Query{" +
                "pages=" + pages +
                '}';
    }
}

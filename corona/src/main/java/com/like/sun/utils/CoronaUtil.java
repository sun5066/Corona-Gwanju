package com.like.sun.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CoronaUtil {
    private String url;

    public CoronaUtil(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Document getDocument() throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }
}

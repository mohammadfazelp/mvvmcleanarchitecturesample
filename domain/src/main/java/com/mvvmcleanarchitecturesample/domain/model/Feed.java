package com.mvvmcleanarchitecturesample.domain.model;

import java.io.Serializable;
import java.util.List;

/**
 * Class that represents a Feed in the domain layer.
 */
public class Feed implements Serializable {

    private transient long id;
    private String status;
    private long totalResults;
    private List<Article> articles;

    public Feed(long id) {
        this.id = id;
    }

    long getRandomNumber() {
        return (long) ((Math.random() * ((100000) + 1)) + 0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticleEntities() {
        return articles;
    }

    public void setArticleEntities(List<Article> articleEntities) {
        this.articles = articleEntities;
    }
}


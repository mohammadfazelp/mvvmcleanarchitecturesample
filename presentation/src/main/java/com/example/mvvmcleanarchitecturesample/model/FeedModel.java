package com.example.mvvmcleanarchitecturesample.model;

import java.io.Serializable;
import java.util.List;

/**
 * Class that represents a FeedModel in the domain layer.
 */
public class FeedModel implements Serializable {

    private transient long id;
    private String status;
    private long totalResults;
    private List<ArticleModel> articles;

    public FeedModel(long id) {
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

    public List<ArticleModel> getArticleEntities() {
        return articles;
    }

    public void setArticleEntities(List<ArticleModel> articleEntities) {
        this.articles = articleEntities;
    }
}


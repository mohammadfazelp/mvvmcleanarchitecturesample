package com.example.mvvmcleanarchitecturesample.model;

import java.io.Serializable;

public class ArticleModel implements Serializable {

    private static int increment = 0;

    private long id;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private SourceModel source;

    public ArticleModel() {
        id = ++increment;
    }

    long getRandomNumber() {
        return (long) ((Math.random() * ((100000) + 1)) + 0);
    }

    public long getId() {
        return id;
    }

    protected void setId() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public SourceModel getSource() {
        return source;
    }

    public void setSource(SourceModel source) {
        this.source = source;
    }


//    public static DiffUtil.ItemCallback<ArticleEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<ArticleEntity>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull ArticleEntity oldItem, @NonNull ArticleEntity newItem) {
//            return oldItem.id == newItem.id;
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull ArticleEntity oldItem, @NonNull ArticleEntity newItem) {
//            return oldItem.equals(newItem);
//        }
//    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        ArticleModel article = (ArticleModel) obj;
        return article.id == this.id;
    }
}


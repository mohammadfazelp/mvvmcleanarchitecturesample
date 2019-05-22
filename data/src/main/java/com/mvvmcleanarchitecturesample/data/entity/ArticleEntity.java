package com.mvvmcleanarchitecturesample.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class ArticleEntity implements Parcelable {

    private static int increment = 0;

    private long id;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private SourceEntity sourceEntity;

    public ArticleEntity() {
        id = ++increment;
    }

    long getRandomNumber() {
        return (long) ((Math.random() * ((100000) + 1)) + 0);
    }

    protected ArticleEntity(Parcel in) {
        id = getRandomNumber();
        author = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
        sourceEntity = in.readParcelable(SourceEntity.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(url);
        dest.writeString(urlToImage);
        dest.writeString(publishedAt);
        dest.writeParcelable(sourceEntity, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ArticleEntity> CREATOR = new Creator<ArticleEntity>() {
        @Override
        public ArticleEntity createFromParcel(Parcel in) {
            return new ArticleEntity(in);
        }

        @Override
        public ArticleEntity[] newArray(int size) {
            return new ArticleEntity[size];
        }
    };

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

    public SourceEntity getSourceEntity() {
        return sourceEntity;
    }

    public void setSourceEntity(SourceEntity sourceEntity) {
        this.sourceEntity = sourceEntity;
    }


    public static DiffUtil.ItemCallback<ArticleEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<ArticleEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull ArticleEntity oldItem, @NonNull ArticleEntity newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticleEntity oldItem, @NonNull ArticleEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        ArticleEntity articleEntity = (ArticleEntity) obj;
        return articleEntity.id == this.id;
    }
}


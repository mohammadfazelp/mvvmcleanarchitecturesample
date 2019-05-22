package com.mvvmcleanarchitecturesample.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedEntity implements Parcelable {

    @SerializedName("id")
    private transient long id;
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private long totalResults;
    @SerializedName("articleEntities")
    private List<ArticleEntity> articleEntities;

    long getRandomNumber() {
        return (long) ((Math.random() * ((100000) + 1)) + 0);
    }
    protected FeedEntity(Parcel in) {
        id = getRandomNumber();
        status = in.readString();
        totalResults = in.readLong();
        articleEntities = in.createTypedArrayList(ArticleEntity.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(status);
        dest.writeLong(totalResults);
        dest.writeTypedList(articleEntities);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<FeedEntity> CREATOR = new Creator<FeedEntity>() {
        @Override
        public FeedEntity createFromParcel(Parcel in) {
            return new FeedEntity(in);
        }

        @Override
        public FeedEntity[] newArray(int size) {
            return new FeedEntity[size];
        }
    };


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

    public List<ArticleEntity> getArticleEntities() {
        return articleEntities;
    }

    public void setArticleEntities(List<ArticleEntity> articleEntities) {
        this.articleEntities = articleEntities;
    }
}


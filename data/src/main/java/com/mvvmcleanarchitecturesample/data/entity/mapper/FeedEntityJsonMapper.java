package com.mvvmcleanarchitecturesample.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mvvmcleanarchitecturesample.data.entity.FeedEntity;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class FeedEntityJsonMapper {

    private final Gson gson;

    @Inject
    public FeedEntityJsonMapper() {
        this.gson = new Gson();
    }

    /**
     * Transform from valid json string to {@link com.mvvmcleanarchitecturesample.data.entity.FeedEntity}.
     *
     * @param feedJsonResponse A json representing a feed profile.
     * @return {@link com.mvvmcleanarchitecturesample.data.entity.FeedEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public FeedEntity transformFeedEntity(String feedJsonResponse) throws JsonSyntaxException {
        final Type feedEntityType = new TypeToken<FeedEntity>() {
        }.getType();
        return this.gson.fromJson(feedJsonResponse, feedEntityType);
    }

    /**
     * Transform from valid json string to List of {@link FeedEntity}.
     *
     * @param feedListJsonResponse A json representing a collection of feeds.
     * @return List of {@link FeedEntity}.
     * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
     */
    public List<FeedEntity> transformFeedEntityCollection(String feedListJsonResponse)
            throws JsonSyntaxException {
        final Type listOfFeedEntityType = new TypeToken<List<FeedEntity>>() {
        }.getType();
        return this.gson.fromJson(feedListJsonResponse, listOfFeedEntityType);
    }
}


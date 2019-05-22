package com.mvvmcleanarchitecturesample.data.repository.datasource;

import com.mvvmcleanarchitecturesample.data.entity.FeedEntity;

import java.util.List;

import io.reactivex.Observable;


/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface FeedDataStore {
    /**
     * Get an {@link Observable} which will emit a List of {@link FeedEntity}.
     */
    Observable<List<FeedEntity>> feedEntityList();

    /**
     * Get an {@link Observable} which will emit a {@link FeedEntity} by its id.
     *
     * @param feedId The id to retrieve feed data.
     */
    Observable<FeedEntity> feedEntityDetails(final int feedId);
}

package com.mvvmcleanarchitecturesample.data.repository.datasource;

import com.mvvmcleanarchitecturesample.data.cache.FeedCache;
import com.mvvmcleanarchitecturesample.data.entity.FeedEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link FeedDataStore} implementation based on file system data store.
 */
class DiskFeedDataStore implements FeedDataStore {

  private final FeedCache feedCache;


  /**
   * Construct a {@link FeedDataStore} based file system data store.
   *
   * @param feedCache A {@link FeedCache} to cache data retrieved from the api.
   */
  DiskFeedDataStore(FeedCache feedCache) {
    this.feedCache = feedCache;
  }

    @Override
    public Observable<List<FeedEntity>> feedEntityList() {
        //TODO: implement simple cache for storing/retrieving collections of feeds.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<FeedEntity> feedEntityDetails(int feedId) {
        return this.feedCache.get(feedId);
    }
}

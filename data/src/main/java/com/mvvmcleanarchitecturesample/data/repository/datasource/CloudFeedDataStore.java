package com.mvvmcleanarchitecturesample.data.repository.datasource;

import com.mvvmcleanarchitecturesample.data.cache.FeedCache;
import com.mvvmcleanarchitecturesample.data.entity.FeedEntity;
import com.mvvmcleanarchitecturesample.data.net.IRestApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link FeedDataStore} implementation based on connections to the api (Cloud).
 */
class CloudFeedDataStore implements FeedDataStore {

    private final IRestApi restApi;
    private final FeedCache feedCache;

    /**
     * Construct a {@link FeedDataStore} based on connections to the api (Cloud).
     *
     * @param restApi   The {@link IRestApi} implementation to use.
     * @param feedCache A {@link FeedCache} to cache data retrieved from the api.
     */
    CloudFeedDataStore(IRestApi restApi, FeedCache feedCache) {
        this.restApi = restApi;
        this.feedCache = feedCache;
    }

    @Override
    public Observable<List<FeedEntity>> feedEntityList() {
        return this.restApi.feedEntityList();
    }

    @Override
    public Observable<FeedEntity> feedEntityDetails(final int feedId) {
//    return this.restApi.feedEntityById(feedId).doOnNext(CloudFeedDataStore.this.feedCache::put);
        return null;
    }

}

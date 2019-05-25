package com.mvvmcleanarchitecturesample.data.repository.datasource;

import android.content.Context;

import com.mvvmcleanarchitecturesample.data.cache.FeedCache;
import com.mvvmcleanarchitecturesample.data.entity.mapper.FeedEntityJsonMapper;
import com.mvvmcleanarchitecturesample.data.api.IRestApi;
import com.mvvmcleanarchitecturesample.data.api.RestApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;

/**
 * Factory that creates different implementations of {@link FeedDataStore}.
 */
@Singleton
public class FeedDataStoreFactory {

    private final Context context;
    private final FeedCache feedCache;

    @Inject
    FeedDataStoreFactory(@NonNull Context context, @NonNull FeedCache feedCache) {
        this.context = context.getApplicationContext();
        this.feedCache = feedCache;
    }

    /**
     * Create {@link FeedDataStore} from a user id.
     */
    public FeedDataStore create(int userId) {
        FeedDataStore feedDataStore;

        if (!this.feedCache.isExpired() && this.feedCache.isCached(userId)) {
            feedDataStore = new DiskFeedDataStore(this.feedCache);
        } else {
            feedDataStore = createCloudDataStore();
        }

        return feedDataStore;
    }

    /**
     * Create {@link FeedDataStore} to retrieve data from the Cloud.
     */
    public FeedDataStore createCloudDataStore() {
        final FeedEntityJsonMapper userEntityJsonMapper = new FeedEntityJsonMapper();
        final IRestApi restApi = new RestApi(this.context, userEntityJsonMapper);
        return new CloudFeedDataStore(restApi, this.feedCache);
    }
}

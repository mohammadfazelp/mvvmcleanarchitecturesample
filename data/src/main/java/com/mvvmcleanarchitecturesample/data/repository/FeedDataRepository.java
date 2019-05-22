package com.mvvmcleanarchitecturesample.data.repository;

import com.mvvmcleanarchitecturesample.data.entity.mapper.FeedEntityDataMapper;
import com.mvvmcleanarchitecturesample.data.repository.datasource.FeedDataStore;
import com.mvvmcleanarchitecturesample.data.repository.datasource.FeedDataStoreFactory;
import com.mvvmcleanarchitecturesample.domain.model.Feed;
import com.mvvmcleanarchitecturesample.domain.repository.IFeedRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link IFeedRepository} for retrieving feed data.
 */
@Singleton
public class FeedDataRepository implements IFeedRepository {

    private final FeedDataStoreFactory feedDataStoreFactory;
    private final FeedEntityDataMapper feedEntityDataMapper;

    /**
     * Constructs a {@link IFeedRepository}.
     *
     * @param dataStoreFactory     A factory to construct different data source implementations.
     * @param feedEntityDataMapper {@link FeedEntityDataMapper}.
     */
    @Inject
    FeedDataRepository(FeedDataStoreFactory dataStoreFactory,
                       FeedEntityDataMapper feedEntityDataMapper) {
        this.feedDataStoreFactory = dataStoreFactory;
        this.feedEntityDataMapper = feedEntityDataMapper;
    }

    @Override
    public Observable<List<Feed>> feeds() {
        //we always get all feeds from the cloud
        final FeedDataStore feedDataStore = this.feedDataStoreFactory.createCloudDataStore();
        return feedDataStore.feedEntityList().map(this.feedEntityDataMapper::transform);
    }

    @Override
    public Observable<Feed> feed(int feedId) {
        final FeedDataStore feedDataStore = this.feedDataStoreFactory.create(feedId);
        return feedDataStore.feedEntityDetails(feedId).map(this.feedEntityDataMapper::transform);
    }
}

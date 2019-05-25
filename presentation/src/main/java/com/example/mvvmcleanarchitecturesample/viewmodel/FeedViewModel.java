package com.example.mvvmcleanarchitecturesample.viewmodel;

import com.example.mvvmcleanarchitecturesample.mapper.FeedModelDataMapper;
import com.example.mvvmcleanarchitecturesample.model.FeedModel;
import com.example.mvvmcleanarchitecturesample.ui.base.BaseViewModel;
import com.mvvmcleanarchitecturesample.data.utils.NetworkState;
import com.mvvmcleanarchitecturesample.domain.interactor.DefaultObserver;
import com.mvvmcleanarchitecturesample.domain.interactor.GetFeedDetails;
import com.mvvmcleanarchitecturesample.domain.model.Feed;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FeedViewModel extends BaseViewModel {

    private Executor executor;
    private LiveData networkState;
    private MutableLiveData<FeedModel> feedModelMutableLiveData;


    private final GetFeedDetails getFeedDetails;
    private final FeedModelDataMapper feedModelDataMapper;

    @Inject
    public FeedViewModel(GetFeedDetails getFeedDetails,
                         FeedModelDataMapper feedModelDataMapper) {

        this.getFeedDetails = getFeedDetails;
        this.feedModelDataMapper = feedModelDataMapper;
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public MutableLiveData<FeedModel> getFeedLiveData() {
        return feedModelMutableLiveData;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        getFeedDetails.dispose();
    }


    public void initialize() {
        this.loadFeedList();
    }


    private void loadFeedList() {
        this.getFeedList();
    }

    private void getFeedList() {
        this.getFeedDetails.execute(new FeedListObserver(), null);
    }

    private final class FeedListObserver extends DefaultObserver<Feed> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Feed feed) {
            feedModelMutableLiveData.postValue(mapFeed(feed));
        }
    }

    private FeedModel mapFeed(Feed feed) {
        FeedModel feedModel = feedModelDataMapper.transform(feed);
        return feedModel;
    }
}


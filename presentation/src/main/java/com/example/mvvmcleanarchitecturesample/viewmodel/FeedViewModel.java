package com.example.mvvmcleanarchitecturesample.viewmodel;

import com.example.mvvmcleanarchitecturesample.App;
import com.example.mvvmcleanarchitecturesample.model.FeedModel;
import com.example.mvvmcleanarchitecturesample.ui.base.BaseViewModel;
import com.example.mvvmcleanarchitecturesample.utils.mapper.FeedModelDataMapper;
import com.mvvmcleanarchitecturesample.data.utils.NetworkState;
import com.mvvmcleanarchitecturesample.domain.exception.DefaultErrorBundle;
import com.mvvmcleanarchitecturesample.domain.exception.ErrorBundle;
import com.mvvmcleanarchitecturesample.domain.interactor.DefaultObserver;
import com.mvvmcleanarchitecturesample.domain.interactor.GetFeedList;
import com.mvvmcleanarchitecturesample.domain.model.Article;
import com.mvvmcleanarchitecturesample.domain.model.Feed;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class FeedViewModel extends BaseViewModel {

    private Executor executor;
//    private LiveData networkState;
//    private LiveData articleLiveData;

    private App app;

//    public FeedViewModel(@NonNull App app) {
//        this.app = app;
//        init();
//    }

    private final GetFeedList getFeedList;
    private final FeedModelDataMapper feedModelDataMapper;

    @Inject
    public FeedViewModel(GetFeedList getFeedList,
                         FeedModelDataMapper feedModelDataMapper) {
        this.getFeedList = getFeedList;
        this.feedModelDataMapper = feedModelDataMapper;
    }

    /*
     * Step 1: We are initializing an Executor class
     * Step 2: We are getting an instance of the DataSourceFactory class
     * Step 3: We are initializing the network state liveData as well.
     *         This will update the UI on the network changes that take place
     *         For instance, when the data is getting fetched, we would need
     *         to display a loader and when data fetching is completed, we
     *         should hide the loader.
     * Step 4: We need to configure the PagedList.Config.
     * Step 5: We are initializing the pageList using the config we created
     *         in Step 4 and the DatasourceFactory we created from Step 2
     *         and the executor we initialized from Step 1.
     */

//    private void init() {
//
//        executor = Executors.newFixedThreadPool(5);
//
//        FeedDataFactory feedDataFactory = new FeedDataFactory(app);
//        networkState = Transformations.switchMap(feedDataFactory.getMutableLiveData(),
//                dataSource -> dataSource.getNetworkState());
//
//        PagedList.Config pagedListConfig =
//                (new PagedList.Config.Builder())
//                        .setEnablePlaceholders(false)
//                        .setInitialLoadSizeHint(10)
//                        .setPageSize(20).build();
//
//        articleLiveData = (new LivePagedListBuilder(feedDataFactory, pagedListConfig))
//                .setFetchExecutor(executor)
//                .build();
//    }

//    public LiveData<NetworkState> getNetworkState() {
//        return networkState;
//    }

//    public LiveData<PagedList<Article>> getArticleLiveData() {
//        return articleLiveData;
//    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        getFeedList.dispose();
    }

    /**
     * Initializes the presenter by start retrieving the feed list.
     */
    public void initialize() {
        this.loadFeedList();
    }

    /**
     * Loads all feeds.
     */
    private void loadFeedList() {
        this.getFeedList();
    }

    private void getFeedList() {
        this.getFeedList.execute(new FeedListObserver(), null);
    }

    private final class FeedListObserver extends DefaultObserver<List<Feed>> {

        @Override
        public void onComplete() {
            FeedViewModel.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            FeedViewModel.this.hideViewLoading();
            FeedViewModel.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            FeedViewModel.this.showViewRetry();
        }

        @Override
        public void onNext(List<Feed> feeds) {
            FeedViewModel.this.showFeedsCollectionInView(feeds);
        }
    }


    private void showViewLoading() {
//        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
//        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
//        this.viewListView.showRetry();
    }

    private void hideViewRetry() {
//        this.viewListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
//        String errorMessage = ErrorMessageFactory.create(this.viewListView.context(),
//                errorBundle.getException());
//        this.viewListView.showError(errorMessage);
    }

    private void showFeedsCollectionInView(Collection<Feed> feedsCollection) {
        final Collection<FeedModel> feedModelsCollection =
                this.feedModelDataMapper.transform(feedsCollection);
//        this.viewListView.renderFeedList(feedModelsCollection);
    }
}


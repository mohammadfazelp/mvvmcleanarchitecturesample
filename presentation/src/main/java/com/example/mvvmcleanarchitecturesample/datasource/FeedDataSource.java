//package com.example.mvvmcleanarchitecturesample.datasource;
//
//import android.util.Log;
//
//import com.example.mvvmcleanarchitecturesample.App;
//import com.mvvmcleanarchitecturesample.data.utils.Constants;
//import com.mvvmcleanarchitecturesample.data.utils.NetworkState;
//import com.mvvmcleanarchitecturesample.domain.interactor.DefaultObserver;
//import com.mvvmcleanarchitecturesample.domain.interactor.feed.GetFeedList;
//import com.mvvmcleanarchitecturesample.domain.model.Article;
//import com.mvvmcleanarchitecturesample.domain.model.Feed;
//
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.MutableLiveData;
//import androidx.paging.PageKeyedDataSource;
//
///**
// * Created by PIRI on 1/22/2019.
// */
//public class FeedDataSource extends PageKeyedDataSource<Long, Article> implements Constants {
//
//    private static final String TAG = FeedDataSource.class.getSimpleName();
//
//    /*
//     * Step 1: Initialize the restApiFactory.
//     * The networkState and initialLoading variables
//     * are for updating the UI when data is being fetched
//     * by displaying a progress bar
//     */
//
//    // private App app;
//    private GetFeedList getFeedList;
//
//    private MutableLiveData networkState;
//    private MutableLiveData initialLoading;
//
//    public FeedDataSource(App app) {
//
////        this.app = app;
//        networkState = new MutableLiveData();
//        initialLoading = new MutableLiveData();
//    }
//
//    public FeedDataSource(/*App app,*/GetFeedList getFeedList) {
////        this.app = app;
//        this.getFeedList = getFeedList;
//        networkState = new MutableLiveData();
//        initialLoading = new MutableLiveData();
//    }
//
//
//    public MutableLiveData getNetworkState() {
//        return networkState;
//    }
//
//    public MutableLiveData getInitialLoading() {
//        return initialLoading;
//    }
//
//    /*
//     * Step 2: This method is responsible to load the data initially
//     * when app screen is launched for the first time.
//     * We are fetching the first page data from the api
//     * and passing it via the callback method to the UI.
//     */
//    @Override
//    public void loadInitial(@NonNull LoadInitialParams<Long> params,
//                            @NonNull final LoadInitialCallback<Long, Article> callback) {
//
//        initialLoading.postValue(NetworkState.LOADING);
//        networkState.postValue(NetworkState.LOADING);
//
//        this.getFeedList.execute(new FeedListObserver(), null);
////        app.getRestApi().fetchFeed(QUERY, API_KEY, 1, params.requestedLoadSize)
////                .enqueue(new Callback<Feed>() {
////                    @Override
////                    public void onResponse(Call<Feed> call, Response<Feed> response) {
////                        if (response.isSuccessful()) {
////                            callback.onResult(response.body().getArticles(), null, 2l);
////                            initialLoading.postValue(NetworkState.LOADED);
////                            networkState.postValue(NetworkState.LOADED);
////
////                        } else {
////                            initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
////                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<Feed> call, Throwable t) {
////                        String errorMessage = t == null ? "unknown error" : t.getMessage();
////                        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
////                    }
////                });
//    }
//
//    @Override
//    public void loadBefore(@NonNull LoadParams<Long> params,
//                           @NonNull LoadCallback<Long, Article> callback) {
//
//    }
//
//    /*
//     * Step 3: This method it is responsible for the subsequent call to load the data page wise.
//     * This method is executed in the background thread
//     * We are fetching the next page data from the api
//     * and passing it via the callback method to the UI.
//     * The "params.key" variable will have the updated value.
//     */
//    @Override
//    public void loadAfter(@NonNull final LoadParams<Long> params,
//                          @NonNull final LoadCallback<Long, Article> callback) {
//
//        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);
//
//        networkState.postValue(NetworkState.LOADING);
//        getFeedList.execute(new FeedListObserver(), null);
//    }
//
//    private final class FeedListObserver extends DefaultObserver<List<Feed>> {
//
//        @Override
//        public void onComplete() {
//            initialLoading.postValue(NetworkState.LOADED);
//            networkState.postValue(NetworkState.LOADED);
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, e.getMessage()));
//            initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, e.getMessage()));
//        }
//
//        @Override
//        public void onNext(List<Feed> feeds) {
//            long nextKey = (params.key == response.body().getTotalResults()) ? null : params.key + 1;
//
//            initialLoading.postValue(NetworkState.LOADED);
//            networkState.postValue(NetworkState.LOADED);
//        }
//    }
//}

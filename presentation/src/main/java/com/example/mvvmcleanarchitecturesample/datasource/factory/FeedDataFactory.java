//package com.example.mvvmcleanarchitecturesample.datasource.factory;
//
//
//import com.example.mvvmcleanarchitecturesample.App;
//import com.example.mvvmcleanarchitecturesample.datasource.FeedDataSource;
//import com.mvvmcleanarchitecturesample.domain.interactor.feed.GetFeedList;
//
//import androidx.lifecycle.MutableLiveData;
//import androidx.paging.DataSource;
//
//public class FeedDataFactory extends DataSource.Factory {
//
//    /*DataSourceFactory is responsible for retrieving the data
//    using the DataSource and PagedList configuration which we
//    create in our ViewModel class.*/
//
//    private MutableLiveData<FeedDataSource> mutableLiveData;
//    private FeedDataSource feedDataSource;
////    private App app;
//    private GetFeedList getFeedList;
//
//    public FeedDataFactory(/*App app,*/ GetFeedList getFeedList) {
////        this.app = app;
//        this.getFeedList = getFeedList;
//        this.mutableLiveData = new MutableLiveData<>();
//    }
//
//    @Override
//    public DataSource create() {
//        feedDataSource = new FeedDataSource(/*app,*/getFeedList);
//        mutableLiveData.postValue(feedDataSource);
//        return feedDataSource;
//    }
//
//
//    public MutableLiveData<FeedDataSource> getMutableLiveData() {
//        return mutableLiveData;
//    }
//}
package com.mvvmcleanarchitecturesample.data.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mvvmcleanarchitecturesample.data.entity.FeedEntity;
import com.mvvmcleanarchitecturesample.data.entity.mapper.FeedEntityJsonMapper;
import com.mvvmcleanarchitecturesample.data.exception.NetworkConnectionException;
import com.mvvmcleanarchitecturesample.data.utils.Constants;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.FormBody;
import okhttp3.RequestBody;


/**
 * {@link IRestApi} implementation for retrieving data from the network.
 */
public class RestApi implements IRestApi {

    private final Context context;
    private final FeedEntityJsonMapper feedEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context              {@link android.content.Context}.
     * @param feedEntityJsonMapper {@link FeedEntityJsonMapper}.
     */
    public RestApi(Context context, FeedEntityJsonMapper feedEntityJsonMapper) {
        if (context == null || feedEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.feedEntityJsonMapper = feedEntityJsonMapper;
    }

//    @Override
//    public Call<FeedEntity> fetchFeed(String q, String apiKey, long page, int pageSize) {
//        return null;
//    }

    @Override
    public Observable<List<FeedEntity>> feedEntityList() {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseFeedEntities = getFeedEntitiesFromApi();
                    if (responseFeedEntities != null) {
                        emitter.onNext(feedEntityJsonMapper.transformFeedEntityCollection(
                                responseFeedEntities));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }

//    @Override public Observable<FeedEntity> feedEntityById(final int feedId) {
//        return Observable.create(emitter -> {
//            if (isThereInternetConnection()) {
//                try {
//                    String responseFeedDetails = getFeedDetailsFromApi(feedId);
//                    if (responseFeedDetails != null) {
//                        emitter.onNext(feedEntityJsonMapper.transformFeedEntity(responseFeedDetails));
//                        emitter.onComplete();
//                    } else {
//                        emitter.onError(new NetworkConnectionException());
//                    }
//                } catch (Exception e) {
//                    emitter.onError(new NetworkConnectionException(e.getCause()));
//                }
//            } else {
//                emitter.onError(new NetworkConnectionException());
//            }
//        });
//    }

    private String getFeedEntitiesFromApi() throws MalformedURLException {
//        return ApiConnection.createGET(API_URL_GET_FEED_LIST).requestSyncCall();
        RequestBody formBody = new FormBody.Builder()
                .add("q", "movies")
                .add("apiKey", Constants.API_KEY)
                .add("page", "1")
                .add("pageSize", "10")
                .build();
        return ApiConnection.createGET(API_URL_GET_FEED_LIST).createReqWithBody(formBody);
    }

//    private String getFeedDetailsFromApi(int feedId) throws MalformedURLException {
//        String apiUrl = API_URL_GET_FEED_DETAILS + feedId + ".json";
//        return ApiConnection.createGET(apiUrl).requestSyncCall();
//    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}

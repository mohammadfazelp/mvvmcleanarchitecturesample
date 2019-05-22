package com.mvvmcleanarchitecturesample.data.net;

import com.mvvmcleanarchitecturesample.data.entity.FeedEntity;
import com.mvvmcleanarchitecturesample.data.utils.Constants;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRestApi {


//    @GET("/v2/everything")
//    Call<FeedEntity> fetchFeed(@Query("q") String q,
//                               @Query("apiKey") String apiKey,
//                               @Query("page") long page,
//                               @Query("pageSize") int pageSize);

    /**
     * Api url for getting all feeds
     */
    String API_URL_GET_FEED_LIST = Constants.BASE_URL + "/v2/everything";

    /** Api url for getting a feed profile: Remember to concatenate id + 'json' */
//    String API_URL_GET_FEED_DETAILS = Constants.BASE_URL + "feed_";

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link FeedEntity}.
     */
    Observable<List<FeedEntity>> feedEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link FeedEntity}.
     *
     * @param feedId The feed id used to get feed data.
     */
//    Observable<FeedEntity> feedEntityById(final int feedId);
}

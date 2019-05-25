package com.mvvmcleanarchitecturesample.data.api;

import com.mvvmcleanarchitecturesample.data.entity.FeedEntity;
import com.mvvmcleanarchitecturesample.data.utils.Constants;

import java.util.List;

import io.reactivex.Observable;

public interface IRestApi {

    /**
     * Api url for getting feed
     */
    String API_URL_GET_FEED = Constants.BASE_URL + "/v2/everything";

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link FeedEntity}.
     */
    Observable<List<FeedEntity>> feedEntityList();

    /**
     * Retrieves an {@link Observable} which will emit a {@link FeedEntity}.
     *
     * @param feedId The feed id used to get feed data.
     */
    Observable<FeedEntity> feedEntityById(final int feedId);
}

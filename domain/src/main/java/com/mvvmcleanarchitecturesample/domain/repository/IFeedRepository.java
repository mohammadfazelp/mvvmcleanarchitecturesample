package com.mvvmcleanarchitecturesample.domain.repository;

import com.mvvmcleanarchitecturesample.domain.model.Feed;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link Feed} related data.
 */
public interface IFeedRepository {
    /**
     * Get an {@link Observable} which will emit a List of {@link Feed}.
     */
//    Observable<List<Feed>> feeds();

    /**
     * Get an {@link Observable} which will emit a {@link Feed}.
     *
     * @param id The id used to retrieve data.
     */
    Observable<Feed> feed(final int id);
}

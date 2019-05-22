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
    Observable<List<Feed>> feeds();

    /**
     * Get an {@link Observable} which will emit a {@link Feed}.
     *
     * @param userId The user id used to retrieve user data.
     */
    Observable<Feed> feed(final int userId);
}

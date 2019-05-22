
package com.mvvmcleanarchitecturesample.data.cache;

import com.mvvmcleanarchitecturesample.data.entity.FeedEntity;

import io.reactivex.Observable;

/**
 * An interface representing a feed Cache.
 */
public interface FeedCache {
  /**
   * Gets an {@link Observable} which will emit a {@link FeedEntity}.
   *
   * @param feedId The feed id to retrieve data.
   */
  Observable<FeedEntity> get(final int feedId);

  /**
   * Puts and element into the cache.
   *
   * @param feedEntity Element to insert in the cache.
   */
  void put(FeedEntity feedEntity);

  /**
   * Checks if an element (FeedEntity) exists in the cache.
   *
   * @param feedId The id used to look for inside the cache.
   * @return true if the element is cached, otherwise false.
   */
  boolean isCached(final int feedId);

  /**
   * Checks if the cache is expired.
   *
   * @return true, the cache is expired, otherwise false.
   */
  boolean isExpired();

  /**
   * Evict all elements of the cache.
   */
  void evictAll();
}

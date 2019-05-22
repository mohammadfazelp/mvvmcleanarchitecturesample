
package com.mvvmcleanarchitecturesample.data.cache;

import android.content.Context;

import com.mvvmcleanarchitecturesample.data.cache.serializer.Serializer;
import com.mvvmcleanarchitecturesample.data.entity.FeedEntity;
import com.mvvmcleanarchitecturesample.data.exception.FeedNotFoundException;
import com.mvvmcleanarchitecturesample.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link FeedCache} implementation.
 */
@Singleton
public class FeedCacheImpl implements FeedCache {

  private static final String SETTINGS_FILE_NAME = "com.fernandocejas.android10.SETTINGS";
  private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";

  private static final String DEFAULT_FILE_NAME = "feed_";
  private static final long EXPIRATION_TIME = 60 * 10 * 1000;

  private final Context context;
  private final File cacheDir;
  private final Serializer serializer;
  private final FileManager fileManager;
  private final ThreadExecutor threadExecutor;

  /**
   * Constructor of the class {@link FeedCacheImpl}.
   *
   * @param context A
   * @param serializer {@link Serializer} for object serialization.
   * @param fileManager {@link FileManager} for saving serialized objects to the file system.
   */
  @Inject
  FeedCacheImpl(Context context, Serializer serializer,
                FileManager fileManager, ThreadExecutor executor) {
    if (context == null || serializer == null || fileManager == null || executor == null) {
      throw new IllegalArgumentException("Invalid null parameter");
    }
    this.context = context.getApplicationContext();
    this.cacheDir = this.context.getCacheDir();
    this.serializer = serializer;
    this.fileManager = fileManager;
    this.threadExecutor = executor;
  }

  @Override
  public Observable<FeedEntity> get(final int feedId) {
    return Observable.create(emitter -> {
      final File feedEntityFile = FeedCacheImpl.this.buildFile(feedId);
      final String fileContent = FeedCacheImpl.this.fileManager.readFileContent(feedEntityFile);
      final FeedEntity feedEntity =
          FeedCacheImpl.this.serializer.deserialize(fileContent, FeedEntity.class);

      if (feedEntity != null) {
        emitter.onNext(feedEntity);
        emitter.onComplete();
      } else {
        emitter.onError(new FeedNotFoundException());
      }
    });
  }

  @Override
  public void put(FeedEntity feedEntity) {
    if (feedEntity != null) {
      final File feedEntityFile = this.buildFile((int) feedEntity.getId());
      if (!isCached((int) feedEntity.getId())) {
        final String jsonString = this.serializer.serialize(feedEntity, FeedEntity.class);
        this.executeAsynchronously(new CacheWriter(this.fileManager, feedEntityFile, jsonString));
        setLastCacheUpdateTimeMillis();
      }
    }
  }

  @Override
  public boolean isCached(int feedId) {
    final File feedEntityFile = this.buildFile(feedId);
    return this.fileManager.exists(feedEntityFile);
  }

  @Override
  public boolean isExpired() {
    long currentTime = System.currentTimeMillis();
    long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

    boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME);

    if (expired) {
      this.evictAll();
    }

    return expired;
  }

  @Override
  public void evictAll() {
    this.executeAsynchronously(new CacheEvictor(this.fileManager, this.cacheDir));
  }

  /**
   * Build a file, used to be inserted in the disk cache.
   *
   * @param feedId The id feed to build the file.
   * @return A valid file.
   */
  private File buildFile(int feedId) {
    final StringBuilder fileNameBuilder = new StringBuilder();
    fileNameBuilder.append(this.cacheDir.getPath());
    fileNameBuilder.append(File.separator);
    fileNameBuilder.append(DEFAULT_FILE_NAME);
    fileNameBuilder.append(feedId);

    return new File(fileNameBuilder.toString());
  }

  /**
   * Set in millis, the last time the cache was accessed.
   */
  private void setLastCacheUpdateTimeMillis() {
    final long currentMillis = System.currentTimeMillis();
    this.fileManager.writeToPreferences(this.context, SETTINGS_FILE_NAME,
        SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis);
  }

  /**
   * Get in millis, the last time the cache was accessed.
   */
  private long getLastCacheUpdateTimeMillis() {
    return this.fileManager.getFromPreferences(this.context, SETTINGS_FILE_NAME,
        SETTINGS_KEY_LAST_CACHE_UPDATE);
  }

  /**
   * Executes a {@link Runnable} in another Thread.
   *
   * @param runnable {@link Runnable} to execute
   */
  private void executeAsynchronously(Runnable runnable) {
    this.threadExecutor.execute(runnable);
  }

  /**
   * {@link Runnable} class for writing to disk.
   */
  private static class CacheWriter implements Runnable {
    private final FileManager fileManager;
    private final File fileToWrite;
    private final String fileContent;

    CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
      this.fileManager = fileManager;
      this.fileToWrite = fileToWrite;
      this.fileContent = fileContent;
    }

    @Override
    public void run() {
      this.fileManager.writeToFile(fileToWrite, fileContent);
    }
  }

  /**
   * {@link Runnable} class for evicting all the cached files
   */
  private static class CacheEvictor implements Runnable {
    private final FileManager fileManager;
    private final File cacheDir;

    CacheEvictor(FileManager fileManager, File cacheDir) {
      this.fileManager = fileManager;
      this.cacheDir = cacheDir;
    }

    @Override
    public void run() {
      this.fileManager.clearDirectory(this.cacheDir);
    }
  }
}

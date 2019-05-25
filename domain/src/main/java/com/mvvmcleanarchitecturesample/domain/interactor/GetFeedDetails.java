
package com.mvvmcleanarchitecturesample.domain.interactor;

import com.mvvmcleanarchitecturesample.domain.executor.PostExecutionThread;
import com.mvvmcleanarchitecturesample.domain.executor.ThreadExecutor;
import com.mvvmcleanarchitecturesample.domain.model.Feed;
import com.mvvmcleanarchitecturesample.domain.repository.IFeedRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Feed}.
 */
public class GetFeedDetails extends UseCase<Feed, GetFeedDetails.Params> {

    private final IFeedRepository feedRepository;

    @Inject
    GetFeedDetails(IFeedRepository feedRepository, ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.feedRepository = feedRepository;
    }

    @Override
    Observable<Feed> buildUseCaseObservable(Params params) {
//        Preconditions.checkNotNull(params);
        return this.feedRepository.feed(params.feedId);
    }

    public static final class Params {

        private final int feedId;

        private Params(int feedId) {
            this.feedId = feedId;
        }

        public static Params forFeed(int feedId) {
            return new Params(feedId);
        }
    }
}

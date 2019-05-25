//package com.mvvmcleanarchitecturesample.domain.interactor.feed;
//
//import com.mvvmcleanarchitecturesample.domain.executor.PostExecutionThread;
//import com.mvvmcleanarchitecturesample.domain.executor.ThreadExecutor;
//import com.mvvmcleanarchitecturesample.domain.interactor.UseCase;
//import com.mvvmcleanarchitecturesample.domain.model.Feed;
//import com.mvvmcleanarchitecturesample.domain.repository.IFeedRepository;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import io.reactivex.Observable;
//
///**
// * This class is an implementation of {@link UseCase} that represents a use case for
// * retrieving a collection of all {@link Feed}.
// */
//public class GetFeedList extends UseCase<List<Feed>, Void> {
//
//    private final IFeedRepository feedRepository;
//
//    @Inject
//    GetFeedList(IFeedRepository feedRepository, ThreadExecutor threadExecutor,
//                PostExecutionThread postExecutionThread) {
//        super(threadExecutor, postExecutionThread);
//        this.feedRepository = feedRepository;
//    }
//
//    @Override
//    Observable<List<Feed>> buildUseCaseObservable(Void unused) {
//        return this.feedRepository.feeds();
//    }
//}

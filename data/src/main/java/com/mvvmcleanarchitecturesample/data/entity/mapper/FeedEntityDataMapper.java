package com.mvvmcleanarchitecturesample.data.entity.mapper;

import com.mvvmcleanarchitecturesample.data.entity.ArticleEntity;
import com.mvvmcleanarchitecturesample.data.entity.FeedEntity;
import com.mvvmcleanarchitecturesample.domain.model.Article;
import com.mvvmcleanarchitecturesample.domain.model.Feed;
import com.mvvmcleanarchitecturesample.domain.model.Source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Mapper class used to transform {@link com.mvvmcleanarchitecturesample.data.entity.FeedEntity}
 * (in the data layer) to {@link com.mvvmcleanarchitecturesample.domain.model.Feed} in the domain layer.
 */
@Singleton
public class FeedEntityDataMapper {

    @Inject
    FeedEntityDataMapper() {
    }

    /**
     * Transform a {@link com.mvvmcleanarchitecturesample.data.entity.FeedEntity} into
     * an {@link com.mvvmcleanarchitecturesample.domain.model.Feed}.
     *
     * @param feedEntity Object to be transformed.
     * @return {@link com.mvvmcleanarchitecturesample.domain.model.Feed}
     * if valid {@link com.mvvmcleanarchitecturesample.data.entity.FeedEntity} otherwise null.
     */
    public Feed transform(FeedEntity feedEntity) {
        Feed feed = null;
        if (feedEntity != null) {
            feed = new Feed(feedEntity.getId());
            feed.setStatus(feedEntity.getStatus());
            feed.setTotalResults(feedEntity.getTotalResults());
            List<Article> articles = new ArrayList<>();
            for (ArticleEntity articleEntity : feedEntity.getArticleEntities()) {
                Article ar = new Article();
                ar.setAuthor(articleEntity.getAuthor());
                ar.setDescription(articleEntity.getDescription());
                ar.setPublishedAt(articleEntity.getPublishedAt());
                Source source = new Source();
                source.setName(articleEntity.getSourceEntity().getName());
                ar.setSource(source);
                ar.setTitle(articleEntity.getTitle());
                ar.setUrlToImage(articleEntity.getUrlToImage());
                articles.add(ar);
            }
            feed.setArticleEntities(articles);
        }
        return feed;
    }

    /**
     * Transform a List of {@link FeedEntity} into a Collection of {@link Feed}.
     *
     * @param feedEntityCollection Object Collection to be transformed.
     * @return {@link Feed} if valid {@link FeedEntity} otherwise null.
     */
    public List<Feed> transform(Collection<FeedEntity> feedEntityCollection) {
        final List<Feed> feedList = new ArrayList<>(20);
        for (FeedEntity feedEntity : feedEntityCollection) {
            final Feed feed = transform(feedEntity);
            if (feed != null) {
                feedList.add(feed);
            }
        }
        return feedList;
    }
}

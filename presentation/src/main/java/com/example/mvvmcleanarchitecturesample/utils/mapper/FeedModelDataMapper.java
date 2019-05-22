package com.example.mvvmcleanarchitecturesample.utils.mapper;


import com.example.mvvmcleanarchitecturesample.di.PerActivity;
import com.example.mvvmcleanarchitecturesample.model.FeedModel;
import com.mvvmcleanarchitecturesample.data.entity.ArticleEntity;
import com.mvvmcleanarchitecturesample.domain.model.Article;
import com.mvvmcleanarchitecturesample.domain.model.Feed;
import com.mvvmcleanarchitecturesample.domain.model.Source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Feed} (in the domain layer) to {@link FeedModel} in the
 * presentation layer.
 */
@PerActivity
public class FeedModelDataMapper {

    @Inject
    public FeedModelDataMapper() {
    }

    /**
     * Transform a {@link Feed} into an {@link FeedModel}.
     *
     * @param feed Object to be transformed.
     * @return {@link FeedModel}.
     */
    public FeedModel transform(Feed feed) {
        if (feed == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final FeedModel feedModel = new FeedModel(feed.getId());
        feedModel.setStatus(feed.getStatus());
        feedModel.setTotalResults(feed.getTotalResults());
        List<Article> articles = new ArrayList<>();
        for (Article article : feed.getArticleEntities()) {
            Article ar = new Article();
            ar.setAuthor(article.getAuthor());
            ar.setDescription(article.getDescription());
            ar.setPublishedAt(article.getPublishedAt());
            Source source = new Source();
            source.setName(article.getSource().getName());
            ar.setSource(source);
            ar.setTitle(article.getTitle());
            ar.setUrlToImage(article.getUrlToImage());
            articles.add(ar);
        }
        feed.setArticleEntities(articles);
        return feedModel;
    }

    /**
     * Transform a Collection of {@link Feed} into a Collection of {@link FeedModel}.
     *
     * @param feedsCollection Objects to be transformed.
     * @return List of {@link FeedModel}.
     */
    public Collection<FeedModel> transform(Collection<Feed> feedsCollection) {
        Collection<FeedModel> feedModelsCollection;

        if (feedsCollection != null && !feedsCollection.isEmpty()) {
            feedModelsCollection = new ArrayList<>();
            for (Feed feed : feedsCollection) {
                feedModelsCollection.add(transform(feed));
            }
        } else {
            feedModelsCollection = Collections.emptyList();
        }

        return feedModelsCollection;
    }
}


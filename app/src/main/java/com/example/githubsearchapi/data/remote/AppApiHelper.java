package com.example.githubsearchapi.data.remote;

import com.example.githubsearchapi.data.model.api.contributors.Contributor;
import com.example.githubsearchapi.data.model.api.searchrepositories.SearchRepositories;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    private final ApiHelper mApiHelper;

    @Inject
    AppApiHelper(ApiHelper apiHelper) {
        this.mApiHelper = apiHelper;
    }

    @Override
    public Single<SearchRepositories> searchRepositories(String q, int page, int perPage) {
        return mApiHelper.searchRepositories(q, page, perPage);
    }

    @Override
    public Single<List<Contributor>> getContributors(String contributorsUrl) {
        return mApiHelper.getContributors(contributorsUrl);
    }
}

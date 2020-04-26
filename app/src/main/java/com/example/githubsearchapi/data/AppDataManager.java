package com.example.githubsearchapi.data;


import com.example.githubsearchapi.data.local.db.DbHelper;
import com.example.githubsearchapi.data.local.prefs.PreferencesHelper;
import com.example.githubsearchapi.data.model.api.contributors.Contributors;
import com.example.githubsearchapi.data.model.api.searchrepositories.SearchRepositories;
import com.example.githubsearchapi.data.model.db.Contributor;
import com.example.githubsearchapi.data.model.db.Repository;
import com.example.githubsearchapi.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {


    private final ApiHelper mApiHelper;

    private final DbHelper mDbHelper;

    private final PreferencesHelper mPreferencesHelper;


    @Inject
    public AppDataManager(DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<Boolean> saveRepository(Repository repository) {
        return mDbHelper.saveRepository(repository);
    }

    @Override
    public Observable<List<Contributor>> getContributorsOfRepositoryById(String repositoryId) {
        return mDbHelper.getContributorsOfRepositoryById(repositoryId);
    }

    @Override
    public Observable<Boolean> saveContributors(List<Contributor> contributors) {
        return mDbHelper.saveContributors(contributors);
    }

    @Override
    public Single<SearchRepositories> searchRepositories(String q, int page, int perPage) {
        return mApiHelper.searchRepositories(q, page, perPage);
    }

    @Override
    public Single<List<Contributors>> getContributors(String url) {
        return mApiHelper.getContributors(url);
    }

    @Override
    public void saveRepositoryAndContributors(Repository repository, List<Contributor> contributors) {
        saveContributors(Observable.fromIterable(contributors)
                .map(contributor -> {
                    contributor.repositoryId = repository.id;
                    return contributor;
                }).toList().blockingGet());
        saveRepository(repository);
    }
}

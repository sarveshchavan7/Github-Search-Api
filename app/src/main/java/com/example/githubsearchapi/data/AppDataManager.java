package com.example.githubsearchapi.data;


import android.util.Log;

import com.example.githubsearchapi.data.local.db.DbHelper;
import com.example.githubsearchapi.data.local.prefs.PreferencesHelper;
import com.example.githubsearchapi.data.model.api.contributors.Contributor;
import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.data.model.api.searchrepositories.SearchRepositories;
import com.example.githubsearchapi.data.model.db.ContributorEntity;
import com.example.githubsearchapi.data.model.db.RepositoryEntity;
import com.example.githubsearchapi.data.remote.ApiHelper;
import com.example.githubsearchapi.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = AppDataManager.class.getSimpleName();

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
    public Observable<Boolean> saveRepository(RepositoryEntity repositoryEntity) {
        return mDbHelper.saveRepository(repositoryEntity);
    }

    @Override
    public Observable<List<ContributorEntity>> getContributorsOfRepositoryById(String repositoryId) {
        return mDbHelper.getContributorsOfRepositoryById(repositoryId);
    }

    @Override
    public Observable<Boolean> saveContributors(List<ContributorEntity> contributorEntities) {
        return mDbHelper.saveContributors(contributorEntities);
    }

    @Override
    public Single<SearchRepositories> searchRepositories(String q, int page, int perPage) {
        return mApiHelper.searchRepositories(q, page, perPage);
    }

    @Override
    public Single<List<Contributor>> getContributors(String url) {
        // Check in cache or else make api call
        return mApiHelper.getContributors(url);
    }

    @Override
    public RepositoryEntity convertItemToEntity(Items items) {
        return new RepositoryEntity(items.getName(),
                items.getFullName(),
                items.getId(),
                items.getDescription());
    }

    @Override
    public List<ContributorEntity> convertContributorsToEntity(Integer repositoryId, List<Contributor> contributors) {
        return Observable.fromIterable(contributors)
                .map(contributor -> new ContributorEntity(contributor.getId(),
                        repositoryId,
                        contributor.getUrl(),
                        contributor.getLogin())).toList().blockingGet();
    }


    @Override
    public Observable<Boolean> saveRepository(Items item) {
        return saveRepository(convertItemToEntity(item))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> saveRepositoryAndContributors(Items item, List<Contributor> contributors) {
        return saveContributors(convertContributorsToEntity(item.getId(), contributors));
    }
}
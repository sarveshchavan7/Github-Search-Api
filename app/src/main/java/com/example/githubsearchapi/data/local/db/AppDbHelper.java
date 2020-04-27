package com.example.githubsearchapi.data.local.db;

import android.util.Log;

import com.example.githubsearchapi.data.model.db.ContributorEntity;
import com.example.githubsearchapi.data.model.db.RepositoryEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    public static final String TAG = AppDbHelper.class.getSimpleName();

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<Boolean> saveRepository(final RepositoryEntity repositoryEntity) {
        return Observable.fromCallable(() -> {
            mAppDatabase.repositoryDao().insert(repositoryEntity);
            return true;
        });
    }

    @Override
    public Observable<List<ContributorEntity>> getContributorsOfRepositoryById(String repositoryId) {
        return mAppDatabase.contributorDao().loadAllByRepositoryId(repositoryId)
                .toObservable();
    }

    @Override
    public Observable<Boolean> saveContributors(final List<ContributorEntity> contributorEntities) {
        return Observable.fromCallable(() -> {
            mAppDatabase.contributorDao().insertAll(contributorEntities);
            return true;
        });
    }
}

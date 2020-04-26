package com.example.githubsearchapi.data.local.db;

import com.example.githubsearchapi.data.model.db.Contributor;
import com.example.githubsearchapi.data.model.db.Repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<Boolean> saveRepository(final Repository repository) {
        return Observable.fromCallable(() -> {
            mAppDatabase.repositoryDao().insert(repository);
            return true;
        });
    }

    @Override
    public Observable<List<Contributor>> getContributorsOfRepositoryById(String repositoryId) {
        return mAppDatabase.contributorDao().loadAllByRepositoryId(repositoryId)
                .toObservable();
    }

    @Override
    public Observable<Boolean> saveContributors(final List<Contributor> contributors) {
        return Observable.fromCallable(() -> {
            mAppDatabase.contributorDao().insertAll(contributors);
            return true;
        });
    }
}

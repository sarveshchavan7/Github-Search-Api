package com.example.githubsearchapi.data.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.data.remote.ApiHelper;
import com.example.githubsearchapi.utils.AppConstants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/*
*   DataSource for paging library jetpack
* */
public class ItemDataSource extends PageKeyedDataSource<Integer, Items> {

    private static final String TAG = ItemDataSource.class.getSimpleName();

    private final ApiHelper mApiHelper;

    private final CompositeDisposable mCompositeDisposable;

    private String mQueryString;

    ItemDataSource(String queryString, ApiHelper apiHelper, CompositeDisposable compositeDisposable) {
        this.mApiHelper = apiHelper;
        this.mCompositeDisposable = compositeDisposable;
        this.mQueryString = queryString;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Items> callback) {

        mCompositeDisposable.add(mApiHelper.searchRepositories(mQueryString,
                AppConstants.FIRST_PAGE, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositories -> {
                    if (repositories != null) {
                        Log.d(TAG, String.valueOf(repositories.getTotalCount()));
                        callback.onResult(repositories.getItems(),
                                null,
                                AppConstants.FIRST_PAGE + 1);
                    }
                }, throwable -> {
                    // TODO: wrap Items in a wrapper class which will hold enums -
                    //  loading,success,error and show error on ui
                    // TODO: log the error
                }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, Items> callback) {

    }


    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, Items> callback) {
        mCompositeDisposable.add(
                mApiHelper.searchRepositories(mQueryString, params.key, params.requestedLoadSize)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(repositories -> {
                            if (repositories != null) {
                                Integer key = (repositories.getTotalCount()
                                        >= params.requestedLoadSize * params.key)
                                        ? params.key + 1 : null;
                                Log.d(TAG, String.valueOf(key));
                                callback.onResult(repositories.getItems(), key);
                            }
                        }, throwable -> {
                            // TODO: wrap Items in a wrapper class which will hold enums -
                            //  loading,success,error and show error on ui
                            // TODO: log the error
                        }));
    }

    void invalidateAndDispose() {
        this.invalidate();
        mCompositeDisposable.dispose();
    }
}

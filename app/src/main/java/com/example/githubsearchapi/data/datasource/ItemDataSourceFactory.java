package com.example.githubsearchapi.data.datasource;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.data.remote.ApiHelper;

import io.reactivex.disposables.CompositeDisposable;


public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Items>> mItemLiveDataSource = new MutableLiveData<>();

    private ItemDataSource mItemDataSource;

    private String mQueryString;

    private ApiHelper mApiHelper;

    public ItemDataSourceFactory(ApiHelper apiHelper) {
        this.mApiHelper = apiHelper;
    }

    @Override
    public DataSource create() {
        if (mItemDataSource != null) {
            mItemDataSource.invalidateAndDispose();
        }

        mItemDataSource = new ItemDataSource(mQueryString, mApiHelper, new CompositeDisposable());

        mItemLiveDataSource.postValue(mItemDataSource);

        return mItemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Items>> getLiveDataSource() {
        return mItemLiveDataSource;
    }

    public void setQueryString(String queryString) {
        this.mQueryString = queryString;
    }
}

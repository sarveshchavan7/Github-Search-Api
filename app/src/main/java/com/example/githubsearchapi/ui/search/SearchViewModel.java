package com.example.githubsearchapi.ui.search;


import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.githubsearchapi.data.DataManager;
import com.example.githubsearchapi.data.datasource.ItemDataSourceFactory;
import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.ui.base.BaseViewModel;
import com.example.githubsearchapi.utils.AppConstants;
import com.example.githubsearchapi.utils.rx.SchedulerProvider;

public class SearchViewModel extends BaseViewModel {

    public static final String TAG = SearchViewModel.class.getSimpleName();

    public ItemDataSourceFactory mItemDataSourceFactory;

    public LiveData<PagedList<Items>> mItemPagedList;

    public SearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider,
                           ItemDataSourceFactory itemDataSourceFactory) {
        super(dataManager, schedulerProvider);
        this.mItemDataSourceFactory = itemDataSourceFactory;
        initPaging();
    }

    private void initPaging() {

        //LiveData<PageKeyedDataSource<Integer, Items>> liveDataSource = mItemDataSourceFactory.getLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(AppConstants.PER_PAGE).build();


        mItemPagedList = (new LivePagedListBuilder(mItemDataSourceFactory, pagedListConfig)).build();
    }

}

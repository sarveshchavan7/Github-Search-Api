package com.example.githubsearchapi.ui.search;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.githubsearchapi.R;
import com.example.githubsearchapi.data.DataManager;
import com.example.githubsearchapi.data.datasource.ItemDataSourceFactory;
import com.example.githubsearchapi.data.model.api.contributors.Contributors;
import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.ui.base.BaseViewModel;
import com.example.githubsearchapi.ui.search.details.DetailNavigator;
import com.example.githubsearchapi.utils.AppConstants;
import com.example.githubsearchapi.utils.rx.SchedulerProvider;

import java.util.List;


public class SearchViewModel extends BaseViewModel<DetailNavigator> {

    public static final String TAG = SearchViewModel.class.getSimpleName();

    public MutableLiveData<Items> mItemsLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Contributors>> mContributorsLiveData = new MutableLiveData<>();

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

    public Items getValue() {
        return mItemsLiveData.getValue();
    }

    public void getContributors(String url) {
        getCompositeDisposable().add(getDataManager()
                .getContributors(url)
                .subscribe(contributors -> {
                    mContributorsLiveData.setValue(contributors);
                }, throwable -> {
                    getNavigator().handleError(throwable);
                    getNavigator().showMessage(R.string.some_thing_went_wrong);
                })
        );
    }

}

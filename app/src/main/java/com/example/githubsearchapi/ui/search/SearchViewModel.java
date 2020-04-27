package com.example.githubsearchapi.ui.search;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.githubsearchapi.R;
import com.example.githubsearchapi.data.DataManager;
import com.example.githubsearchapi.data.datasource.ItemDataSourceFactory;
import com.example.githubsearchapi.data.model.api.contributors.Contributor;
import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.ui.base.BaseViewModel;
import com.example.githubsearchapi.ui.search.details.DetailNavigator;
import com.example.githubsearchapi.utils.AppConstants;
import com.example.githubsearchapi.utils.rx.SchedulerProvider;

import java.util.List;


public class SearchViewModel extends BaseViewModel<DetailNavigator> {

    public static final String TAG = SearchViewModel.class.getSimpleName();

    public MutableLiveData<Items> mItemsLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Contributor>> mContributorsLiveData = new MutableLiveData<>();

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

    public void getContributors(Items item, String url) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getContributors(url)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(contributors -> {
                    setIsLoading(false);
                    mContributorsLiveData.setValue(contributors);
                    getCompositeDisposable().add(getDataManager()
                            .saveRepository(item)
                            .subscribeOn(getSchedulerProvider().io())
                            .subscribe(aBoolean -> {
                                if (aBoolean) {
                                    getCompositeDisposable().add(getDataManager()
                                            .saveRepositoryAndContributors(item, contributors)
                                            .subscribeOn(getSchedulerProvider().io()).subscribe());
                                }
                            }));
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                    getNavigator().showMessage(R.string.some_thing_went_wrong);
                })
        );
    }

}

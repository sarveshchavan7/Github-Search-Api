package com.example.githubsearchapi;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.githubsearchapi.data.DataManager;
import com.example.githubsearchapi.data.datasource.ItemDataSourceFactory;
import com.example.githubsearchapi.ui.search.SearchViewModel;
import com.example.githubsearchapi.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(mDataManager, mSchedulerProvider, new ItemDataSourceFactory(mDataManager));
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}

package com.example.githubsearchapi.ui.search;

import android.os.Bundle;

import com.example.githubsearchapi.BR;
import com.example.githubsearchapi.R;
import com.example.githubsearchapi.ViewModelProviderFactory;
import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.databinding.ActivitySearchBinding;
import com.example.githubsearchapi.ui.base.BaseActivity;
import com.example.githubsearchapi.ui.search.details.DetailFragment;
import com.example.githubsearchapi.ui.search.repository.RepositoryFragment;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;


public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> {

    @Inject
    ViewModelProviderFactory factory;
    private SearchViewModel mSearchViewModel;
    private ActivitySearchBinding mActivitySearchBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public SearchViewModel getViewModel() {
        mSearchViewModel = new ViewModelProvider(this, factory).get(SearchViewModel.class);
        return mSearchViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySearchBinding = getViewDataBinding();
        setUpFragment();
        observeRepoClick();
    }

    private void setUpFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, new RepositoryFragment())
                .commit();
    }

    private void observeRepoClick() {
        getViewModel().mItemsLiveData.observe(this,
                items -> showDetailFragment());

    }

    private void showDetailFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, new DetailFragment())
                .addToBackStack(null)
                .commit();
    }


}

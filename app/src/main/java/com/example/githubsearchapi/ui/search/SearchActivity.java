package com.example.githubsearchapi.ui.search;

import android.os.Bundle;

import com.example.githubsearchapi.BR;
import com.example.githubsearchapi.R;
import com.example.githubsearchapi.ViewModelProviderFactory;
import com.example.githubsearchapi.databinding.ActivitySearchBinding;
import com.example.githubsearchapi.ui.base.BaseActivity;
import com.example.githubsearchapi.ui.search.details.DetailFragment;
import com.example.githubsearchapi.ui.search.repository.RepositoryFragment;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

/*
 *   Activity with shared view model between two fragments
 *   i.e RepositoryFragment, DetailFragment
 * */
public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> {

    @Inject
    ViewModelProviderFactory factory;

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
        return new ViewModelProvider(this, factory).get(SearchViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

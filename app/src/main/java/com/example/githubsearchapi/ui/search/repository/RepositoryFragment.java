package com.example.githubsearchapi.ui.search.repository;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;

import com.example.githubsearchapi.BR;
import com.example.githubsearchapi.R;
import com.example.githubsearchapi.ViewModelProviderFactory;
import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.databinding.FragmentRepositoryBinding;
import com.example.githubsearchapi.ui.base.BaseFragment;
import com.example.githubsearchapi.ui.search.SearchViewModel;

import javax.inject.Inject;

public class RepositoryFragment extends BaseFragment<FragmentRepositoryBinding, SearchViewModel> {

    private static final String TAG = RepositoryFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private SearchViewModel mSearchViewModel;
    private FragmentRepositoryBinding mFragmentRepositoryBinding;

    @Inject
    RepositoryAdapter mRepositoryAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_repository;
    }

    @Override
    public SearchViewModel getViewModel() {
        mSearchViewModel = new ViewModelProvider(getBaseActivity(), factory).get(SearchViewModel.class);
        return mSearchViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentRepositoryBinding = getViewDataBinding();
        initAdapter();
        initSearchView();
    }

    private void initSearchView() {
        mFragmentRepositoryBinding.svSearchRepo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchViewModel.mItemDataSourceFactory.setQueryString(query);
                mSearchViewModel.mItemDataSourceFactory.create();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initAdapter() {
        mSearchViewModel.mItemPagedList.observe(getViewLifecycleOwner(), (PagedList<Items> items) -> {
            mRepositoryAdapter.submitList(items);
        });
        mFragmentRepositoryBinding.recyclerViewRepo.setAdapter(mRepositoryAdapter);
    }


}

package com.example.githubsearchapi.ui.search.details;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.githubsearchapi.BR;
import com.example.githubsearchapi.R;
import com.example.githubsearchapi.ViewModelProviderFactory;
import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.databinding.FragmentDetailBinding;
import com.example.githubsearchapi.ui.base.BaseFragment;
import com.example.githubsearchapi.ui.search.SearchViewModel;

import javax.inject.Inject;

public class DetailFragment extends BaseFragment<FragmentDetailBinding, SearchViewModel> implements DetailNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private SearchViewModel mSearchViewModel;
    private FragmentDetailBinding mFragmentDetailBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    public SearchViewModel getViewModel() {
        mSearchViewModel = new ViewModelProvider(getBaseActivity(), factory).get(SearchViewModel.class);
        return mSearchViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentDetailBinding = getViewDataBinding();
        initViews();
    }

    private void initViews() {
        getBaseActivity().setSupportActionBar(mFragmentDetailBinding.toolBar);

    }

    @Override
    public void handleError(Throwable throwable) {
        //TODO: log the error or show
    }

    @Override
    public void showMessage(Integer resourceId) {
        Toast.makeText(getBaseActivity(), getString(resourceId), Toast.LENGTH_SHORT).show();
    }
}

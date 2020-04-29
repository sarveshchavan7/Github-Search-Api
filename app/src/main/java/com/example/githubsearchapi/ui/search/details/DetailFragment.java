package com.example.githubsearchapi.ui.search.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.githubsearchapi.BR;
import com.example.githubsearchapi.R;
import com.example.githubsearchapi.ViewModelProviderFactory;
import com.example.githubsearchapi.databinding.FragmentDetailBinding;
import com.example.githubsearchapi.ui.base.BaseFragment;
import com.example.githubsearchapi.ui.search.SearchViewModel;


import javax.inject.Inject;

public class DetailFragment extends BaseFragment<FragmentDetailBinding, SearchViewModel> implements DetailNavigator {

    private static final String TAG = DetailFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    @Inject
    public DetailContributorAdapter mDetailContributorAdapter;

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
        return new ViewModelProvider(getBaseActivity(), factory).get(SearchViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        observerContributorData();
    }

    private void observerContributorData() {
        mViewModel.mContributorsLiveData.observe(getBaseActivity(),
                contributors -> mDetailContributorAdapter.updateContributorsList(contributors));
    }

    private void initViews() {
        getBaseActivity().setSupportActionBar(mViewDataBinding.toolBar);
        mViewDataBinding.rvContributor.setAdapter(mDetailContributorAdapter);
        mViewDataBinding.rvContributor.setLayoutManager(new GridLayoutManager(getBaseActivity(), 3));
    }

    @Override
    public void handleError(Throwable throwable) {
        //TODO: log the error or show
    }

    @Override
    public void showMessage(Integer resourceId) {
        Toast.makeText(getBaseActivity(), getString(resourceId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openWebView() {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(mViewDataBinding.tvProjectLink.getText().toString()));
            startActivity(intent);
        } catch (Exception e) {
            //TODO: log error
        }
    }
}

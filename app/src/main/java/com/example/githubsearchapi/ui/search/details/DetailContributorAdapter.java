package com.example.githubsearchapi.ui.search.details;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubsearchapi.data.model.api.contributors.Contributor;
import com.example.githubsearchapi.databinding.ItemDetailContributorViewBinding;
import com.example.githubsearchapi.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DetailContributorAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Contributor> mContributorList = new ArrayList<>();

    @NonNull
    @Override
    public ContributorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailContributorViewBinding itemDetailContributorViewBinding =
                ItemDetailContributorViewBinding
                        .inflate(LayoutInflater
                                .from(parent.getContext()), parent, false);
        return new ContributorsViewHolder(itemDetailContributorViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mContributorList.size();
    }

    public void updateContributorsList(List<Contributor> contributorList) {
        clearContributorsList();
        mContributorList.addAll(contributorList);
        notifyDataSetChanged();
    }

    private void clearContributorsList() {
        mContributorList.clear();
    }

    class ContributorsViewHolder extends BaseViewHolder {

        private ItemDetailContributorViewBinding mItemDetailContributorViewBinding;

        public ContributorsViewHolder(@NonNull ItemDetailContributorViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.mItemDetailContributorViewBinding = viewBinding;
        }

        @Override
        public void onBind(int position) {
            final Contributor contributor = mContributorList.get(position);
            DetailContributorViewModel detailContributorViewModel = new DetailContributorViewModel(contributor);
            mItemDetailContributorViewBinding.setViewModel(detailContributorViewModel);
        }
    }
}

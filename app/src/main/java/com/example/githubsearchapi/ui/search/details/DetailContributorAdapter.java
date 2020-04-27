package com.example.githubsearchapi.ui.search.details;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubsearchapi.data.model.db.Contributor;
import com.example.githubsearchapi.ui.base.BaseViewHolder;

import java.util.List;

public class DetailContributorAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Contributor> mContributorsList;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mContributorsList.size();
    }

    public void updateContributorsList(List<Contributor> contributorList) {
        clearContributorsList();
        mContributorsList.addAll(contributorList);
        notifyDataSetChanged();
    }

    private void clearContributorsList() {
        mContributorsList.clear();
    }

    class ContributorsViewHolder extends RecyclerView.ViewHolder {


        public ContributorsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

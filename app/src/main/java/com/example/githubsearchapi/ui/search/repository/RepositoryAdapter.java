package com.example.githubsearchapi.ui.search.repository;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.databinding.ItemRepositoryViewBinding;
import com.example.githubsearchapi.ui.base.BaseViewHolder;

public class RepositoryAdapter extends PagedListAdapter<Items, BaseViewHolder> {

    private static final String TAG = RepositoryAdapter.class.getSimpleName();

    private RepositoryAdapterListener mListener;

    public RepositoryAdapter() {
        super(Items.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRepositoryViewBinding itemRepositoryViewBinding = ItemRepositoryViewBinding
                .inflate(LayoutInflater.from(parent
                        .getContext()), parent, false);
        return new ItemViewHolder(itemRepositoryViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    class ItemViewHolder extends BaseViewHolder {

        private ItemRepositoryViewBinding mItemRepositoryViewBinding;

        ItemViewHolder(ItemRepositoryViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.mItemRepositoryViewBinding = viewBinding;
        }

        public void onBind(int position) {
            final Items items = getItem(position);
            if (items != null) {
                RepositoryItemViewModel mRepositoryItemViewModel = new RepositoryItemViewModel(items);
                mItemRepositoryViewBinding.setViewModel(mRepositoryItemViewModel);
                mItemRepositoryViewBinding.executePendingBindings();
                mItemRepositoryViewBinding.cardRepoItem.setOnClickListener(v -> {
                    mListener.onItemClicked(items);
                });
            }
        }
    }

    public interface RepositoryAdapterListener {

        void onItemClicked(Items items);
    }

    void setListener(RepositoryAdapterListener listener) {
        this.mListener = listener;
    }
}

package com.example.githubsearchapi.ui.search.repository;

import androidx.databinding.ObservableField;

import com.example.githubsearchapi.data.model.api.searchrepositories.Items;

public class RepositoryItemViewModel {

    public final ObservableField<String> name;

    public final ObservableField<String> desc;

    public final ObservableField<String> imgUrl;

    private final Items mItems;

    RepositoryItemViewModel(Items items) {
        this.mItems = items;
        name = new ObservableField<>(mItems.getName());
        desc = new ObservableField<>(mItems.getDescription());
        imgUrl = new ObservableField<>(mItems.getOwner().getAvatarUrl());
    }

}

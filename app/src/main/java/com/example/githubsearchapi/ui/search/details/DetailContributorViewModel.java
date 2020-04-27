package com.example.githubsearchapi.ui.search.details;

import androidx.databinding.ObservableField;

import com.example.githubsearchapi.data.model.api.contributors.Contributor;

public class DetailContributorViewModel {

    public final ObservableField<String> imgUrl;

    public final ObservableField<String> contributorName;

    private Contributor mContributor;

    DetailContributorViewModel(Contributor contributor) {
        this.mContributor = contributor;
        imgUrl = new ObservableField<>(contributor.getAvatarUrl());
        contributorName = new ObservableField<>(contributor.getLogin());
    }
}

package com.example.githubsearchapi.ui.search.repository;

import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryFragmentModule {

    @Provides
    RepositoryAdapter provideBlogAdapter() {
        return new RepositoryAdapter();
    }

}

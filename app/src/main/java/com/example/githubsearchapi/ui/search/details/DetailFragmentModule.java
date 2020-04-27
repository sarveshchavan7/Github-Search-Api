package com.example.githubsearchapi.ui.search.details;


import dagger.Module;
import dagger.Provides;

@Module
public class DetailFragmentModule {

    @Provides
    DetailContributorAdapter provideBlogAdapter() {
        return new DetailContributorAdapter();
    }

}

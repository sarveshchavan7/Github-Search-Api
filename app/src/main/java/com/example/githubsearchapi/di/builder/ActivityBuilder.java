package com.example.githubsearchapi.di.builder;

import com.example.githubsearchapi.ui.search.SearchActivity;
import com.example.githubsearchapi.ui.search.details.DetailFragmentProvider;
import com.example.githubsearchapi.ui.search.repository.RepositoryFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(
            modules = {
                    RepositoryFragmentProvider.class,
                    DetailFragmentProvider.class
            }
    )
    abstract SearchActivity provideSearchActivity();
}

package com.example.githubsearchapi.ui.search.repository;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RepositoryFragmentProvider {

    @ContributesAndroidInjector(modules = RepositoryFragmentModule.class)
    abstract RepositoryFragment provideRepositoryFragment();

}

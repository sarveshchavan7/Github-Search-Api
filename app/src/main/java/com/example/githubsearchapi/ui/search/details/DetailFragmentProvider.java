package com.example.githubsearchapi.ui.search.details;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailFragmentProvider {

    @ContributesAndroidInjector(modules = DetailFragmentModule.class)
    abstract DetailFragment provideDetailFragment();

}

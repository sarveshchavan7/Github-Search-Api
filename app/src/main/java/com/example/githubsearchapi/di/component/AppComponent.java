package com.example.githubsearchapi.di.component;

import android.app.Application;

import com.example.githubsearchapi.GithubApp;
import com.example.githubsearchapi.di.builder.ActivityBuilder;
import com.example.githubsearchapi.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilder.class,
                AppModule.class,
        }
)
public interface AppComponent extends AndroidInjector<GithubApp> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

}

package com.example.githubsearchapi.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.githubsearchapi.BuildConfig;
import com.example.githubsearchapi.data.AppDataManager;
import com.example.githubsearchapi.data.DataManager;
import com.example.githubsearchapi.data.local.db.AppDatabase;
import com.example.githubsearchapi.data.local.db.AppDbHelper;
import com.example.githubsearchapi.data.local.db.DbHelper;
import com.example.githubsearchapi.data.local.prefs.AppPreferencesHelper;
import com.example.githubsearchapi.data.local.prefs.PreferencesHelper;
import com.example.githubsearchapi.data.remote.ApiHelper;
import com.example.githubsearchapi.di.DatabaseInfo;
import com.example.githubsearchapi.di.PreferenceInfo;
import com.example.githubsearchapi.utils.AppConstants;
import com.example.githubsearchapi.utils.rx.AppSchedulerProvider;
import com.example.githubsearchapi.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}

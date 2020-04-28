package com.example.githubsearchapi.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.githubsearchapi.di.PreferenceInfo;

import javax.inject.Inject;

/*
 *  AppPreferencesHelper has implementation of PreferencesHelper
 *  with SharedPreferences support
 * */
public class AppPreferencesHelper implements PreferencesHelper {
    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }
}

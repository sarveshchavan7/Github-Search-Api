package com.example.githubsearchapi.data;

import com.example.githubsearchapi.data.local.db.DbHelper;
import com.example.githubsearchapi.data.local.prefs.PreferencesHelper;
import com.example.githubsearchapi.data.model.db.Contributor;
import com.example.githubsearchapi.data.model.db.Repository;
import com.example.githubsearchapi.data.remote.ApiHelper;

import java.util.List;


public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void saveRepositoryAndContributors(Repository repository, List<Contributor> contributors);

}

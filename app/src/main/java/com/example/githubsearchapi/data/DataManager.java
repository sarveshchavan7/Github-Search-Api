package com.example.githubsearchapi.data;

import com.example.githubsearchapi.data.local.db.DbHelper;
import com.example.githubsearchapi.data.local.prefs.PreferencesHelper;
import com.example.githubsearchapi.data.model.api.contributors.Contributor;
import com.example.githubsearchapi.data.model.api.searchrepositories.Items;
import com.example.githubsearchapi.data.model.db.ContributorEntity;
import com.example.githubsearchapi.data.model.db.RepositoryEntity;
import com.example.githubsearchapi.data.remote.ApiHelper;

import java.util.List;

import io.reactivex.Observable;


public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {


    RepositoryEntity convertItemToEntity(Items items);

    List<ContributorEntity> convertContributorsToEntity(Integer repositoryId, List<Contributor> contributors);

    Observable<Boolean> saveRepository(Items item);

    Observable<Boolean> saveRepositoryAndContributors(Items item, List<Contributor> contributors);
}

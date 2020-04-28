package com.example.githubsearchapi.data.local.db;

import com.example.githubsearchapi.data.model.db.ContributorEntity;
import com.example.githubsearchapi.data.model.db.RepositoryEntity;

import java.util.List;

import io.reactivex.Observable;


public interface DbHelper {

    Observable<Boolean> saveRepository(RepositoryEntity repositoryEntity);

    Observable<List<ContributorEntity>> getContributorsOfRepositoryByUrl(String contributorsUrl);

    Observable<Boolean> saveContributors(List<ContributorEntity> contributorEntities);

}


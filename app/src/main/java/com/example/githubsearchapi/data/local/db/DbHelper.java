package com.example.githubsearchapi.data.local.db;

import com.example.githubsearchapi.data.model.db.Contributor;
import com.example.githubsearchapi.data.model.db.Repository;

import java.util.List;

import io.reactivex.Observable;


public interface DbHelper {

    Observable<Boolean> saveRepository(Repository repository);

    Observable<List<Contributor>> getContributosOfRepositoryId(String repositoryId);

    Observable<Boolean> saveContributors(List<Contributor> contributors);

}

package com.example.githubsearchapi.data.remote;

import com.example.githubsearchapi.data.model.api.contributors.Contributors;
import com.example.githubsearchapi.data.model.api.searchrepositories.SearchRepositories;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiHelper {

    @GET
    Single<SearchRepositories> searchRepositories(@Query("q") String q,
                                                  @Query("page") int page,
                                                  @Query("per_page") int perPage);

    @GET
    Single<List<Contributors>> getContributors(@Url String url);


}

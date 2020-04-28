package com.example.githubsearchapi.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.githubsearchapi.data.model.db.ContributorEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ContributorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ContributorEntity> contributorEntities);

    @Query("SELECT * FROM contributors where contributors_url =:contributorsUrl")
    Single<List<ContributorEntity>> loadAllByContributorsUrl(String contributorsUrl);

}

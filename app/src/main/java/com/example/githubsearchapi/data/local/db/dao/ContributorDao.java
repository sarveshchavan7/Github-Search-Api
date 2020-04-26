package com.example.githubsearchapi.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.githubsearchapi.data.model.db.Contributor;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ContributorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Contributor> contributors);

    @Query("SELECT * FROM contributors where repository_id =:repositoryId")
    Single<List<Contributor>> loadAllByRepositoryId(String repositoryId);

}

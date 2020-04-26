package com.example.githubsearchapi.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.githubsearchapi.data.model.db.Repository;

@Dao
public interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Repository repository);
}

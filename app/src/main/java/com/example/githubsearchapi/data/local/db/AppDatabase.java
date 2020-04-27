package com.example.githubsearchapi.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.githubsearchapi.data.local.db.dao.ContributorDao;
import com.example.githubsearchapi.data.local.db.dao.RepositoryDao;
import com.example.githubsearchapi.data.model.db.ContributorEntity;
import com.example.githubsearchapi.data.model.db.RepositoryEntity;

@Database(entities = {RepositoryEntity.class, ContributorEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RepositoryDao repositoryDao();

    public abstract ContributorDao contributorDao();

}

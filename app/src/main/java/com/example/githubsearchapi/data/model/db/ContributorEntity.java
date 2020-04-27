package com.example.githubsearchapi.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(
        tableName = "contributors",
        foreignKeys = @ForeignKey(
                entity = RepositoryEntity.class,
                parentColumns = "id",
                childColumns = "repository_id"
        )
)
public class ContributorEntity {

    @PrimaryKey
    public Integer id;

    public ContributorEntity(Integer id, Integer repositoryId, String url, String login) {
        this.id = id;
        this.repositoryId = repositoryId;
        this.url = url;
        this.login = login;
    }

    @Expose
    @SerializedName("repository_id")
    @ColumnInfo(name = "repository_id")
    public Integer repositoryId;

    public String url;

    public String login;

}

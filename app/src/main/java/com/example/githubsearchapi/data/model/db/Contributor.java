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
                entity = Repository.class,
                parentColumns = "id",
                childColumns = "repository_id"
        )
)
public class Contributor {

    @PrimaryKey
    public Integer id;

    @Expose
    @SerializedName("repository_id")
    @ColumnInfo(name = "repository_id")
    public Integer repositoryId;

    public String url;

    public String login;

}

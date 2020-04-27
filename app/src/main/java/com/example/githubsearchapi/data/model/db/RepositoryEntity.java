package com.example.githubsearchapi.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "repository")
public class RepositoryEntity {

    public String name;

    @Expose
    @SerializedName("full_name")
    @ColumnInfo(name = "full_name")
    public String fullName;

    @PrimaryKey
    public Integer id;

    public String description;

    public RepositoryEntity(String name, String fullName, Integer id, String description) {
        this.name = name;
        this.fullName = fullName;
        this.id = id;
        this.description = description;
    }
}

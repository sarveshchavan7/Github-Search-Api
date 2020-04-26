package com.example.githubsearchapi.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "repository")
public class Repository {

    public String name;

    @Expose
    @SerializedName("full_name")
    @ColumnInfo(name = "full_name")
    public String fullName;

    @PrimaryKey
    public Integer id;


    public String description;
}

package com.marbax.githubapi.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Users")
data class User(
    @field:ColumnInfo(name = "Login") @SerializedName("login") var login: String,
    @field:ColumnInfo(name = "Type") @SerializedName("type") var type: String,
    @field:ColumnInfo(name = "AvatarUrl") @SerializedName("avatar_url") var avatar_url: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
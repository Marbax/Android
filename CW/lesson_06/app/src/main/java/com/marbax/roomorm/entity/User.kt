package com.marbax.roomorm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @field:ColumnInfo(name = "FirstName") var firstName: String,
    @field:ColumnInfo(name = "LastName") var lastName: String
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}
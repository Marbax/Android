package com.marbax.roomorm.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marbax.roomorm.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao():UserDao
}

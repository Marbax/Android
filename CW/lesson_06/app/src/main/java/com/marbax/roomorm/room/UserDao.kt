package com.marbax.roomorm.room

import androidx.room.*
import com.marbax.roomorm.entity.User
import io.reactivex.Maybe

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :id")
    fun get(id: Int): Maybe<User>

    @Query("SELECT * FROM users")
    fun get(): Maybe<List<User>>

    @Insert
    fun add(vararg users: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}
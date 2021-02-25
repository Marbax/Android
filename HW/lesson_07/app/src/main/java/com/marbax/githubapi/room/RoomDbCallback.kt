package com.marbax.githubapi.room

import com.marbax.githubapi.entity.User

interface RoomDbCallback {
    fun onUsersReady(users: List<User>)
    fun onUserAdded()
    fun onUserUpdated()
    fun onUserDeleted()
}
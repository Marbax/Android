package com.marbax.roomorm.room

import com.marbax.roomorm.entity.User

interface RoomDbCallback {
    fun onUsersReady(users: List<User>){}
    fun onUserAdded(){}
    fun onUserUpdated(){}
    fun onUserDeleted(){}
}
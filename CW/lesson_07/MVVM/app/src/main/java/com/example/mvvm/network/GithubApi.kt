package com.example.mvvm.network

import com.example.mvvm.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users")
    suspend fun getUsers(): List<User>

    @GET("/users/{login}")
    suspend fun getUserInfo(@Path("login") login: String): User

}
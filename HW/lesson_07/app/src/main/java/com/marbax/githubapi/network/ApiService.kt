package com.marbax.githubapi.network

import com.marbax.githubapi.entity.User
import com.marbax.githubapi.network.ApiClient.PER_PAGE
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(@Query("per_page") perPage: Int = PER_PAGE, @Query("since") since: Int = 1):
            Call<List<User>>
}


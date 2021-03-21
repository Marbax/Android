package com.example.mvvm.network

import com.example.mvvm.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class ApiProvider {

    private lateinit var okHttpClient: OkHttpClient

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun retrofit() = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BuildConfig.SERVER_URL)
        .client(okHttpClient)
        .build()

    protected fun initOkHttpClient() {
        this.okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    abstract fun isInitialized(): Boolean

    fun <T> getApi(apiType: Class<T>): T {
        check(isInitialized())
        val api = retrofit().create(apiType)
        return api
    }

    protected val isOkHttpInitialized
        get() = this::okHttpClient.isInitialized
}
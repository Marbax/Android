package com.example.mvvm.network

object GlobalApi: ApiProvider() {

    override fun isInitialized() = isOkHttpInitialized

    fun initialize(){
        initOkHttpClient()
    }

    val github: GithubApi
        get() = getApi(GithubApi::class.java)
}
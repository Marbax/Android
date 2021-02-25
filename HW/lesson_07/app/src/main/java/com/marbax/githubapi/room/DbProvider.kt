package com.marbax.githubapi.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.marbax.githubapi.entity.User
import com.marbax.githubapi.network.ApiClient
import com.marbax.githubapi.network.ApiService
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.operators.completable.CompletableFromAction
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DbProvider {

    private const val DB_NAME = "githubDb"
    private lateinit var db: GitHubDatabase
    private val disposable: CompositeDisposable =
        CompositeDisposable()

    fun init(context: Context) {
        db = Room.databaseBuilder(
            context,
            GitHubDatabase::class.java,
            DB_NAME
        ).build()
    }

    fun addUser(user: User, callback: RoomDbCallback) {
        val disposable = Completable.fromAction { db.userDao().add(user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { callback.onUserAdded() }

        this.disposable.add(disposable)
    }

    fun getUsers(callback: RoomDbCallback) {
        val disposable = db.userDao()
            .get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { users -> callback.onUsersReady(users) }

        this.disposable.add(disposable)
    }

    fun getCacheUsers(callback: RoomDbCallback) {
        val disposable = db.userDao()
            .get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { users ->
                if (users.size > 1)
                    callback.onUsersReady(users)
                else
                    getApiUsers(callback)
            }

        this.disposable.add(disposable)
    }

    private fun getApiUsers(callback: RoomDbCallback) {
        val apiService = ApiClient.buildService(ApiService::class.java)
        val call = apiService.getUsers()
        call.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback.onUsersReady(mutableListOf())
                Log.d("Call api ex: ", "cant get users", t)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val movies = response.body()
                movies?.apply {
                    callback.onUsersReady(this)
                    this.forEach {
                        addUser(it, callback)
                    }
                }
            }
        })
    }


    fun updateUser(oldId: Int, newUser: User, callback: RoomDbCallback) {
        val disposable = db.userDao()
            .get(oldId)
            .flatMapCompletable { user ->
                CompletableFromAction {
                    db.userDao().update(user.apply {
                        this.login = newUser.login
                        this.type = newUser.type
                        this.avatar_url = newUser.avatar_url
                    })
                }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { callback.onUserUpdated() }

        this.disposable.add(disposable)
    }

    fun deleteUser(oldId: Int, callback: RoomDbCallback) {
        val disposable = db.userDao()
            .get(oldId)
            .flatMapCompletable { user ->
                CompletableFromAction {
                    db.userDao().delete(user)
                }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { callback.onUserUpdated() }

        this.disposable.add(disposable)
    }

    fun disposeAll() {
        disposable.clear()
    }
}
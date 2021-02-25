package com.marbax.roomorm.room

import android.content.Context
import androidx.room.Room
import com.marbax.roomorm.entity.User
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.operators.completable.CompletableFromAction
import io.reactivex.schedulers.Schedulers

object DbProvider {

    private const val DB_NAME = "usersDb"
    private lateinit var db: AppDatabase
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun init(context: Context) {
        db = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
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

    // inner observers
    fun updateUser(oldId: Int, newUser: User, callback: RoomDbCallback) {
        val disposable = db.userDao()
            .get(oldId)
            .flatMapCompletable { user ->
                CompletableFromAction {
                    db.userDao().update(user.apply {
                        this.firstName = newUser.firstName
                        this.lastName = newUser.lastName
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
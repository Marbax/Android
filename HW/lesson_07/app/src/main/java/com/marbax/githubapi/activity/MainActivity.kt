package com.marbax.githubapi.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marbax.githubapi.adapter.UserAdapter
import com.marbax.githubapi.databinding.ActivityMainBinding
import com.marbax.githubapi.entity.User
import com.marbax.githubapi.room.DbProvider
import com.marbax.githubapi.room.RoomDbCallback

// cash users from github
class MainActivity : AppCompatActivity(), RoomDbCallback {

    private lateinit var binding: ActivityMainBinding
    private var adapter = UserAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DbProvider.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
        DbProvider.getCacheUsers(this)
    }

    override fun onUsersReady(users: List<User>) {
        adapter.submitList(users)
    }

    override fun onUserAdded() {
        //
    }

    override fun onUserUpdated() {
        //
    }

    override fun onUserDeleted() {
        //
    }

    override fun onDestroy() {
        DbProvider.disposeAll()
        super.onDestroy()
    }
}
package com.marbax.roomorm.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marbax.roomorm.adapters.UserAdapter
import com.marbax.roomorm.databinding.ActivityUsersListBinding
import com.marbax.roomorm.entity.User
import com.marbax.roomorm.room.DbProvider
import com.marbax.roomorm.room.RoomDbCallback

class UsersListActivity : AppCompatActivity(), RoomDbCallback {

    private lateinit var binding: ActivityUsersListBinding
    private var adapter = UserAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
        DbProvider.getUsers(this)
    }

    override fun onUsersReady(users: List<User>) {
        adapter.submitList(users)
    }
}
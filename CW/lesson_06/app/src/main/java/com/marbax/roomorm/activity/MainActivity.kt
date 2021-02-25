package com.marbax.roomorm.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marbax.roomorm.R
import com.marbax.roomorm.databinding.ActivityMainBinding
import com.marbax.roomorm.entity.User
import com.marbax.roomorm.room.DbProvider
import com.marbax.roomorm.room.RoomDbCallback

class MainActivity : AppCompatActivity(), RoomDbCallback, View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DbProvider.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.let {
            it.btnCreate.setOnClickListener(this)
            it.btnRead.setOnClickListener(this)
            it.btnUpdate.setOnClickListener(this)
            it.btnDelete.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreate -> create()
            R.id.btnRead -> read()
            R.id.btnUpdate -> update()
            R.id.btnDelete -> delete()
        }
    }

    // adding user to db using name and last name fields
    private fun create() {
        if (isNotEmptyFields(binding.etUserName, binding.etLastName))
            DbProvider.addUser(
                User(
                    binding.etUserName.text.toString(),
                    binding.etLastName.text.toString()
                ), this
            )
    }

    private fun read() {
        startActivity(Intent(this, UsersListActivity::class.java))
    }

    private fun delete() {
        if (isNotEmptyFields(binding.etUserId))
            DbProvider.deleteUser(binding.etUserId.text.toString().toInt(), this)
    }

    private fun update() {
        if (isNotEmptyFields(binding.etUserName, binding.etLastName, binding.etUserId))
            DbProvider.updateUser(
                binding.etUserId.text.toString().toInt(), User(
                    binding.etUserName.text.toString(),
                    binding.etLastName.text.toString()
                ), this
            )
    }

    private fun isNotEmptyFields(vararg fields: EditText): Boolean {
        return fields.all { it.text.isNotBlank() }
    }

    override fun onUserAdded() {
        Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
    }

    override fun onUserUpdated() {
        Toast.makeText(this, "User updated", Toast.LENGTH_SHORT).show()
    }

    override fun onUserDeleted() {
        Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        DbProvider.disposeAll()
        super.onDestroy()
    }
}
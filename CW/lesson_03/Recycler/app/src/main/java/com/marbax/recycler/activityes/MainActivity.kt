package com.marbax.recycler.activityes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marbax.recycler.R
import com.marbax.recycler.adapters.UserAdapter
import com.marbax.recycler.models.User
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RVMain.layoutManager = LinearLayoutManager(this)
        RVMain.adapter = UserAdapter(initUsers(100))
    }

    private fun initUsers(count: Int): List<User> {
        val list = mutableListOf<User>()
        (0..count).forEach { i ->
            list.add(
                User(
                    UUID.randomUUID().toString().take(5) + '_' + i.toString(),
                    UUID.randomUUID().toString().take(5) + '_' + i.toString()
                )
            )
        }
        return list
    }
}
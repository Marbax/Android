package com.example.mvvm.ui.activityes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm.R
import com.example.mvvm.network.GlobalApi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalApi.initialize()
    }
}
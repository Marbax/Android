package com.example.mvvm.util

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun navigate(destination: NavDirections, navController: NavController) =
    with(navController) {
        currentDestination?.getAction(destination.actionId)?.let { navigate(destination) }
    }
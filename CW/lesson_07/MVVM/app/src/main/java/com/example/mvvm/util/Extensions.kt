package com.example.mvvm.util

import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun Exception.noInternetException() = this is TimeoutException || this is UnknownHostException || this is ConnectException

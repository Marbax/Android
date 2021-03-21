package com.example.mvvm.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.models.SystemDialogModel
import com.example.mvvm.models.User
import com.example.mvvm.network.GlobalApi
import com.example.mvvm.util.noInternetException
import kotlinx.coroutines.launch

class UserInfoFVM(private val globalApi: GlobalApi) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _error = MutableLiveData<SystemDialogModel>()
    val error: LiveData<SystemDialogModel>
        get() = _error

    fun getUserInfo(login: String) {
        viewModelScope.launch {
            try {
                _user.postValue(globalApi.github.getUserInfo(login))
            } catch (e: Exception) {
                if (e.noInternetException()){
                    _error.postValue(
                        SystemDialogModel(
                            e::class.java.simpleName,
                            e.message
                        )
                    )
                }
            }
        }
    }

}
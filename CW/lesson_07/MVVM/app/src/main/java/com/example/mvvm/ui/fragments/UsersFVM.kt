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

class UsersFVM(private val globalApi: GlobalApi): ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _error = MutableLiveData<SystemDialogModel>()
    val error: LiveData<SystemDialogModel>
        get() = _error

    fun getUsers(){
        viewModelScope.launch {
            try {
                _users.postValue(globalApi.github.getUsers())
            }catch (e: Exception){
                if(e.noInternetException()){
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
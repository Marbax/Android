package com.example.mvvm.ui.fragments.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SystemDFVM: ViewModel() {

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action>
        get() = _action

    fun positive(){
        _action.postValue(Action.POSITIVE)
    }

    fun negative(){
        _action.postValue(Action.NEGATIVE)
    }

    enum class Action {
        POSITIVE, NEGATIVE
    }

}
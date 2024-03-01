package com.exxceliqsolutiions.taskuserdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exxceliqsolutiions.taskuserdetails.api.repository.UserRepository

/*
*  This is factory class need while getting data from Room DB.
* */


class UserViewModelFactory (private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GetRoomDataViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GetRoomDataViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
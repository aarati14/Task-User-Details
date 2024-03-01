package com.exxceliqsolutiions.taskuserdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.exxceliqsolutiions.taskuserdetails.api.repository.UserRepository
import com.exxceliqsolutiions.taskuserdetails.model.UserDetailsModel

/*
*  This view model is used to retrieve all rows stored in DB
* */


class GetRoomDataViewModel(private val repository: UserRepository)  : ViewModel() {
    val allModels: LiveData<List<UserDetailsModel>> = repository.getAllItems()
    }

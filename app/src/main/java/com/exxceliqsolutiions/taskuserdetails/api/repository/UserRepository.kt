package com.exxceliqsolutiions.taskuserdetails.api.repository


import androidx.lifecycle.LiveData
import com.exxceliqsolutiions.taskuserdetails.db.AppDatabase
import com.exxceliqsolutiions.taskuserdetails.model.UserDetailsModel

/*
*  This repository object is used for Room DB operations.
* */


class UserRepository(private val userDatabase: AppDatabase) {
    private val itemDao = userDatabase.appDao()
    suspend fun insertItem(item: List<UserDetailsModel>): Unit {
        return itemDao.insertItems(item)
    }

    fun getAllItems(): LiveData<List<UserDetailsModel>> {
        return itemDao.getAllDataSet()
    }
}






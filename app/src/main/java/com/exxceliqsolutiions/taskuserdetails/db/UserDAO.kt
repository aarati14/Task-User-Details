package com.exxceliqsolutiions.taskuserdetails.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.exxceliqsolutiions.taskuserdetails.model.UserDetailsModel

/*
*  This interface defines all operations (queries) needed for Room DB.
* */


@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItems(userDetailsModel: List<UserDetailsModel>)

    @Query("SELECT * FROM users")
    fun getAllDataSet(): LiveData<List<UserDetailsModel>>

    @Update
    fun updateUser(user: UserDetailsModel)

}


package com.exxceliqsolutiions.taskuserdetails.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exxceliqsolutiions.taskuserdetails.model.UserDetailsModel

/*
*  This is the abstract class for application database
* */


@Database(entities = [UserDetailsModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun appDao(): UserDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "users"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}

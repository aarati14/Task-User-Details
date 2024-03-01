package com.exxceliqsolutiions.taskuserdetails.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*
*  This is the class for the variables of user details array response from API as well as
*  defines table name to store these values to room db.
* */


@Entity(tableName = "users")
data class UserDetailsModel(
    @PrimaryKey
    @SerializedName("id")
    var userId : String,
    @SerializedName("email")
    var userEmail : String,
    @SerializedName("first_name")
    var userFirstName : String,
    @SerializedName("last_name")
    var userLastName : String,
    @SerializedName("avatar")
    var userProfileImage : String

)

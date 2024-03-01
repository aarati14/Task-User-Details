package com.exxceliqsolutiions.taskuserdetails.api.server_response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exxceliqsolutiions.taskuserdetails.model.UserDetailsModel
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

/*
*  This is the data class which is having variables for getting response through API and defining primary key for Room DB .
* */


@Entity
data class GetUserDetailsResponse (
    @PrimaryKey(autoGenerate = true)

    @SerializedName("data")
    var userDetailsList : ArrayList<UserDetailsModel>,

     var pageNumberInherit: String,
     var perPageRecordsInherit: String,
     var totalRecordsInherit: String,
     var totalPagesInherit: String,
     var supportDetailsInherit : JsonObject

): BaseResponse(pageNumberInherit , perPageRecordsInherit , totalRecordsInherit , totalPagesInherit , supportDetailsInherit)
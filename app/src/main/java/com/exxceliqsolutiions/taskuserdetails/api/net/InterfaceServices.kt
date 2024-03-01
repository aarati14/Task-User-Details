package com.exxceliqsolutiions.taskuserdetails.api.net

import com.exxceliqsolutiions.taskuserdetails.api.server_response.GetUserDetailsResponse
import com.exxceliqsolutiions.taskuserdetails.utility.UrlStrings
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
*  This is the interface created to set get request for api.
* */


interface InterfaceServices {

    @GET(UrlStrings.USER)
    fun getUserDetailsServices(@Query("page") page: Int): Call<GetUserDetailsResponse>

}
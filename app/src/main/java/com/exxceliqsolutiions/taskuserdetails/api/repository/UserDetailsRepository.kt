package com.exxceliqsolutiions.taskuserdetails.api.repository

import androidx.lifecycle.MutableLiveData
import com.exxceliqsolutiions.taskuserdetails.api.net.RetrofitApiClientInstance
import com.exxceliqsolutiions.taskuserdetails.api.server_response.GetUserDetailsResponse
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
*  This is the repository object for collecting response of API.
* */

@RunWith(MockitoJUnitRunner::class)
object UserDetailsRepository {
    val getUserDetailsResponse = MutableLiveData<GetUserDetailsResponse>()


    fun getUserDetailsApiCall(page : Int): MutableLiveData<GetUserDetailsResponse> {


            val call = RetrofitApiClientInstance.apiInterface.getUserDetailsServices(page)



            call.enqueue(object : Callback<GetUserDetailsResponse> {
                override fun onFailure(call: Call<GetUserDetailsResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<GetUserDetailsResponse>,
                    response: Response<GetUserDetailsResponse>
                ) {

                    val data = response.body()
                    getUserDetailsResponse.value = response.body()
                }
            })
        return getUserDetailsResponse
    }
}
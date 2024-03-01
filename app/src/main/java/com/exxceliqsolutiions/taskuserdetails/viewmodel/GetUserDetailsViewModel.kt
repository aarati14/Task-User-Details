package com.exxceliqsolutiions.taskuserdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exxceliqsolutiions.taskuserdetails.api.repository.UserDetailsRepository
import com.exxceliqsolutiions.taskuserdetails.api.server_response.GetUserDetailsResponse
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/*
*  This is a class of type view model used to connect repository to activity and get livedata from response.
* */

class GetUserDetailsViewModel  : ViewModel() {

    private var getUserDetailsLiveData: MutableLiveData<GetUserDetailsResponse>? = null

    fun getUserDetailsData(page:Int): LiveData<GetUserDetailsResponse>? {
        getUserDetailsLiveData = UserDetailsRepository.getUserDetailsApiCall(page)
        return getUserDetailsLiveData
    }


}
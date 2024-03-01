package com.exxceliqsolutiions.taskuserdetails.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.exxceliqsolutiions.taskuserdetails.databinding.ActivityDashboardScreenBinding
import com.exxceliqsolutiions.taskuserdetails.viewmodel.GetUserDetailsViewModel
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.exxceliqsolutiions.taskuserdetails.adapter.UserDetailsListAdapter
import com.exxceliqsolutiions.taskuserdetails.api.net.RetrofitApiClientInstance
import com.exxceliqsolutiions.taskuserdetails.api.repository.UserRepository
import com.exxceliqsolutiions.taskuserdetails.db.AppDatabase
import com.exxceliqsolutiions.taskuserdetails.db.SessionManager
import com.exxceliqsolutiions.taskuserdetails.db.UserDAO
import com.exxceliqsolutiions.taskuserdetails.model.UserDetailsModel
import com.exxceliqsolutiions.taskuserdetails.viewmodel.GetRoomDataViewModel
import com.exxceliqsolutiions.taskuserdetails.viewmodel.UserViewModelFactory
import kotlinx.coroutines.launch
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/*
*  This is main activity on which a recycler view displays data.
* */

@RunWith(MockitoJUnitRunner::class)
class DashboardScreenActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDashboardScreenBinding
    private val userDetailsViewModel by viewModels<GetUserDetailsViewModel>()
    private lateinit  var userRepository : UserRepository
    private var userDisplayList = ArrayList<UserDetailsModel>()
    private var page: Int = 1
    private lateinit var adapter :UserDetailsListAdapter
    private val TAG : String = "Dashboard Data"
    @Mock
    lateinit var userDAO : UserDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        RetrofitApiClientInstance.init(this)
        adapter = UserDetailsListAdapter(userDisplayList)

        // call to api
        getUserDetailsResponse()

        // Handles pagination by nested scroll view.
        binding.scrollView.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener
        { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight)
            {
                page++
                if(page <=5) {
                    getUserDetailsResponse()
                    adapter.notifyDataSetChanged()
                    Log.i(TAG, "BOTTOM SCROLL");
                }
            }
            else if (scrollY == 0) {
                Log.i(TAG, "TOP SCROLL");
                page--
                if(page >0) {
                    getUserDetailsResponse()
                    adapter.notifyDataSetChanged()

                }
            }
        })

        //logout from app
        binding.logoutButton.setOnClickListener{
            SessionManager.saveUserLoginStatus(this , false)
            val i = Intent(this@DashboardScreenActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }


    private fun getUserDetailsResponse()
    {
        binding.prgbar.visibility = View.VISIBLE
            userDetailsViewModel.getUserDetailsData(page)!!
                .observe(this, Observer { userDetailsResponse ->

                    val allUserDetails = userDetailsResponse.userDetailsList

                    userDAO = AppDatabase.getInstance(applicationContext)?.appDao()!!
                    val userDatabase = AppDatabase.getInstance(applicationContext)
                    val insertedItems = userDAO?.getAllDataSet()

                    // following data is used to test updated list in room db
                    /*  var userDetailsModel = UserDetailsModel("1" , "a" , "b" , "c" , "d")
        var userDetailsModel1 = UserDetailsModel("2" , "a" , "b" , "c" , "d")
        var userDetailsModel2 = UserDetailsModel("3" , "a" , "b" , "c" , "d")
        var userDetailsModel3 = UserDetailsModel("4" , "a" , "b" , "c" , "d")
        var userDetailsModel4 = UserDetailsModel("5" , "a" , "b" , "c" , "d")
         var allUserDetails =  ArrayList<UserDetailsModel>()

        allUserDetails.add( userDetailsModel)
        allUserDetails.add( userDetailsModel1)
        allUserDetails.add( userDetailsModel2)
        allUserDetails.add( userDetailsModel3)
        allUserDetails.add(userDetailsModel4)
         var userList =  ArrayList<UserDetailsModel>()
        userList.add( userDetailsModel)
        userList.add( userDetailsModel1)
        userList.add( userDetailsModel2)
        userList.add( userDetailsModel3)
*/



// Here will get the response of api and the user details list will store in room db

                    userRepository = UserRepository(userDatabase)
                    Log.d(TAG, "Inserted Item: ${insertedItems.toString()}")
                    lifecycleScope.launch {
                        userRepository.getAllItems()
                            .observe(this@DashboardScreenActivity) { userList ->
                                if (userList.isEmpty() || userList == null) {
                                    insertItemIntoDatabase(userDetailsResponse.userDetailsList)
                                    Log.d( TAG, "Inserted Item: ${insertedItems.toString()}")
                                } else {
                                    if (allUserDetails.size == userList.size) {
                                        println("No Updates")
                                    } else {
                                        val extraUserDetails =
                                            allUserDetails.filter { userDetails -> !userList.any { user -> user.userId == userDetails.userId } }

                                        Log.d(TAG, extraUserDetails.toString())
                                        extraUserDetails.forEach { userDetails ->
                                            val user = UserDetailsModel(
                                                userDetails.userId,
                                                userDetails.userEmail,
                                                userDetails.userFirstName,
                                                userDetails.userLastName,
                                                userDetails.userProfileImage
                                            )
                                            userDAO?.updateUser(user)
                                        }
                                        Mockito.verify(userDAO)?.insertItems(allUserDetails)
                                        val updatedUserList = userDAO?.getAllDataSet()

                                        Log.d(
                                            TAG,
                                            "Inserted Item: ${updatedUserList.toString()}"
                                        )
                                    }
                                }
                            }
                    }
                    val adapter = UserDetailsListAdapter(allUserDetails)
                    binding.userDetailsRecycler.adapter = adapter
                    binding.prgbar.visibility = View.GONE
                })
            //  getAllDataFromDB()

    }

    // this function used to insert item in DB
    private fun insertItemIntoDatabase(item: List<UserDetailsModel>) {
        lifecycleScope.launch {

            userRepository.insertItem(item)
        }
    }

    // This function is used to retrieve data from DB
    fun getAllDataFromDB()
    {
        val userDatabase = AppDatabase.getInstance(applicationContext)
        val repository = UserRepository(userDatabase)
        val viewModelFactory = UserViewModelFactory(repository)
        val userViewModel = ViewModelProvider(this, viewModelFactory)[GetRoomDataViewModel::class.java]

        userViewModel.allModels.observe(this@DashboardScreenActivity, Observer { updatedList ->
            updatedList.forEach { user ->
                userDisplayList.add(UserDetailsModel(user.userId, user.userEmail , user.userFirstName , user.userLastName , user.userProfileImage))
            }
        })
        val adapter = UserDetailsListAdapter(userDisplayList)
        binding.userDetailsRecycler.adapter = adapter

    }



}
package com.exxceliqsolutiions.taskuserdetails.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exxceliqsolutiions.taskuserdetails.databinding.UserDetailsListItemBinding
import com.exxceliqsolutiions.taskuserdetails.model.UserDetailsModel

/*
*  This is the adapter class created for a recycler view to display data.
* */


class UserDetailsListAdapter (private val userDetailsList: ArrayList<UserDetailsModel>) : RecyclerView.Adapter<UserDetailsListAdapter.ViewHolder>() {

    val userDetailsListNew : ArrayList<UserDetailsModel> = userDetailsList
    private lateinit var binding: UserDetailsListItemBinding
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        binding = UserDetailsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val userDetailsModel = userDetailsList[position]

        binding.firstNameText.text = userDetailsModel.userFirstName
        binding.lastNameText.text = userDetailsModel.userLastName
        binding.emailIdText.text = userDetailsModel.userEmail
        Glide.with(context)
            .load(userDetailsModel.userProfileImage)
            .into(binding.profileImage)
           // .onLoadFailed(context.resources.getDrawable(R.drawable.purple_background, context.resources.newTheme() ))

    }


    override fun getItemCount(): Int {
        return userDetailsList.size
    }


    class ViewHolder(private val binding: UserDetailsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    
}
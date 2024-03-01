package com.exxceliqsolutiions.taskuserdetails.utility


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import com.exxceliqsolutiions.taskuserdetails.R


/*
* This class contains validation functions for edit text.
* */

class Utilities (context: Context){



    /*
	 * Check is Edit Text null
	 */

    fun isEditTextNull(
        editText: EditText, context: Context,
        nameOfField: String
    ): Boolean {

        return if (!TextUtils.isEmpty(editText.text.toString().trim())) {
            true
        } else {
            editText.hint = MessageList.ENTER + nameOfField
            editText.setHintTextColor((AppCompatResources.getColorStateList(context , R.color.dark_purple)))
            false
        }
    }


    @SuppressLint("ResourceAsColor")
    fun wrongTextSetHint(
        editText: EditText, context: Activity,
        nameOfField: String, limit : Int
    ): Boolean {
        return if(editText.text.length == limit) {
            true
        }
        else
        {
            editText.setText("")
            editText.hint = MessageList.VALIDDATA + limit + " digit " + nameOfField
            editText.setHintTextColor(R.color.dark_purple)
            false
        }

    }


}
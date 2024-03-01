package com.exxceliqsolutiions.taskuserdetails.utility;

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/*
* This class applies semi bold font to textview
* */


public class UserDetailsSemiBoldTextView: AppCompatTextView{
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init(attrs)
    }

    constructor(context: Context?) : super(context!!) {
        init(null)
    }

    private fun init(attrs: AttributeSet?) {
        val tf = Typeface.createFromAsset(
            context.assets,
            "Inter-SemiBold.ttf"
        )
        typeface = tf
    }
}
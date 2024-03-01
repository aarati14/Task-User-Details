package com.exxceliqsolutiions.taskuserdetails.utility

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

/*
* This class applies bold font to button
* */


class UserDetailsButton: AppCompatButton {
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
            "Inter-Regular.ttf"
        )
        typeface = tf
    }
}
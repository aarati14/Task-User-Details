package com.exxceliqsolutiions.taskuserdetails.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.exxceliqsolutiions.taskuserdetails.databinding.ActivityLoginBinding
import com.exxceliqsolutiions.taskuserdetails.db.SessionManager
import com.exxceliqsolutiions.taskuserdetails.utility.Utilities


/*
*  This is login activity having validation for fields mobile number ( any 10 digit num) , password anything except null.
* */


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSoftKeyboard();

        binding.loginButton.setOnClickListener {
            validate()
        }
        binding.password.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                binding.loginButton.performClick()
                return@OnKeyListener true
            }
            false
        })
    }

    private fun validate() {
        var utilities: Utilities = Utilities(this)
        if (utilities.isEditTextNull(binding.mobileNumber!!, this, "mobile number")) {
            if(utilities.wrongTextSetHint(binding.mobileNumber!!, this, "mobile number" , 10)) {
                if (utilities.isEditTextNull(binding.password!!, this, "password")) {

                    binding.prgbar.visibility = View.VISIBLE
                    val i = Intent(this@LoginActivity, DashboardScreenActivity::class.java)
                    startActivity(i)
                    finish()
                    SessionManager.saveUserLoginStatus(this , true)
                    showToast("Logged in Successfully")
                    binding.prgbar.visibility = View.GONE
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun hideSoftKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val focus = this.currentFocus
        if (focus != null) inputMethodManager.hideSoftInputFromWindow(
            focus.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

}
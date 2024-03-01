package com.exxceliqsolutiions.taskuserdetails.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.exxceliqsolutiions.taskuserdetails.databinding.ActivitySplashScreenBinding
import com.exxceliqsolutiions.taskuserdetails.db.SessionManager

/*
*  This  activity  displays a splash screen for 3 seconds.
* */


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            try {
                if (SessionManager.getUserLoginStatus(this) == true) {
                    val i = Intent(this@SplashScreenActivity, DashboardScreenActivity::class.java)
                    startActivity(i)
                    finish()
                } else {
                    val i = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                    SessionManager.saveUserLoginStatus(this , false)
                }
            } catch (e: Exception) {
                Log.d("Error:", e.toString())
                finish()
            }
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {
        private const val SPLASH_TIME_OUT = 3000
    }
}
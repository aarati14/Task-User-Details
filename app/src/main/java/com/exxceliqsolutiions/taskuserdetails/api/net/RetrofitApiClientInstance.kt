package com.exxceliqsolutiions.taskuserdetails.api.net

import android.content.Context
import com.exxceliqsolutiions.taskuserdetails.utility.UrlStrings
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
*  This is the retrofit  object created to build a http client.and calling to API
* */


object RetrofitApiClientInstance {

    private lateinit var application: Context

    fun init(application: Context){
        RetrofitApiClientInstance.application =application
    }

    val retrofitClient: Retrofit.Builder by lazy {

        val levelType: HttpLoggingInterceptor.Level
       // if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = HttpLoggingInterceptor.Level.BODY //else levelType = HttpLoggingInterceptor.Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addNetworkInterceptor(logging).addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .build()
            chain.proceed(request)
        }.build()

        Retrofit.Builder()
            .baseUrl(UrlStrings.BASEURL)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())

    }

    val apiInterface: InterfaceServices by lazy {
        retrofitClient
            .build()
            .create(InterfaceServices::class.java)
    }

}